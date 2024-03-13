package com.example.bootshelf.boardsvc.board.model.response;

import com.example.bootshelf.boardsvc.boardcategory.model.entity.BoardCategory;
import lombok.*;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostCreateBoardRes {
    private Integer idx;

    private String boardtitle;

    private String boardcontent;

    private Integer boardCategoryIdx;

    private List<String> boardTagList;

}
