package com.example.bootshelf.boardsvc.boardtag.model.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetListHotTagRes {

    private Integer tagIdx;
    private String tagName;
    private Long count;

}
