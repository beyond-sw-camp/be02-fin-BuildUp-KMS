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

    private String boardTitle;

    private String boardContent;

    private Integer boardCategoryIdx;

    private List<String> tagList;
}
