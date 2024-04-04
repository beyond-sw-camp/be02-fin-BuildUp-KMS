package com.example.bootshelf.user.model.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatchFindPasswordUserReq {

    private String name;
    private String email;
}
