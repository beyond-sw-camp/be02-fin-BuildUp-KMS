package com.example.bootshelf.user.model.entity.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostUserLoginRes {
    private String token;
}
