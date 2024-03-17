package com.example.bootshelf.boardsvc.boardtag.repository.querydsl;

import com.example.bootshelf.boardsvc.boardtag.model.entity.BoardTag;
import com.example.bootshelf.boardsvc.boardtag.model.response.GetListHotTagRes;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardTagRepositoryCustom {

    List<GetListHotTagRes> findByHotTagList();
}
