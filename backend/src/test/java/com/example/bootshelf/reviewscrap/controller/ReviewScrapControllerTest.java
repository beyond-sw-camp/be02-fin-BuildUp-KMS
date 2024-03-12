package com.example.bootshelf.reviewscrap.controller;

import com.example.bootshelf.reviewscrap.service.ReviewScrapService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(ReviewScrapController.class)
@ContextConfiguration(classes = {ReviewScrapController.class})
class ReviewScrapControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    private ReviewScrapService reviewScrapService;

    @Test
    void reviewScrapController_create_success() throws Exception {
        Map<String, Long> result = new HashMap<>();
        result.put("idx", 1L);
    }
}