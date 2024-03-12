package com.example.bootshelf.boardscrap.service;

import com.example.bootshelf.board.exception.BoardException;
import com.example.bootshelf.board.model.entity.Board;
import com.example.bootshelf.board.repository.BoardRepository;
import com.example.bootshelf.boardscrap.exception.BoardScrapException;
import com.example.bootshelf.boardscrap.model.entity.BoardScrap;
import com.example.bootshelf.boardscrap.model.request.PostCreateBoardScrapReq;
import com.example.bootshelf.boardscrap.model.response.GetFindBoardScrapRes;
import com.example.bootshelf.boardscrap.model.response.PostCreateBoardScrapRes;
import com.example.bootshelf.boardscrap.repository.BoardScrapRepository;
import com.example.bootshelf.common.BaseRes;
import com.example.bootshelf.common.error.ErrorCode;
import com.example.bootshelf.user.model.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardScrapService {
    private final BoardScrapRepository boardScrapRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public BaseRes createBoardScrap(User user, PostCreateBoardScrapReq req) {
        Board board = boardRepository.findByIdx(req.getBoardIdx())
                .orElseThrow(() -> new BoardException(ErrorCode.BOARD_NOT_EXISTS, String.format("Board [ %s ] is not exists.", req.getBoardIdx())));

        BoardScrap boardScrapResult = boardScrapRepository.findByUserIdxAndBoardIdx(user.getIdx(), req.getBoardIdx());
        if (boardScrapResult != null) {
            if (boardScrapResult.getStatus().equals(true))
                throw new BoardScrapException(ErrorCode.DUPLICATED_BOARD_SCRAP, String.format("Board [ %s ] is already scrapped.", req.getBoardIdx()));
            else {
                boardScrapResult.setStatus(true);
                boardScrapRepository.save(boardScrapResult);

                board.increaseScrapCnt();
                boardRepository.save(board);
            }
        }

        BoardScrap boardScrap = BoardScrap.toEntity(user, req);

        boardScrap = boardScrapRepository.save(boardScrap);

        PostCreateBoardScrapRes res = PostCreateBoardScrapRes.toDto(boardScrap);

        return BaseRes.builder()
                .isSuccess(true)
                .message("게시판 게시글 스크랩 등록 성공")
                .result(res)
                .build();
    }

    @Transactional(readOnly = true)
    public BaseRes findBoardScrapList(User user, Pageable pageable) {
        Page<BoardScrap> boardScrapList = boardScrapRepository.findByUser(user, pageable);
        if (boardScrapList.isEmpty())
            throw new BoardScrapException(ErrorCode.BOARD_SCRAP_IS_EMPTY, "");

        List<GetFindBoardScrapRes> resultList = new ArrayList<>();
        for (BoardScrap boardScrap : boardScrapList.getContent()) {
            GetFindBoardScrapRes res = GetFindBoardScrapRes.builder()
                    .boardScrapIdx(boardScrap.getIdx())
                    .boardIdx(boardScrap.getBoard().getIdx())
                    .categoryName(boardScrap.getBoard().getBoardCategory().getCategoryName())
                    .boardTitle(boardScrap.getBoard().getBoardTitle())
                    .createdAt(boardScrap.getCreatedAt())
                    .build();

            resultList.add(res);
        }

        return BaseRes.builder()
                .isSuccess(true)
                .message("게시글 스크랩 목록 조회 성공")
                .result(resultList)
                .build();
    }

    public BaseRes deleteBoardScrap(User user, Integer boardScrapIdx) throws Exception {
        Optional<BoardScrap> result = boardScrapRepository.findByIdx(boardScrapIdx);
        if (result.isPresent()) {
            BoardScrap boardScrap = result.get();

            if (boardScrap.getUser().getIdx().equals(user.getIdx())) {
                boardScrap.setStatus(false);
                boardScrapRepository.save(boardScrap);

                return BaseRes.builder()
                        .isSuccess(true)
                        .message("게시글 스크랩 삭제 성공")
                        .build();
            }
            throw new BoardScrapException(ErrorCode.UNAUTHORIZED_BOARD_SCRAP,
                    String.format("User [ %s ] is already scrapped.\n " +
                            "User who scrapped board is [ %s ].", user.getIdx(), boardScrap.getUser().getIdx()));
        }
        throw new BoardScrapException(ErrorCode.BOARD_SCRAP_NOT_EXISTS, String.format("Board scrap idx [ %s ] is not exists.", boardScrapIdx));
    }
}