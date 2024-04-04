package com.example.bootshelf.boardsvc.boardtag.repository.querydsl;

import com.example.bootshelf.boardsvc.boardtag.model.entity.BoardTag;
import com.example.bootshelf.boardsvc.boardtag.model.entity.QBoardTag;
import com.example.bootshelf.boardsvc.boardtag.model.response.GetListHotTagRes;
import com.example.bootshelf.tag.model.entity.QTag;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class BoardTagRepositoryCustomImpl extends QuerydslRepositorySupport implements BoardTagRepositoryCustom {
    public BoardTagRepositoryCustomImpl() {
        super(BoardTag.class);
    }

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<GetListHotTagRes> findByHotTagList() {

        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        QBoardTag boardTag = new QBoardTag("boardTag");
        QTag tag = new QTag("tag");

        List<GetListHotTagRes> result = queryFactory
                .select(Projections.constructor(GetListHotTagRes.class,
                        tag.idx, tag.tagName, boardTag.idx.countDistinct()))
                .from(boardTag)
                .leftJoin(boardTag.tag, tag)
                .groupBy(tag.idx)
                .orderBy(boardTag.idx.countDistinct().desc())
                .limit(5)
                .fetch();

        return result;
    }
}
