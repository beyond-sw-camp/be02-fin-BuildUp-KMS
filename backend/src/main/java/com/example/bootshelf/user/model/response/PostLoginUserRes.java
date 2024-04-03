package com.example.bootshelf.user.model.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostLoginUserRes {
    private String accessToken;
    private String refreshToken;
}
