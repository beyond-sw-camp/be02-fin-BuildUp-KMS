package com.example.bootshelf.boardsvc.boardscrap.repository.querydsl;

import com.example.bootshelf.boardsvc.board.model.entity.Board;
import com.example.bootshelf.boardsvc.board.model.entity.QBoard;
import com.example.bootshelf.boardsvc.boardcategory.model.entity.QBoardCategory;
import com.example.bootshelf.boardsvc.boardscrap.model.entity.BoardScrap;
import com.example.bootshelf.boardsvc.boardscrap.model.entity.QBoardScrap;
import com.example.bootshelf.user.model.entity.User;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.ArrayList;
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

    @Override
    public Page<BoardScrap> findBoardScrapListByCategory(User user, Integer boardCategoryIdx, Integer sortIdx, Pageable pageable) {
        QBoardScrap boardScrap = new QBoardScrap("boardScrap");

        OrderSpecifier[] orderSpecifiers = createOrderSpecifier(sortIdx, boardScrap);
        JPQLQuery<BoardScrap> result = from(boardScrap)
                .leftJoin(boardScrap.board.boardCategory)
                .where(boardScrap.user.idx.eq(user.getIdx()).and(boardScrap.status.eq(true)).and(boardScrap.board.boardCategory.idx.eq(boardCategoryIdx)))
                .orderBy(orderSpecifiers);

        JPQLQuery<BoardScrap> pageableQuery = getQuerydsl().applyPagination(pageable, result);
        List<BoardScrap> boardScrapList = pageableQuery.fetch();

        return new PageImpl<>(boardScrapList, pageable, pageableQuery.fetchCount());
    }


    private OrderSpecifier[] createOrderSpecifier(Integer sortIdx, QBoardScrap boardScrap) {
        List<OrderSpecifier> orderSpecifiers = new ArrayList<>();

        switch (sortIdx) {
            case 1:
                orderSpecifiers.add(boardScrap.board.updatedAt.desc());
                break;
            case 2:
                orderSpecifiers.add(boardScrap.board.upCnt.desc());
                break;
            case 3:
                orderSpecifiers.add(boardScrap.board.viewCnt.desc());
                break;
            case 4:
                orderSpecifiers.add(boardScrap.board.scrapCnt.desc());
                break;
            case 5:
                orderSpecifiers.add(boardScrap.board.commentCnt.desc());
                break;
            default:
                orderSpecifiers.add(boardScrap.board.updatedAt.desc());
        }
        return orderSpecifiers.toArray(new OrderSpecifier[0]);
    }
}
