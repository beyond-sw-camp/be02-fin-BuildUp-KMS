package com.example.bootshelf.board.model.entity.res;

import com.example.bootshelf.board.model.entity.Board;
import com.example.bootshelf.boardcategory.model.entity.BoardCategory;
import com.example.bootshelf.boardtag.model.entity.BoardTag;
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

    private BoardCategory boardCategory;

    private List<String> boardTagList;

}
