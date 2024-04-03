package com.example.bootshelf.boardsvc.board.model.response;

import com.example.bootshelf.reviewsvc.review.model.response.GetListCommentReviewRes;
import lombok.*;
import org.joda.time.LocalDateTime;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetListCommentBoardRes {

    private Integer commentIdx;
    private Integer userIdx;
    private String userNickName;
    private String profileImage;
    private String boardCommentContent;
    private Integer upCnt;
    private LocalDateTime updatedAt;
    private List<GetListCommentBoardRes> children; // 대댓글 목록을 위한 필드 추가

}
