package com.example.bootshelf.user.model.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetListUserResResult {
    private Long totalCnt;
    private Integer totalPages;
    private List<GetListUserRes> list;
}
