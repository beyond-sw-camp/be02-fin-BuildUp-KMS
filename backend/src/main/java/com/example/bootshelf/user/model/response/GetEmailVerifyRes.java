package com.example.bootshelf.user.model.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetEmailVerifyRes {

    private String email;
    private Boolean status;
}
