package com.example.bootshelf.application.port.input;

import com.example.bootshelf.common.BaseRes;
import org.springframework.data.domain.Pageable;

public interface SearchTotalReviewUseCase {

    BaseRes searchTotalReview(Integer selectedDropdownValue, String title, Pageable pageable);
}
