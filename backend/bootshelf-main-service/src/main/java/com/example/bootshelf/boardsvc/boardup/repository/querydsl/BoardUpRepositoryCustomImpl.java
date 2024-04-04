package com.example.bootshelf.boardsvc.boardup.repository.querydsl;

import com.example.bootshelf.boardsvc.boardup.model.entity.BoardUp;
import com.example.bootshelf.boardsvc.boardup.model.entity.QBoardUp;
import com.example.bootshelf.user.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import java.util.stream.Collectors;

public class BoardUpRepositoryCustomImpl extends QuerydslRepositorySupport implements BoardUpRepositoryCustom {
    public BoardUpRepositoryCustomImpl() {
        super(BoardUp.class);
    }

    @Override
    public Page<BoardUp> findByUser(User user, Pageable pageable) {
        QBoardUp boardUp = new QBoardUp("boardUp");

        List<BoardUp> result = from(boardUp)
                .leftJoin(boardUp.board).fetchJoin()
                .leftJoin(boardUp.user).fetchJoin()
                .where(boardUp.status.eq(true))
                .distinct()
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch().stream().collect(Collectors.toList());

        return new PageImpl<>(result, pageable, pageable.getPageSize());
    }

    @Override
    public BoardUp findByUserIdxAndBoardIdx(Integer userIdx, Integer boardIdx) {
        QBoardUp boardUp = new QBoardUp("boardUp");

        BoardUp result = from(boardUp)
                .leftJoin(boardUp.user).fetchJoin()
                .leftJoin(boardUp.board).fetchJoin()
                .where(boardUp.user.idx.eq(userIdx).and(boardUp.board.idx.eq(boardIdx)))
                .fetchOne();

        return result;
    }
}
