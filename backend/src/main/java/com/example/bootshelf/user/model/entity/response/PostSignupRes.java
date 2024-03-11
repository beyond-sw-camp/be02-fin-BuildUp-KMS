package com.example.bootshelf.user.model.entity.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostSignupRes{

    private String userEmail;
    private String userName;

}
