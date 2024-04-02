package com.example.bootshelf.user.model.entity;

import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;
import java.util.UUID;

@EntityScan
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

    private Integer userIdx;

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
