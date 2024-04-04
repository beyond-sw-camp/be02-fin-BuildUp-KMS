package com.example.bootshelf.tag.model.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetListTagResResult {
    private Long totalCnt;
    private Integer totalPages;
    private List<GetListTagRes> list;
}
