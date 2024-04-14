package com.example.bootshelf.application.port.input;

import com.example.bootshelf.common.BaseRes;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SearchBoardUseCase {

    BaseRes searchBoard(Integer categoryIdx, Integer sortType, String title, Pageable pageable);

    BaseRes searchAfterBoard(Integer categoryIdx, Integer sortType, String title, int size, List<Object> searchAfterStr);
}
