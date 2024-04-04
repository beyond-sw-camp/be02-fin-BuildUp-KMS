package com.example.bootshelf.reviewsvc.reviewcommentup.model.entity;

import com.example.bootshelf.reviewsvc.reviewcomment.model.entity.ReviewComment;
import com.example.bootshelf.reviewsvc.reviewcommentup.model.request.PostCreateReviewCommentUpReq;
import com.example.bootshelf.user.model.entity.User;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewCommentUp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "User_idx")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ReviewComment_idx")
    private ReviewComment reviewComment;

    private Boolean status;

    public static ReviewCommentUp toEntity(User user, PostCreateReviewCommentUpReq req) {
        return ReviewCommentUp.builder()
                .user(User.builder().idx(user.getIdx()).build())
                .reviewComment(ReviewComment.builder().idx(req.getReviewCommentIdx()).build())
                .status(true)
                .build();
    }
}
