package com.example.bootshelf.board.model.entity.req;

import lombok.*;

import javax.persistence.Column;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatchUpdateBoardReq {
    @Column(nullable = false, length = 100)
    private String boardTitle;

    @Column(nullable = false, length = 400)
    private String boardContent;

    private Integer boardCategoryIdx;

    private List<String> tagList;
}
