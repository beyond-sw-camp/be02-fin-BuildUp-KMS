package com.example.bootshelf.esreview.model.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewSearchResResult {

    private Long totalHits;
    private Integer totalPages;
    private List<ReviewSearchRes> list;


}
