package com.example.bootshelf.admin.model.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostLoginAdminRes {
    private String token;
}
