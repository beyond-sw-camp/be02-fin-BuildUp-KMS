package com.example.bootshelf.user.model.entity.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostLoginUserRes {
    private String token;
}
