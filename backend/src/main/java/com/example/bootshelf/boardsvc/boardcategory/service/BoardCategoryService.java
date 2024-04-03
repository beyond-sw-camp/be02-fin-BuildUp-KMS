package com.example.bootshelf.boardsvc.boardcategory.service;

import com.example.bootshelf.boardsvc.board.model.entity.Board;
import com.example.bootshelf.boardsvc.board.repository.BoardRepository;
import com.example.bootshelf.boardsvc.boardcategory.model.entity.BoardCategory;
import com.example.bootshelf.boardsvc.boardcategory.model.request.PatchUpdateBoardCategoryReq;
import com.example.bootshelf.boardsvc.boardcategory.model.request.PostCreateBoardCategoryReq;
import com.example.bootshelf.boardsvc.boardcategory.model.response.GetListBoardCategoryRes;
import com.example.bootshelf.boardsvc.boardcategory.model.response.PatchUpdateBoardCategoryRes;
import com.example.bootshelf.boardsvc.boardcategory.model.response.PostCreateBoardCategoryRes;
import com.example.bootshelf.boardsvc.boardcategory.repository.BoardCategoryRepository;
import com.example.bootshelf.common.BaseRes;
import com.example.bootshelf.common.error.ErrorCode;
import com.example.bootshelf.common.error.entityexception.BoardCategoryException;
import com.example.bootshelf.common.error.entityexception.BoardCommentException;
import com.example.bootshelf.common.error.entityexception.BoardException;
import lombok.RequiredArgsConstructor;
import org.joda.time.LocalDateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardCategoryService {

    private final BoardCategoryRepository boardCategoryRepository;
    private final BoardRepository boardRepository;

    // 카테고리 추가
    public BaseRes createBoardCategory(PostCreateBoardCategoryReq postCreateBoardCategoryReq) {

        Optional<BoardCategory> result = boardCategoryRepository.findByCategoryName(postCreateBoardCategoryReq.getCategoryName());

        if (result.isPresent()) {
            throw new BoardException(ErrorCode.DUPLICATED_BOARD_CATEGORY, String.format("Board Category [ %s ] is duplicated.", postCreateBoardCategoryReq.getCategoryName()));
        }

        if (postCreateBoardCategoryReq.getCategoryName() == null || postCreateBoardCategoryReq.getCategoryName().isEmpty()) {
            throw new BoardCommentException(ErrorCode.INVALID_INPUT_VALUE, String.format("Board Category is empty."));
        }

        boardCategoryRepository.save(BoardCategory.builder()
                .categoryName(postCreateBoardCategoryReq.getCategoryName())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .status(true)
                .build());

        PostCreateBoardCategoryRes postCreateBoardCategoryRes = PostCreateBoardCategoryRes.builder()
                .categoryName(postCreateBoardCategoryReq.getCategoryName())
                .build();

        BaseRes baseRes = BaseRes.builder()
                .isSuccess(true)
                .message("게시판 카테고리 등록 성공")
                .result(postCreateBoardCategoryRes)
                .build();

        return baseRes;
    }


    // 카테고리 전체 목록
    @Transactional(readOnly = true)
    public BaseRes listBoardCategory(Pageable pageable) {

        Page<BoardCategory> boardCategoriesPage = boardCategoryRepository.findAll(pageable);

        List<BoardCategory> boardCategories = boardCategoriesPage.getContent();

        List<GetListBoardCategoryRes> response = new ArrayList<>();
        for (BoardCategory boardCategory : boardCategories) {
            GetListBoardCategoryRes getListBoardCategoryRes = GetListBoardCategoryRes.builder()
                    .idx(boardCategory.getIdx())
                    .categoryName(boardCategory.getCategoryName())
                    .build();

            response.add(getListBoardCategoryRes);
        }

        BaseRes baseRes = BaseRes.builder()
                .isSuccess(true)
                .message("요청 성공")
                .result(response)
                .build();

        return baseRes;
    }

    // 수정
    public BaseRes updateBoardCategory(PatchUpdateBoardCategoryReq patchUpdateBoardCategoryReq, Integer boardCategoryIdx) {
        Optional<BoardCategory> result = boardCategoryRepository.findById(boardCategoryIdx);
        Optional<BoardCategory> resultCategoryName = boardCategoryRepository.findByCategoryName(patchUpdateBoardCategoryReq.getCategoryName());

        if (resultCategoryName.isPresent()) {
            throw new BoardException(ErrorCode.DUPLICATED_BOARD_CATEGORY, String.format("Board Category [ %s ] is duplicated.", patchUpdateBoardCategoryReq.getCategoryName()));
        }

        // 수정하고자 하는 카테고리를 찾지 못할 때
        if(!result.isPresent()){
            throw new BoardCategoryException(ErrorCode.BOARD_CATEGORY_NOT_EXISTS, String.format("Board Category [ idx : %s ] is not exists.", boardCategoryIdx));
        }

        if(result.isPresent()) {
            BoardCategory boardCategory = result.get();
            boardCategory.setUpdatedAt(LocalDateTime.now());
            boardCategory.setCategoryName(patchUpdateBoardCategoryReq.getCategoryName());

            boardCategoryRepository.save(boardCategory);

            PatchUpdateBoardCategoryRes patchUpdateBoardCategoryRes = PatchUpdateBoardCategoryRes.builder()
                    .idx(boardCategory.getIdx())
                    .categoryName(patchUpdateBoardCategoryReq.getCategoryName())
                    .build();
            BaseRes baseRes = BaseRes.builder()
                    .isSuccess(true)
                    .message("게시판 카테고리 수정 성공")
                    .result(patchUpdateBoardCategoryRes)
                    .build();

            return baseRes;
        }

        return null;
    }


    //삭제
    @Transactional(readOnly = false)
    public BaseRes deleteBoardCategory(Integer boardCategoryIdx) {
        Optional<BoardCategory> result = boardCategoryRepository.findById(boardCategoryIdx);

        // 삭제하고자 하는 카테고리를 찾지 못할 때
        if(!result.isPresent()){
            throw new BoardCategoryException(ErrorCode.BOARD_CATEGORY_NOT_EXISTS, String.format("Board Category [ idx : %s ] is not exists.", boardCategoryIdx));

        }
            BoardCategory boardCategory = result.get();
            List<Board> boardList = boardCategory.getBoardList();

            for (Board board : boardList) {
                board.setBoardCategory(null);
            }

            boardRepository.saveAll(boardList);
            boardCategoryRepository.deleteById(boardCategoryIdx);

            BaseRes baseRes = BaseRes.builder()
                    .isSuccess(true)
                    .message("게시판 카테고리 삭제 성공")
                    .result("게시판 카테고리 및 관련 게시글의 카테고리 정보가 삭제되었습니다.")
                    .build();

            return baseRes;
    }
}
