package com.example.bootshelf.board.model.entity.res;

import com.example.bootshelf.boardcategory.model.entity.BoardCategory;
import com.example.bootshelf.boardcomment.model.entity.BoardComment;
import com.example.bootshelf.boardimage.model.entity.BoardImage;
import com.example.bootshelf.boardtag.model.entity.BoardTag;
import com.example.bootshelf.tag.model.entity.Tag;
import com.example.bootshelf.user.model.entity.User;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetListBoardRes {
    private Integer idx;

    private String boardTitle;

    private String boardContent;

    private Integer boardCategoryIdx;

    private List<Integer> boardTagListIdx;

    private Integer viewCnt;

    private Integer upCnt;

    private Integer scrapCnt;

    private Integer commentCnt;

    private String createdAt;

    private String updatedAt;

    private List<String> boardImageList;

    private List<Integer> boardCommentList;

    private String userProfile;

    private String userName;
}
