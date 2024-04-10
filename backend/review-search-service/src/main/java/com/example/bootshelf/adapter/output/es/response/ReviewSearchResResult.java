package com.example.bootshelf.adapter.output.es.response;

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
    private Object[] lastSearchAfter;
    private List<ReviewSearchRes> list;
}
