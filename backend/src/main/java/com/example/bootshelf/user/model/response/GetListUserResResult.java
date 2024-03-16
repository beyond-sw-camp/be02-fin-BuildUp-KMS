package com.example.bootshelf.user.model.response;

import com.example.bootshelf.tag.model.response.GetListTagRes;
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
