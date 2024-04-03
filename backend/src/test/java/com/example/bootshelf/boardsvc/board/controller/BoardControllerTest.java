package com.example.bootshelf.boardsvc.board.controller;

import com.example.bootshelf.boardsvc.board.model.response.GetBoardListByQueryRes;
import com.example.bootshelf.boardsvc.board.model.response.GetBoardListByQueryResResult;
import com.example.bootshelf.boardsvc.board.service.BoardService;
import com.example.bootshelf.boardsvc.boardimage.service.BoardImageService;
import com.example.bootshelf.common.BaseRes;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(BoardController.class)
class BoardControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private BoardService boardService;

    @MockBean
    private BoardImageService boardImageService;


    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .addFilter(new CharacterEncodingFilter("UTF-8", true))
                .build();
    }

    @Test
    public void 게시판_검색_결과_조회() throws Exception {
        // given
        GetBoardListByQueryRes getBoardListByQueryRes = GetBoardListByQueryRes.builder()
                .idx(1)
                .title("스프링 테스트 코드")
                .content("스프링 테스트 코드는 영한이형에게")
                .nickName("갓영한")
                .createdAt(LocalDateTime.now().toString())
                .updatedAt(LocalDateTime.now().toString())
                .viewCnt(100)
                .commentCnt(10)
                .upCnt(50)
                .build();


        List<GetBoardListByQueryRes> content = Arrays.asList(getBoardListByQueryRes);

        GetBoardListByQueryResResult result = new GetBoardListByQueryResResult(1L, 1, content);
        BaseRes baseRes = new BaseRes(true, "게시글 검색 성공", result);

        given(boardService.searchBoardListByQuery(anyString(), anyInt(), any(Pageable.class)))
                .willReturn(baseRes);

        // When & Then
        mvc.perform(get("/board/search")
                        .param("query", "스프링 테스트 코드")
                        .param("searchType", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isSuccess").value(true))
                .andExpect(jsonPath("$.message").value("게시글 검색 성공"))
                .andExpect(jsonPath("$.result.totalCnt").value(1L))
                .andExpect(jsonPath("$.result.list[0].title").value("스프링 테스트 코드"));
    }
}