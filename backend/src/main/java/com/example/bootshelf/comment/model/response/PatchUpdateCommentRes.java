package com.example.bootshelf.comment.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatchUpdateCommentRes {

    private String reviewCommentContent;
    private String updateAt;

}
