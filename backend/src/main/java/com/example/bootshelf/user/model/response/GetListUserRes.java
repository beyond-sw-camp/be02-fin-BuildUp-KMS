package com.example.bootshelf.user.model.response;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetListUserRes {

    private Integer userIdx;
    private String email;
    private String name;
    private String nickName;
    private String profileImage;

}
