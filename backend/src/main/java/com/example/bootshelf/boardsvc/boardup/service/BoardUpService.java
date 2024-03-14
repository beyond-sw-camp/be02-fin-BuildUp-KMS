package com.example.bootshelf.boardsvc.boardup.service;

import com.example.bootshelf.boardsvc.board.model.entity.Board;
import com.example.bootshelf.boardsvc.board.repository.BoardRepository;
import com.example.bootshelf.boardsvc.boardcommentup.model.response.PostCreateBoardCommentUpRes;
import com.example.bootshelf.boardsvc.boardup.model.entity.BoardUp;
import com.example.bootshelf.boardsvc.boardup.model.request.PostCreateBoardUpReq;
import com.example.bootshelf.boardsvc.boardup.model.response.GetFindBoardUpRes;
import com.example.bootshelf.boardsvc.boardup.model.response.PostCreateBoardUpRes;
import com.example.bootshelf.boardsvc.boardup.repository.BoardUpRepository;
import com.example.bootshelf.common.BaseRes;
import com.example.bootshelf.common.error.ErrorCode;
import com.example.bootshelf.common.error.entityexception.BoardException;
import com.example.bootshelf.common.error.entityexception.BoardUpException;
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
public class BoardUpService {
    private final BoardUpRepository boardUpRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public BaseRes createBoardUp(User user, PostCreateBoardUpReq req) {
        Board board = boardRepository.findByIdx(req.getBoardIdx())
                .orElseThrow(() -> new BoardException(ErrorCode.BOARD_NOT_EXISTS, String.format("Board [ %s ] is not exists.", req.getBoardIdx())));

        BoardUp boardUpResult = boardUpRepository.findByUserIdxAndBoardIdx(user.getIdx(), req.getBoardIdx());
        if (boardUpResult != null) {
            if (boardUpResult.getStatus().equals(true))
                throw new BoardUpException(ErrorCode.DUPLICATED_BOARD_UP, String.format("Board [ %s ] is already recommended.", req.getBoardIdx()));
            else {
                boardUpResult.setStatus(true);
                boardUpRepository.save(boardUpResult);

                board.increaseUpCnt();
                boardRepository.save(board);

                PostCreateBoardUpRes res = PostCreateBoardUpRes.toDto(boardUpResult);

                return BaseRes.builder()
                        .isSuccess(true)
                        .message("게시판 게시글 추천 등록 성공")
                        .result(res)
                        .build();
            }
        }

        BoardUp boardUp = BoardUp.toEntity(user, req);

        boardUp = boardUpRepository.save(boardUp);

        PostCreateBoardUpRes res = PostCreateBoardUpRes.toDto(boardUp);

        return BaseRes.builder()
                .isSuccess(true)
                .message("게시판 게시글 추천 등록 성공")
                .result(res)
                .build();
    }

    @Transactional(readOnly = true)
    public BaseRes findBoardUpList(User user, Pageable pageable) {
        Page<BoardUp> boardUpList = boardUpRepository.findByUser(user, pageable);
        if (boardUpList.isEmpty())
            throw new BoardUpException(ErrorCode.BOARD_UP_IS_EMPTY, "추천한 게시글이 존재하지 않습니다.");

        List<GetFindBoardUpRes> resultList = new ArrayList<>();
        for (BoardUp boardUp : boardUpList.getContent()) {
            GetFindBoardUpRes res = GetFindBoardUpRes.builder()
                    .boardUpIdx(boardUp.getIdx())
                    .boardIdx(boardUp.getBoard().getIdx())
                    .userIdx(boardUp.getUser().getIdx())
                    .build();

            resultList.add(res);
        }

        return BaseRes.builder()
                .isSuccess(true)
                .message("게시글 스크랩 목록 조회 성공")
                .result(resultList)
                .build();
    }


    public BaseRes checkBoardUp(User user, Integer boardIdx) {
        BoardUp boardUpResult = boardUpRepository.findByUserIdxAndBoardIdx(user.getIdx(), boardIdx);
        if (boardUpResult != null) {
            return BaseRes.builder()
                    .isSuccess(true)
                    .message("게시물 추천 여부 확인 성공")
                    .result(true)
                    .build();
        } else {
            return BaseRes.builder()
                    .isSuccess(true)
                    .message("게시물 추천 여부 확인 성공")
                    .result(false)
                    .build();
        }
    }

    @Transactional
    public BaseRes deleteBoardUp(User user, Integer boardUpIdx) {
        Optional<BoardUp> result = boardUpRepository.findByIdx(boardUpIdx);
        if (result.isPresent()) {
            BoardUp boardUp = result.get();
            Board board = boardUp.getBoard();

            if (boardUp.getUser().getIdx().equals(user.getIdx())) {
                boardUp.setStatus(false);
                boardUpRepository.save(boardUp);

                board.decreaseUpCnt();
                boardRepository.save(board);

                return BaseRes.builder()
                        .isSuccess(true)
                        .message("게시글 추천 삭제 성공")
                        .build();
            }
            throw new BoardUpException(ErrorCode.UNAUTHORIZED_BOARD_UP,
                    String.format("Current user is  [ %s ] .\n " +
                            "But User who recommended board is [ %s ].", user.getIdx(), boardUp.getUser().getIdx()));
        }
        throw new BoardUpException(ErrorCode.BOARD_UP_NOT_EXISTS, String.format("Board recommend idx [ %s ] is not exists.", boardUpIdx));
    }
}
