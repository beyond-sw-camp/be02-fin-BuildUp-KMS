package com.example.bootshelf.user.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    @Column(nullable = false, unique = true)
    private Integer userIdx;

    @Column(nullable = false)
    private String refreshToken;

//    public boolean validateRefreshToken(String refreshToken){
//        return this.refreshToken.equals(refreshToken);
//    }
//
//    public UserRefreshToken(Integer userIdx, String token) {
//        this.userIdx = userIdx;
//        this.refreshToken = token;
//    }
}
