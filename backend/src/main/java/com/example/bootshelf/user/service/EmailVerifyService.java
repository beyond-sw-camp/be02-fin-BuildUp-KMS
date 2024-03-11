package com.example.bootshelf.user.service;


import com.example.bootshelf.user.model.entity.EmailVerify;
import com.example.bootshelf.user.model.entity.request.GetEmailVerifyReq;
import com.example.bootshelf.user.repository.EmailVerifyRepostiory;
import com.example.bootshelf.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmailVerifyService {
    private final EmailVerifyRepostiory emailVerifyRepository;

    private final UserRepository userRepository;

    @Transactional(readOnly = false)
    public void create(String email, String token) {
        emailVerifyRepository.save(EmailVerify.builder()
                .email(email)
                .uuid(token)
                .build());
    }

    public Boolean verify(GetEmailVerifyReq getEmailVerifyReq) {
        Optional<EmailVerify> result = emailVerifyRepository.findByEmail(getEmailVerifyReq.getEmail());

        if(result.isPresent()) {
            EmailVerify emailVerify = result.get();
            if(emailVerify.getUuid().equals(getEmailVerifyReq.getUuid())) {
//                Optional<User> successUser = userRepository.findByUserEmail(getEmailVerifyReq.getEmail());
//                User user = successUser.get();
//
//                couponRepository.save(Coupon.builder()
//                        .couponName("회원가입 감사 10% 쿠폰")
//                        .couponDiscountRate(10)
//                        .status(true)
//                        .receivedDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")))
//                        // 만료날짜 : 1달 뒤
//                        .couponExpirationDate(LocalDateTime.now().plusMonths(2L).format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")))
//                        .user(user)
//                        .build());
//
//                couponRepository.save(Coupon.builder()
//                        .couponName(LocalDateTime.now().getMonthValue() + " 월 LONUA 정기 15% 쿠폰")
//                        .couponDiscountRate(15)
//                        .status(true)
//                        .receivedDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")))
//                        // 만료날짜 : 1달 뒤
//                        .couponExpirationDate(LocalDateTime.now().plusMonths(1L).format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")))
//                        .user(user)
//                        .build());

                return true;
            }
        }
        return false;
    }
}
