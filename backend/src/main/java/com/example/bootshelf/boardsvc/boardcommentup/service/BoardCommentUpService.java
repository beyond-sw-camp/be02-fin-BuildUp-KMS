package com.example.bootshelf.boardsvc.boardcommentup.service;

import com.example.bootshelf.boardsvc.board.model.entity.Board;
import com.example.bootshelf.boardsvc.boardcomment.model.entity.BoardComment;
import com.example.bootshelf.boardsvc.boardcomment.repository.BoardCommentRepository;
import com.example.bootshelf.boardsvc.boardcommentup.model.entity.BoardCommentUp;
import com.example.bootshelf.boardsvc.boardcommentup.model.request.PostCreateBoardCommentUpReq;
import com.example.bootshelf.boardsvc.boardcommentup.model.response.GetFindBoardCommentUpRes;
import com.example.bootshelf.boardsvc.boardcommentup.model.response.PostCreateBoardCommentUpRes;
import com.example.bootshelf.boardsvc.boardcommentup.repository.BoardCommentUpRepository;
import com.example.bootshelf.boardsvc.boardup.model.entity.BoardUp;
import com.example.bootshelf.boardsvc.boardup.model.response.GetFindBoardUpRes;
import com.example.bootshelf.boardsvc.boardup.model.response.PostCreateBoardUpRes;
import com.example.bootshelf.common.BaseRes;
import com.example.bootshelf.common.error.ErrorCode;
import com.example.bootshelf.common.error.entityexception.BoardCommentException;
import com.example.bootshelf.common.error.entityexception.BoardCommentUpException;
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
public class BoardCommentUpService {
    private final BoardCommentUpRepository boardCommentUpRepository;
    private final BoardCommentRepository boardCommentRepository;

    @Transactional
    public BaseRes createBoardCommentUp(User user, PostCreateBoardCommentUpReq req) {
        BoardComment boardComment = boardCommentRepository.findByIdx(req.getBoardCommentIdx())
                .orElseThrow(() -> new BoardCommentException(ErrorCode.BOARD_COMMENT_NOT_EXISTS, String.format("Board Comment [ %s ] is not exists.", req.getBoardCommentIdx())));

        BoardCommentUp boardCommentUpResult = boardCommentUpRepository.findByUserIdxAndBoardCommentIdx(user.getIdx(), req.getBoardCommentIdx());
        if (boardCommentUpResult != null) {
            if (boardCommentUpResult.getStatus().equals(true))
                throw new BoardCommentUpException(ErrorCode.DUPLICATED_BOARD_COMMENT_UP, String.format("Board Comment [ %s ] is already recommended.", req.getBoardCommentIdx()));
            else {
                boardCommentUpResult.setStatus(true);
                boardCommentUpRepository.save(boardCommentUpResult);

                boardComment.increaseUpCnt();
                boardCommentRepository.save(boardComment);

                PostCreateBoardCommentUpRes res = PostCreateBoardCommentUpRes.toDto(boardCommentUpResult);

                return BaseRes.builder()
                        .isSuccess(true)
                        .message("게시판 댓글 추천 수정 성공")
                        .result(res)
                        .build();
            }
        }

        BoardCommentUp boardCommentUp = BoardCommentUp.toEntity(user, req);
        boardCommentUp = boardCommentUpRepository.save(boardCommentUp);

        PostCreateBoardCommentUpRes res = PostCreateBoardCommentUpRes.toDto(boardCommentUp);

        return BaseRes.builder()
                .isSuccess(true)
                .message("게시판 댓글 추천 등록 성공")
                .result(res)
                .build();
    }

    @Transactional(readOnly = true)
    public BaseRes findBoardCommentUpList(User user, Pageable pageable) {
        Page<BoardCommentUp> boardCommentUpList = boardCommentUpRepository.findByUser(user, pageable);
        if (boardCommentUpList.isEmpty())
            throw new BoardCommentUpException(ErrorCode.BOARD_COMMENT_UP_IS_EMPTY, "추천한 게시판 댓글이 존재하지 않습니다.");

        List<GetFindBoardCommentUpRes> resultList = new ArrayList<>();
        for (BoardCommentUp boardCommentUp : boardCommentUpList.getContent()) {
            GetFindBoardCommentUpRes res = GetFindBoardCommentUpRes.builder()
                    .boardCommentUpIdx(boardCommentUp.getIdx())
                    .boardCommentIdx(boardCommentUp.getBoardComment().getIdx())
                    .userIdx(boardCommentUp.getUser().getIdx())
                    .build();

            resultList.add(res);
        }

        return BaseRes.builder()
                .isSuccess(true)
                .message("게시판 댓글 추천 목록 조회 성공")
                .result(resultList)
                .build();
    }


    public BaseRes checkBoardCommentUp(User user, Integer boardCommentIdx) {
        BoardCommentUp boardCommentUpResult = boardCommentUpRepository.findByUserIdxAndBoardCommentIdx(user.getIdx(), boardCommentIdx);
        if (boardCommentUpResult != null) {
            return BaseRes.builder()
                    .isSuccess(true)
                    .message("게시판 댓글 추천 여부 확인 성공")
                    .result(true)
                    .build();
        } else {
            return BaseRes.builder()
                    .isSuccess(true)
                    .message("게시판 댓글 추천 여부 확인 성공")
                    .result(false)
                    .build();
        }
    }

    @Transactional
    public BaseRes deleteBoardCommentUp(User user, Integer boardCommentUpIdx) {
        Optional<BoardCommentUp> result = boardCommentUpRepository.findByIdx(boardCommentUpIdx);
        if (result.isPresent()) {
            BoardCommentUp boardCommentUp = result.get();
            BoardComment boardComment = boardCommentUp.getBoardComment();

            if (boardCommentUp.getUser().getIdx().equals(user.getIdx())) {
                boardCommentUp.setStatus(false);
                boardCommentUpRepository.save(boardCommentUp);

                boardComment.decreaseUpCnt();
                boardCommentRepository.save(boardComment);

                return BaseRes.builder()
                        .isSuccess(true)
                        .message("게시글 댓글 추천 삭제 성공")
                        .build();
            }
            throw new BoardUpException(ErrorCode.UNAUTHORIZED_BOARD_COMMENT_UP,
                    String.format("Current user is  [ %s ] .\n " +
                            "But User who recommended board comment is [ %s ].", user.getIdx(), boardCommentUp.getUser().getIdx()));
        }
        throw new BoardCommentUpException(ErrorCode.BOARD_COMMENT_UP_NOT_EXISTS, String.format("Board comment recommend idx [ %s ] is not exists.", boardCommentUpIdx));
    }
}
