package com.example.bootshelf.boardscrap.repository.querydsl;

import com.example.bootshelf.boardscrap.model.entity.BoardScrap;
import com.example.bootshelf.boardscrap.model.entity.QBoardScrap;
import com.example.bootshelf.user.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import java.util.stream.Collectors;

public class BoardScrapRepositoryCustomImpl extends QuerydslRepositorySupport implements BoardScrapRepositoryCustom {
    public BoardScrapRepositoryCustomImpl() {
        super(BoardScrap.class);
    }

    @Override
    public Page<BoardScrap> findByUser(User user, Pageable pageable) {
        QBoardScrap boardScrap = new QBoardScrap("boardScrap");

        List<BoardScrap> result = from(boardScrap)
                .leftJoin(boardScrap.board).fetchJoin()
                .leftJoin(boardScrap.user).fetchJoin()
                .where(boardScrap.status.eq(true))
                .distinct()
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch().stream().collect(Collectors.toList());


        return new PageImpl<>(result, pageable, pageable.getPageSize());
    }

    @Override
    public BoardScrap findByUserIdxAndBoardIdx(Integer userIdx, Integer boardIdx) {
        QBoardScrap boardScrap = new QBoardScrap("boardScrap");

        BoardScrap result = from(boardScrap)
                .leftJoin(boardScrap.user).fetchJoin()
                .leftJoin(boardScrap.board).fetchJoin()
                .where(boardScrap.user.idx.eq(userIdx).and(boardScrap.board.idx.eq(boardIdx)))
                .fetchOne();

        return result;
    }


}
