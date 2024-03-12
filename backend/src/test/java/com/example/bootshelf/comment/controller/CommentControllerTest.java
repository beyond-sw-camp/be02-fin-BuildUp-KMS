package com.example.bootshelf.comment.controller;

import com.example.bootshelf.comment.repository.CommentRepository;
import com.example.bootshelf.comment.service.CommentService;
import com.example.bootshelf.reviewscrap.controller.ReviewScrapController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(CommentController.class)
@ContextConfiguration(classes = {CommentController.class})
public class CommentControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    private CommentService commentService;

    @Test
    void commentController_create_success() throws Exception{
        // 테스트에 사용할 더미 데이터 생성
        Integer reviewIdx = 1;
        PostCreateCommentReq commentCreateReq = new PostCreateCommentReq();
        // commentCreateReq 설정...

        // MockMvc를 사용하여 API 엔드포인트 호출
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/your-endpoint/{reviewIdx}/comment/create", reviewIdx)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(commentCreateReq)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                // 여기서 응답의 추가적인 검증을 수행할 수 있습니다.
                // 예를 들어, JSON 응답의 특정 필드 검증 등...
                .andReturn();
    }


}
