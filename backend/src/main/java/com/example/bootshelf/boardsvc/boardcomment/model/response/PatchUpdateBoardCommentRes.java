package com.example.bootshelf.boardsvc.boardcomment.model.response;

import lombok.*;
import org.joda.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatchUpdateBoardCommentRes {

    private Integer idx;
    private Integer userIdx;
    private String nickName;
    private String boardCommentContent;
    private String createdAt;
    private String updatedAt;

}
