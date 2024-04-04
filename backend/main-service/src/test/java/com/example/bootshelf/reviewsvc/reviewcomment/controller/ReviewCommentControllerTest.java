package com.example.bootshelf.reviewsvc.reviewcomment.controller;

import com.example.bootshelf.common.BaseRes;
import com.example.bootshelf.common.error.ErrorCode;
import com.example.bootshelf.common.error.entityexception.BoardCommentException;
import com.example.bootshelf.reviewsvc.reviewcomment.model.request.PostCreateReviewCommentReq;
import com.example.bootshelf.reviewsvc.reviewcomment.model.request.PostCreateReviewReplyReq;
import com.example.bootshelf.reviewsvc.reviewcomment.model.response.PostCreateReviewCommentRes;
import com.example.bootshelf.reviewsvc.reviewcomment.model.response.PostCreateReviewReplyRes;
import com.example.bootshelf.reviewsvc.reviewcomment.service.ReviewCommentService;
import com.example.bootshelf.user.controller.mock.WithCustomMockUser;
import com.example.bootshelf.user.model.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(ReviewCommentController.class)
@AutoConfigureMockMvc
@DisplayName("ReviewCommentController 테스트")
class ReviewCommentControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private ReviewCommentService reviewCommentService;


    @BeforeEach
    public void setup() {
        // 테스트 전 실행
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    @DisplayName("후기글 댓글 등록 성공")
    @WithCustomMockUser
    void reviewCommentController_create_success() throws Exception {

        PostCreateReviewCommentRes postCreateReviewCommentRes = PostCreateReviewCommentRes.builder()
                .idx(1)
                .reviewCommentContent("후기글 댓글 테스트")
                .build();

        BaseRes baseRes = BaseRes.builder()
                .isSuccess(true)
                .message("댓글 등록 성공")
                .result(postCreateReviewCommentRes)
                .build();

        PostCreateReviewCommentReq postCreateReviewCommentReq = PostCreateReviewCommentReq.builder()
                .reviewCommentContent("후기글 댓글 테스트")
                .build();

        ObjectMapper objectMapper = new ObjectMapper();

        given(reviewCommentService.createReviewComment(any(User.class), any(Integer.class), any(PostCreateReviewCommentReq.class))).willReturn(baseRes);

        mockMvc.perform(post("/review/1/comment/create")
                .content(objectMapper.writeValueAsBytes(postCreateReviewCommentReq))
                .contentType(MediaType.APPLICATION_JSON)
                .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isSuccess").value(true))
                .andExpect(jsonPath("$.message").value("댓글 등록 성공"))
                .andDo(print());
    }

    @Test
    @DisplayName("후기글 댓글 등록 실패 - 댓글 내용이 작성되지 않았을 때")
    @WithCustomMockUser
    void reviewCommentController_create_fail_CommentContentIsEmpty() throws Exception {
        // Given
        ObjectMapper objectMapper = new ObjectMapper();

        PostCreateReviewCommentReq postCreateReviewCommentReq = PostCreateReviewCommentReq.builder()
                .reviewCommentContent(null)
                .build();

        given(reviewCommentService.createReviewComment(any(User.class), any(Integer.class), any(PostCreateReviewCommentReq.class)))
                .willThrow(new BoardCommentException(ErrorCode.INVALID_INPUT_VALUE, "Review Comment Content is empty."));
        // When
        mockMvc.perform(post("/review/1/comment/create")
                        .content(objectMapper.writeValueAsBytes(postCreateReviewCommentReq))
                        // 객체를 json 파일로 변환해준다.
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf()))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("COMMON-001"))
                .andDo(print());
    }

    @Test
    @DisplayName("후기글 대댓글 등록 성공")
    @WithCustomMockUser
    void reviewCommentController_replyCreate_success() throws Exception {

        PostCreateReviewReplyRes postCreateReviewReplyRes = PostCreateReviewReplyRes.builder()
                .parentIdx(1)
                .reviewCommentContent("후기글 대댓글 테스트")
                .build();

        BaseRes baseRes = BaseRes.builder()
                .isSuccess(true)
                .message("댓글 등록 성공")
                .result(postCreateReviewReplyRes)
                .build();

        PostCreateReviewReplyReq postCreateReviewReplyReq = PostCreateReviewReplyReq.builder()
                .reviewReplyContent("후기글 대댓글 테스트")
                .build();

        ObjectMapper objectMapper = new ObjectMapper();

        given(reviewCommentService.createReviewReply(any(User.class), any(Integer.class), any(Integer.class), any(PostCreateReviewReplyReq.class))).willReturn(baseRes);

        mockMvc.perform(post("/review/1/comment/create/1")
                        .content(objectMapper.writeValueAsBytes(postCreateReviewReplyReq))
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isSuccess").value(true))
                .andExpect(jsonPath("$.message").value("댓글 등록 성공"))
                .andDo(print());
    }


}