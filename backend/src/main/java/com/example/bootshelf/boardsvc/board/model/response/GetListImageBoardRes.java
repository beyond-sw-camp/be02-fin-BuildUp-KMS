package com.example.bootshelf.boardsvc.board.model.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetListImageBoardRes {

    private Integer boardImageIdx;
    private String boardImage;

}
