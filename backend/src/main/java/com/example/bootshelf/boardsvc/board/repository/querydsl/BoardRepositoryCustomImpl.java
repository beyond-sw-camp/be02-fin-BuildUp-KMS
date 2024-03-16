package com.example.bootshelf.boardsvc.board.repository.querydsl;

import com.example.bootshelf.boardsvc.board.model.entity.Board;
import com.example.bootshelf.boardsvc.board.model.entity.QBoard;
import com.example.bootshelf.boardsvc.boardtag.model.entity.QBoardTag;
import com.example.bootshelf.tag.model.entity.QTag;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BoardRepositoryCustomImpl extends QuerydslRepositorySupport implements BoardRepositoryCustom {
    public BoardRepositoryCustomImpl() {
        super(Board.class);
    }

    @Override
    public Page<Board> findMyBoardList(Integer userIdx, Pageable pageable, Integer sortIdx) {
        QBoard board = new QBoard("board");


        OrderSpecifier[] orderSpecifiers = createOrderSpecifier(sortIdx, board);
        List<Board> result = from(board)
                .where(board.user.idx.eq(userIdx).and(board.status.eq(true)))
                .orderBy(orderSpecifiers)
                .distinct()
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch().stream().distinct().collect(Collectors.toList());

        return new PageImpl<>(result, pageable, result.size());
    }

    @Override
    public Page<Board> findMyBoardListByCategory(Integer userIdx, Pageable pageable, Integer categoryIdx, Integer sortIdx) {
        QBoard board = new QBoard("board");

        OrderSpecifier[] orderSpecifiers = createOrderSpecifier(sortIdx, board);
        List<Board> result = from(board)
                .leftJoin(board.boardCategory)
                .where(board.user.idx.eq(userIdx).and(board.status.eq(true)).and(board.boardCategory.idx.eq(categoryIdx)))
                .orderBy(orderSpecifiers)
                .distinct()
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch().stream().distinct().collect(Collectors.toList());

        return new PageImpl<>(result, pageable, result.size());
    }

    @Override
    public Page<Board> findBoardListByCategory(Pageable pageable, Integer categoryIdx, Integer sortIdx) {
        QBoard board = new QBoard("board");

        OrderSpecifier[] orderSpecifiers = createOrderSpecifier(sortIdx, board);
        JPQLQuery<Board> result = from(board)
                .leftJoin(board.boardCategory)
                .where(board.status.eq(true).and(board.boardCategory.idx.eq(categoryIdx)))
                .orderBy(orderSpecifiers);

        JPQLQuery<Board> pageableQuery = getQuerydsl().applyPagination(pageable, result);
        List<Board> boardList = pageableQuery.fetch();

        return new PageImpl<>(boardList, pageable, pageableQuery.fetchCount());
    }

    @Override
    public Page<Board> findBoardListByTag(Pageable pageable, Integer tagIdx, Integer sortIdx) {
        QBoard board = new QBoard("board");
        QBoardTag boardTag = new QBoardTag("boardTag");

        OrderSpecifier[] orderSpecifiers = createOrderSpecifier(sortIdx, board);
        JPQLQuery<Board> result = from(board)
                .leftJoin(boardTag)
                .on(boardTag.board.idx.eq(board.idx))
                .where(board.status.eq(true).and(boardTag.tag.idx.eq(tagIdx)))
                .orderBy(orderSpecifiers);

        JPQLQuery<Board> pageableQuery = getQuerydsl().applyPagination(pageable, result);
        List<Board> boardList = pageableQuery.fetch();

        return new PageImpl<>(boardList, pageable, pageableQuery.fetchCount());
    }

    private OrderSpecifier[] createOrderSpecifier(Integer sortIdx, QBoard board) {
        List<OrderSpecifier> orderSpecifiers = new ArrayList<>();

        switch (sortIdx) {
            case 1:
                orderSpecifiers.add(board.updatedAt.desc());
                break;
            case 2:
                orderSpecifiers.add(board.upCnt.desc());
                break;
            case 3:
                orderSpecifiers.add(board.viewCnt.desc());
                break;
            case 4:
                orderSpecifiers.add(board.scrapCnt.desc());
                break;
            case 5:
                orderSpecifiers.add(board.commentCnt.desc());
                break;
            default:
                orderSpecifiers.add(board.updatedAt.desc());
        }
        return orderSpecifiers.toArray(new OrderSpecifier[0]);
    }

    @Override
    public Page<Board> searchBoardListByQueryAndCategory(Pageable pageable, Integer boardCategoryIdx, String query, Integer sortIdx) {
        QBoard qBoard = QBoard.board;

        // 검색 조건
        BooleanExpression searchCondition = titleContains(query).or(contentContains(query));
        OrderSpecifier[] orderSpecifiers = createOrderSpecifier(sortIdx, qBoard);

        // 조회 쿼리 생성 및 페이징 처리
        JPQLQuery<Board> querySQL = from(qBoard)
                .leftJoin(qBoard.user).fetchJoin()
                .leftJoin(qBoard.boardCategory).fetchJoin()
                .where(searchCondition.and(qBoard.boardCategory.idx.eq(boardCategoryIdx)))
                .orderBy(orderSpecifiers);

        // pagination 적용
        JPQLQuery<Board> pageableQuery = getQuerydsl().applyPagination(pageable, querySQL);
        List<Board> boardList = pageableQuery.fetch();

        return new PageImpl<>(boardList, pageable, pageableQuery.fetchCount());
    }

    @Override
    public Page<Board> searchBoardListByQuery(Pageable pageable, String query, Integer searchType) {
        QBoard qBoard = QBoard.board;

        // 검색 조건
        BooleanExpression searchCondition = searchType == 1 ? titleContains(query)
                : titleContains(query).or(contentContains(query));

        // 조회 쿼리 생성 및 페이징 처리
        JPQLQuery<Board> querySQL = from(qBoard)
                .leftJoin(qBoard.user).fetchJoin()
                .leftJoin(qBoard.boardCategory).fetchJoin()
                .where(searchCondition)
                .orderBy(qBoard.createdAt.desc());

        // pagination 적용
        JPQLQuery<Board> pageableQuery = getQuerydsl().applyPagination(pageable, querySQL);
        List<Board> boardList = pageableQuery.fetch();

        return new PageImpl<>(boardList, pageable, pageableQuery.fetchCount());
    }

    private BooleanExpression titleContains(String query) {
        if (query == null || query.trim().isEmpty()) return null;
        return QBoard.board.boardTitle.containsIgnoreCase(query);
    }

    private BooleanExpression contentContains(String query) {
        if (query == null || query.trim().isEmpty()) return null;
        return QBoard.board.boardContent.containsIgnoreCase(query);
    }


    /**
     *  게시판 (+ 후기) 검색 api (v2)
     *  -> 페이지네이션 잘 안됨
     */
    @Override
    public Page<Board> searchBoardListByQueryV2(Pageable pageable, String query, Integer searchType) {
        QBoard qBoard = QBoard.board;

        // 검색 조건
        BooleanExpression searchCondition = searchType == 1 ? titleContainsV2(query)
                : titleContainsV2(query).or(contentContainsV2(query)); // Ensure correct method names are used

        // 조회 쿼리 생성 및 페이징 처리
        JPQLQuery<Board> querySQL = from(qBoard)
                .leftJoin(qBoard.user).fetchJoin()
                .leftJoin(qBoard.boardCategory).fetchJoin()
                .where(searchCondition)
                .orderBy(qBoard.createdAt.desc());

        // pagination 적용
        JPQLQuery<Board> pageableQuery = getQuerydsl().applyPagination(pageable, querySQL);
        List<Board> boardList = pageableQuery.fetch();
        long count = pageableQuery.fetchCount(); // Fetch the count of all matching records

        return new PageImpl<>(boardList, pageable, count); // Return a Page<Board> instead of List<Board>
    }

    private BooleanExpression titleContainsV2(String query) {
        if (query == null || query.trim().isEmpty()) return null;
        return QBoard.board.boardTitle.containsIgnoreCase(query);
    }

    private BooleanExpression contentContainsV2(String query) {
        if (query == null || query.trim().isEmpty()) return null;
        return QBoard.board.boardContent.containsIgnoreCase(query);
    }
}
