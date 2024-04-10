package com.example.bootshelf.application.port.input;

import com.example.bootshelf.common.BaseRes;

import java.util.List;

public interface EsReviewUseCase {
    BaseRes esSearchReview(Integer selectedDropdownValue, Integer sortType, String title, int size, List<Object> searchAfterStr);
}
