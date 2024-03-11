package com.example.bootshelf.user.service;


import com.example.bootshelf.common.BaseRes;
import com.example.bootshelf.config.utils.JwtUtils;
import com.example.bootshelf.user.exception.UserAccountException;
import com.example.bootshelf.user.exception.UserDuplicateException;
import com.example.bootshelf.user.exception.UserNotFoundException;
import com.example.bootshelf.user.model.entity.User;
import com.example.bootshelf.user.model.entity.request.PatchUserUpdateReq;
import com.example.bootshelf.user.model.entity.request.PostCheckPasswordReq;
import com.example.bootshelf.user.model.entity.request.PostSignUpReq;
import com.example.bootshelf.user.model.entity.request.PostUserLoginReq;
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

import javax.persistence.EntityManager;
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

    @Autowired
    private EntityManager entityManager;


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JavaMailSender emailSender;
    private final EmailVerifyService emailVerifyService;



    // 회원가입
    @Transactional(readOnly = false)
    public BaseRes signup(PostSignUpReq postSignUpReq) {
        Optional<User> resultEmail = userRepository.findByEmail(postSignUpReq.getUserEmail());
        Optional<User> resultUserNickName = userRepository.findByNickName(postSignUpReq.getUserNickName());


        // 중복된 이메일에 대한 예외처리
        if (resultEmail.isPresent()) {
            throw UserDuplicateException.forSignupEmail(postSignUpReq.getUserEmail());
        }
        // 중복된 닉네임에 대한 예외처리
        if (resultUserNickName.isPresent()) {
            throw UserDuplicateException.forSignupUserNickName(postSignUpReq.getUserNickName());
        }


        User user = User.builder()
                .password(passwordEncoder.encode(postSignUpReq.getUserPassword()))
                .name(postSignUpReq.getUserName())
                .email(postSignUpReq.getUserEmail())
                .nickName(postSignUpReq.getUserNickName())
                .profileImage(postSignUpReq.getUserProfileImage())
                .authority("ROLE_USER")
                .createdAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")))
                .updatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")))
                .status(false)
                .build();

        userRepository.save(user);



        BaseRes baseRes = BaseRes.builder()
                .isSuccess(true)
                .message("회원가입에 성공하였습니다.")
                .result(PostSignupRes.builder()
                        .userEmail(user.getEmail())
                        .userName(user.getName())
                        .build())
                .build();

        return baseRes;
    }

    @Transactional(readOnly = true)
    public BaseRes list(Integer page, Integer size) {

        Pageable pageable = PageRequest.of(page-1, size);

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

        if(result.isPresent()) {
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
    public BaseRes login(PostUserLoginReq postUserLoginReq) {
        Optional<User> result = userRepository.findByEmail(postUserLoginReq.getEmail());

        if(result.isEmpty()) {
            throw UserNotFoundException.forEmail(postUserLoginReq.getEmail());
        }

        User user = result.get();
        if (passwordEncoder.matches(postUserLoginReq.getPassword(), user.getPassword()) && user.getStatus().equals(true)) {
            PostUserLoginRes postUserLoginRes = PostUserLoginRes.builder()
                    .token(JwtUtils.generateAccessToken(user, secretKey, expiredTimeMs))
                    .build();

            return BaseRes.builder()
                    .isSuccess(true)
                    .message("로그인에 성공하였습니다.")
                    .result(postUserLoginRes)
                    .build();
        } else {
            throw UserAccountException.forInvalidPassword(postUserLoginReq.getPassword());
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
    public void sendEmail(PostSignUpReq postSignUpReq) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(postSignUpReq.getUserEmail());
        message.setSubject("[BootShelf] 회원가입을 완료하기 위해서 이메일 인증을 진행해 주세요"); // 메일 제목

        String uuid = UUID.randomUUID().toString();
        message.setText("http://localhost:8080/user/verify?email=" + postSignUpReq.getUserEmail() + "&uuid=" + uuid);    // 메일 내용

        emailSender.send(message);
        emailVerifyService.create(postSignUpReq.getUserEmail(), uuid);
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
    public BaseRes update(String userEmail, PatchUserUpdateReq patchUserUpdateReq) {
        Optional<User> result = userRepository.findByEmail(userEmail);

        if (result.isPresent()) {
            User user = result.get();

            if(patchUserUpdateReq.getPassword() != null) {
                user.update(patchUserUpdateReq, passwordEncoder.encode(patchUserUpdateReq.getPassword()));
            } else {
                user.update(patchUserUpdateReq, null);
            }
            user.setUpdatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));
            userRepository.save(user);

            PatchUserUpdateRes patchUserUpdateRes = PatchUserUpdateRes.builder()
                    .userPassWord(user.getPassword())
                    .userNickName(user.getNickName())
                    .profileImage(user.getProfileImage())
                    .build();

            return BaseRes.builder()
                    .isSuccess(true)
                    .message("회원정보 수정 성공")
                    .result(patchUserUpdateRes)
                    .build();
        } else {
            throw UserNotFoundException.forEmail(userEmail);        }
    }

    @Transactional(readOnly = false)
    public BaseRes cancel(Integer userIdx) {

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

    @Transactional(readOnly = false)
    public BaseRes delete(Integer idx) {

        Optional<User> byUserIdx = userRepository.findByIdx(idx);
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

            throw UserNotFoundException.forIdx(idx);
        }
    }
}
