package com.example.bootshelf.user.service;


import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.example.bootshelf.common.BaseRes;
import com.example.bootshelf.config.utils.JwtUtils;
import com.example.bootshelf.user.exception.UserAccountException;
import com.example.bootshelf.user.exception.UserDuplicateException;
import com.example.bootshelf.user.exception.UserNotFoundException;
import com.example.bootshelf.user.model.entity.User;
import com.example.bootshelf.user.model.entity.request.PatchUpdateUserReq;
import com.example.bootshelf.user.model.entity.request.PostCheckPasswordReq;
import com.example.bootshelf.user.model.entity.request.PostSignUpUserReq;
import com.example.bootshelf.user.model.entity.request.PostLoginUserReq;
import com.example.bootshelf.user.model.entity.response.*;
import com.example.bootshelf.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    @Value("${jwt.secret-key}")
    private String secretKey;
    @Value("${jwt.token.expired-time-ms}")
    private Long expiredTimeMs;
    @Value("${cloud.aws.s3.profile-bucket}")
    private String profileBucket;

    @Autowired
    private EntityManager entityManager;
    private final AmazonS3 s3;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JavaMailSender emailSender;
    private final EmailVerifyService emailVerifyService;

    public String makeFolder() {
        String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        String folderPath = str.replace("/", File.separator);
        return folderPath;
    }

    public String saveFile(MultipartFile profileImage) {
        String originalName = profileImage.getOriginalFilename();

        String folderPath = makeFolder();
        String uuid = UUID.randomUUID().toString();
        String saveFileName = folderPath + File.separator + uuid + "_" + originalName;

        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(profileImage.getSize());
            metadata.setContentType(profileImage.getContentType());

            s3.putObject(profileBucket, saveFileName.replace(File.separator, "/"), profileImage.getInputStream(), metadata);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            // 로컬 파일 시스템에서 파일 삭제
            File localFile = new File(saveFileName);
            if (localFile.exists()) {
                localFile.delete();
            }
            return s3.getUrl(profileBucket, saveFileName.replace(File.separator, "/")).toString();

        }
    }

    // 회원가입
    @Transactional(readOnly = false)
    public BaseRes signup(PostSignUpUserReq postSignUpUserReq, MultipartFile profileImage) {
        Optional<User> resultEmail = userRepository.findByEmail(postSignUpUserReq.getEmail());
        Optional<User> resultUserNickName = userRepository.findByNickName(postSignUpUserReq.getNickName());

        // 중복된 이메일에 대한 예외처리
        if (resultEmail.isPresent()) {
            throw UserDuplicateException.forSignupEmail(postSignUpUserReq.getEmail());
        }
        // 중복된 닉네임에 대한 예외처리
        if (resultUserNickName.isPresent()) {
            throw UserDuplicateException.forSignupUserNickName(postSignUpUserReq.getNickName());
        }

        String profilePhoto = saveFile(profileImage);

        User user = User.builder()
                .password(passwordEncoder.encode(postSignUpUserReq.getPassword()))
                .name(postSignUpUserReq.getName())
                .email(postSignUpUserReq.getEmail())
                .nickName(postSignUpUserReq.getNickName())
                .profileImage(profilePhoto.replace(File.separator, "/"))
                .authority("ROLE_USER")
                .createdAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")))
                .updatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")))
                .status(false)
                .build();

        userRepository.save(user);

        BaseRes baseRes = BaseRes.builder()
                .isSuccess(true)
                .message("회원가입에 성공하였습니다.")
                .result(PostSignUpUserRes.builder()
                        .userEmail(user.getEmail())
                        .userName(user.getName())
                        .build())
                .build();

        return baseRes;
    }

    @Transactional(readOnly = true)
    public BaseRes list(Integer page, Integer size) {

        Pageable pageable = PageRequest.of(page - 1, size);

        Page<User> userList = userRepository.findUserList(pageable);

        List<GetListUserRes> getListUserResList = new ArrayList<>();
        for (User user : userList) {

            GetListUserRes getListUserRes = GetListUserRes.builder()
                    .userIdx(user.getIdx())
                    .userEmail(user.getEmail())
                    .name(user.getName())
                    .build();

            getListUserResList.add(getListUserRes);
        }

        return BaseRes.builder()
                .isSuccess(true)
                .message("요청 성공")
                .result(getListUserResList)
                .build();
    }

    @Transactional(readOnly = true)
    public BaseRes read(String email) {
        Optional<User> result = userRepository.findUser(email);

        if (result.isPresent()) {
            User user = result.get();

            GetListUserRes getListUserRes = GetListUserRes.builder()
                    .userIdx(user.getIdx())
                    .userEmail(user.getEmail())
                    .name(user.getName())
                    .build();

            return BaseRes.builder()
                    .isSuccess(true)
                    .message("요청 성공")
                    .result(getListUserRes)
                    .build();
        } else {
            throw UserNotFoundException.forEmail(email);
        }
    }

    // 회원 로그인
    @Transactional(readOnly = false)
    public BaseRes login(PostLoginUserReq postLoginUserReq) {
        Optional<User> result = userRepository.findByEmail(postLoginUserReq.getEmail());

        if (result.isEmpty()) {
            throw UserNotFoundException.forEmail(postLoginUserReq.getEmail());
        }

        User user = result.get();
        if (passwordEncoder.matches(postLoginUserReq.getPassword(), user.getPassword()) && user.getStatus().equals(true)) {
            PostLoginUserRes postLogInUserRes = PostLoginUserRes.builder()
                    .token(JwtUtils.generateAccessToken(user, secretKey, expiredTimeMs))
                    .build();

            return BaseRes.builder()
                    .isSuccess(true)
                    .message("로그인에 성공하였습니다.")
                    .result(postLogInUserRes)
                    .build();
        } else {
            throw UserAccountException.forInvalidPassword(postLoginUserReq.getPassword());
        }
    }

    // 회원정보 수정을 위한 비밀번호 체크
    @Transactional
    public Boolean checkPassword(User user, PostCheckPasswordReq postCheckPasswordReq) {
        if (passwordEncoder.matches(postCheckPasswordReq.getPassword(), user.getPassword())) {
            return true;
        } else {
            return false;
        }
    }

    // 인증메일 발송
    @Transactional(readOnly = false)
    public void sendEmail(PostSignUpUserReq postSignUpUserReq) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(postSignUpUserReq.getEmail());
        message.setSubject("[BootShelf] 회원가입을 완료하기 위해서 이메일 인증을 진행해 주세요"); // 메일 제목

        String uuid = UUID.randomUUID().toString();
        message.setText("http://localhost:8080/user/verify?email=" + postSignUpUserReq.getEmail() + "&uuid=" + uuid);    // 메일 내용

        emailSender.send(message);
        emailVerifyService.create(postSignUpUserReq.getEmail(), uuid);
    }

    // 메일 인증 완료 후 회원 상태 수정
    @Transactional(readOnly = false)
    public BaseRes updateStatus(String email) {
        Optional<User> result = userRepository.findByEmail(email);
        if (result.isPresent()) {
            User user = result.get();
            user.setStatus(true);
            userRepository.save(user);
            // 추가한 것
            entityManager.flush();

            return BaseRes.builder()
                    .isSuccess(true)
                    .message("메일 인증에 성공하였습니다.")
                    .result(GetEmailVerifyRes.builder()
                            .email(user.getEmail())
                            .status(user.getStatus())
                            .build())
                    .build();
        } else {
            throw UserNotFoundException.forEmail(email);
        }
    }

    // 카카오 회원가입
    @Transactional
    public void kakaoSignup(String nickName) {

        User user = User.builder()
                .email(nickName)
                .password(passwordEncoder.encode("kakao"))
                .nickName(nickName)
                .name(nickName)
                .profileImage("")
                .authority("ROLE_USER")
                .createdAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")))
                .updatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")))
                .status(true)
                .build();

        user = userRepository.save(user);

    }

    // 회원 이메일 검증
    public User getUserEmail(String email) {
        Optional<User> result = userRepository.findByEmail(email);

        if (result.isPresent()) {
            return result.get();
        } else {
            return null;  // 카카오 로그인 시 null이 반환되면 가입 진행토록 설정
        }
    }

    // 회원정보 수정
    @Transactional(readOnly = false)
    public BaseRes update(String userEmail, PatchUpdateUserReq patchUpdateUserReq) {
        Optional<User> result = userRepository.findByEmail(userEmail);

        if (result.isPresent()) {
            User user = result.get();

            if (patchUpdateUserReq.getPassword() != null) {
                user.update(patchUpdateUserReq, passwordEncoder.encode(patchUpdateUserReq.getPassword()));
            } else {
                user.update(patchUpdateUserReq, null);
            }
            user.setUpdatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));
            userRepository.save(user);

            PatchUpdateUserRes patchUpdateUserRes = PatchUpdateUserRes.builder()
                    .userPassWord(user.getPassword())
                    .userNickName(user.getNickName())
                    .profileImage(user.getProfileImage())
                    .build();

            return BaseRes.builder()
                    .isSuccess(true)
                    .message("회원정보 수정 성공")
                    .result(patchUpdateUserRes)
                    .build();
        } else {
            throw UserNotFoundException.forEmail(userEmail);
        }
    }

    @Transactional(readOnly = false)
    public BaseRes cancel(Integer userIdx) {

        Optional<User> byUserIdx = userRepository.findByIdx(userIdx);
        if (byUserIdx.isPresent()) {
            User loginUser = byUserIdx.get();
            loginUser.setStatus(false);
            userRepository.save(loginUser);

            return BaseRes.builder()
                    .isSuccess(true)
                    .message("요청 성공")
                    .result("회원의 상태가 탈퇴 상태로 변경되었습니다.")
                    .build();
        } else {

            throw UserNotFoundException.forIdx(userIdx);
        }
    }

    @Transactional(readOnly = false)
    public BaseRes delete(Integer userIdx) {

        Integer result = userRepository.deleteByIdx(userIdx);
        if (!result.equals(0)) {

            return BaseRes.builder()
                    .isSuccess(true)
                    .message("요청 성공")
                    .result("회원이 삭제되었습니다.")
                    .build();
        } else {
            throw UserNotFoundException.forIdx(userIdx);
        }
    }
}