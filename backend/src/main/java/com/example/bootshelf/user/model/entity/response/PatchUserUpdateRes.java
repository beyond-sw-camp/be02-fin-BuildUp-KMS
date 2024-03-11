package com.example.bootshelf.user.model.entity.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatchUserUpdateRes {
    private String userPassWord;
    private String userNickName;
    private String profileImage;

}
