package com.example.bootshelf.reviewcommentup.model;

import com.example.bootshelf.reviewcomment.model.entity.ReviewComment;
import com.example.bootshelf.user.model.entity.User;
import lombok.*;

import javax.persistence.*;

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

    @ManyToOne
    @JoinColumn(name = "User_idx")
    private User user;

    @ManyToOne
    @JoinColumn(name = "ReviewComment_idx")
    private ReviewComment reviewComment;
}
