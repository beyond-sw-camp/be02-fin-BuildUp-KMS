package com.example.bootshelf.comment.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostCreateCommentReq {

    @Column(nullable = false, length = 500)
    private String reviewCommentContent;
}
