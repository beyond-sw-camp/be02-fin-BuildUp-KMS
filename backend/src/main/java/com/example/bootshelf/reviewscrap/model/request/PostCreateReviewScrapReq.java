package com.example.bootshelf.reviewscrap.model.request;

import lombok.*;

import javax.persistence.Entity;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostCreateReviewScrapReq {
    private Integer reviewIdx;
}
