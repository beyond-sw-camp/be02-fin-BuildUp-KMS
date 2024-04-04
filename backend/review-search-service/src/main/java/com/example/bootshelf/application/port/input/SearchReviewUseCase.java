package com.example.bootshelf.application.port.input;

import com.example.bootshelf.common.BaseRes;
import org.springframework.data.domain.Pageable;

public interface SearchReviewUseCase {

    BaseRes titleContentSearch(Integer categoryIdx, Integer sortType, String title, Pageable pageable);
}
