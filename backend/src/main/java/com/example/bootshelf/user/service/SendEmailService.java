package com.example.bootshelf.user.service;


import com.example.bootshelf.user.model.response.GetEmailVerifyRes;
import com.example.bootshelf.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


@Service
@AllArgsConstructor
public class SendEmailService {

    private final UserRepository userRepository;
    private final UserService userService;
    private final JavaMailSender mailSender;

    public void findPassword(String email, String name) throws MessagingException {

        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            String str = userService.updatePassword(email, name);

            helper.setTo(email);
            helper.setSubject("[BootShelf] 임시 비밀번호 안내 이메일 입니다.");

            String imagePath = "https://github.com/hyungdoyou/devops/assets/148875644/f9dc322f-9d41-455d-b35c-e3cfcd7c008d";
            String content = "<html><body style= 'font-family: Pretendard; font-style: normal; font-weight: 500'> " +
                    "<p style='color: rgb(84, 29, 122); height: 100%; margin: 0; text-align: center; font-size: 30px; line-height: 3;'>" +
                    "<img src='" + imagePath + "' style='width: auto; height: auto;'/> <br/>" +
                    "[ " + name + " ] 회원님의 임시 비밀번호는" + "[ " + str + " ] 입니다. " +
                    "</p>" + "<p style='color: #333; height: 100%; margin: 0; text-align: center; font-size: 25px; line-height: 3;'>" +
                    "임시 비밀번호로 로그인 후 비밀번호를 변경해주세요 <br/>" + "</p>" + "<div style='text-align: center; line-height: 3;'>\n" +
                    "<a href=`http://www.bootshelf-kro.kr` style='color: #fff; text-decoration: none; background-color: rgb(84, 29, 122); padding: 10px 20px; border-radius: 5px; border: 2px solid rgb(84, 29, 122); display: inline-block; font-size: 20px; line-height: 1;'>\n" +
                    "로그인 하기\n" + "</a>\n" + "</div>" + "</body></html>";

            helper.setText(content, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
            // 예외 처리 로직
        }
    }
}
