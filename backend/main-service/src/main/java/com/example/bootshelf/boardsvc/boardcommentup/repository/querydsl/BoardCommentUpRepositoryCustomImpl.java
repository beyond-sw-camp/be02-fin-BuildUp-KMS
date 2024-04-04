package com.example.bootshelf.boardsvc.boardcommentup.repository.querydsl;

import com.example.bootshelf.boardsvc.boardcommentup.model.entity.BoardCommentUp;
import com.example.bootshelf.boardsvc.boardcommentup.model.entity.QBoardCommentUp;
import com.example.bootshelf.user.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import java.util.stream.Collectors;

public class BoardCommentUpRepositoryCustomImpl extends QuerydslRepositorySupport implements BoardCommentUpRepositoryCustom {
    public BoardCommentUpRepositoryCustomImpl() {
        super(BoardCommentUp.class);
    }

    @Override
    public Page<BoardCommentUp> findByUser(User user, Pageable pageable) {
        QBoardCommentUp boardCommentUp = new QBoardCommentUp("boardCommentUp");

        List<BoardCommentUp> result = from(boardCommentUp)
                .leftJoin(boardCommentUp.user).fetchJoin()
                .leftJoin(boardCommentUp.boardComment).fetchJoin()
                .where(boardCommentUp.status.eq(true))
                .distinct()
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch().stream().collect(Collectors.toList());

        return new PageImpl<>(result, pageable, pageable.getPageSize());
    }

    @Override
    public BoardCommentUp findByUserIdxAndBoardCommentIdx(Integer userIdx, Integer boardCommentIdx) {
        QBoardCommentUp boardCommentUp = new QBoardCommentUp("boardCommentUp");

        BoardCommentUp result = from(boardCommentUp)
                .leftJoin(boardCommentUp.user).fetchJoin()
                .leftJoin(boardCommentUp.boardComment).fetchJoin()
                .where(boardCommentUp.user.idx.eq(userIdx).and(boardCommentUp.boardComment.idx.eq(boardCommentIdx)))
                .fetchOne();

        return result;
    }
}
