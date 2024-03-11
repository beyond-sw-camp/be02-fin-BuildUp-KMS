package com.example.bootshelf.comment.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetListCommentRes {

    private String userNickName;
    private String reviewCommnetContent;
    private String createAt;
    private String updateAt;
}
