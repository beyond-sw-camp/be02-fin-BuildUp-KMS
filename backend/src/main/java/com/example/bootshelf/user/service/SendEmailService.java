package com.example.bootshelf.user.service;


import com.example.bootshelf.user.model.response.GetEmailVerifyRes;
import com.example.bootshelf.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class SendEmailService {

    @Autowired
    UserRepository userRepository;
    private final UserService userService;
    private JavaMailSender mailSender;
    private static final String FROM_ADDRESS = "heueun77@gmail.com";

    public GetEmailVerifyRes findPassword(String email, String name){
        String str = userService.updatePassword(email, name);
        GetEmailVerifyRes dto = new GetEmailVerifyRes();
        dto.setEmail(email);
        dto.setTitle(name+"님의 임시비밀번호 안내 이메일 입니다.");
        dto.setMessage("안녕하세요. 임시비밀번호 안내 관련 이메일 입니다." + "[" + name + "]" +"님의 임시 비밀번호는 "
                + str + " 입니다.");
        return dto;
    }

    public void mailSend(GetEmailVerifyRes getEmailVerifyRes) {
        System.out.println("이메일 전송 완료");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(getEmailVerifyRes.getEmail());
        message.setFrom(SendEmailService.FROM_ADDRESS);
        message.setSubject(getEmailVerifyRes.getTitle());
        message.setText(getEmailVerifyRes.getMessage());

        mailSender.send(message);
    }
}
