package com.example.bootshelf.esboard.model.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardSearchResResult {
    private Long totalHits;
    private Integer totalPages;
    private Object[] lastSearchAfter;
    private List<BoardSearchRes> list;
}
