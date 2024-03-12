package com.example.bootshelf.boardscrap.service;

import com.example.bootshelf.board.repository.BoardRepository;
import com.example.bootshelf.boardscrap.model.entity.BoardScrap;
import com.example.bootshelf.boardscrap.model.request.PostCreateBoardScrapReq;
import com.example.bootshelf.boardscrap.model.response.GetFindBoardScrapRes;
import com.example.bootshelf.boardscrap.model.response.PostCreateBoardScrapRes;
import com.example.bootshelf.boardscrap.repository.BoardScrapRepository;
import com.example.bootshelf.common.BaseRes;
import com.example.bootshelf.user.model.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardScrapService {
    private final BoardScrapRepository boardScrapRepository;
    private final BoardRepository boardRepository;

    public BaseRes createBoardScrap(User user, PostCreateBoardScrapReq req) throws Exception {
        boardRepository.findByIdx(req.getBoardIdx())
                .orElseThrow(() -> new Exception("해당 게시글이 존재하지 않습니다." + req.getBoardIdx()));

        BoardScrap boardScrapResult = boardScrapRepository.findByUserIdxAndBoardIdx(user.getIdx(), req.getBoardIdx());
        if (boardScrapResult != null) {
            if (boardScrapResult.getStatus().equals(true))
                throw new Exception("이미 스크랩한 게시글입니다.");
            else {
                boardScrapResult.setStatus(true);
                boardScrapRepository.save(boardScrapResult);
            }
        }

        BoardScrap boardScrap = BoardScrap.toEntity(user, req);

        boardScrap = boardScrapRepository.save(boardScrap);

        PostCreateBoardScrapRes res = PostCreateBoardScrapRes.toDto(boardScrap);

        return BaseRes.builder()
                .isSuccess(true)
                .message("후기글 스크랩 등록 성공")
                .result(res)
                .build();
    }

    public BaseRes findBoardScrapList(User user, Pageable pageable) throws Exception {
        Page<BoardScrap> boardScrapList = boardScrapRepository.findByUser(user, pageable);
        if (boardScrapList.isEmpty())
            throw new Exception("리뷰 스크랩한 내역이 존재하지 않습니다.");

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
                .message("리뷰 스크랩 목록 조회 성공")
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
            throw new Exception("스크랩한 유저와 현재 유저가 일치하지 않습니다.");
        }
        throw new Exception("해당 게시글이 존재하지 않습니다.");
    }
}