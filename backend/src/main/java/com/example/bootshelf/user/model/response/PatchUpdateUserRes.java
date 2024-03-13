package com.example.bootshelf.user.model.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatchUpdateUserRes {
    private String userPassWord;
    private String userNickName;
    private String profileImage;

}
