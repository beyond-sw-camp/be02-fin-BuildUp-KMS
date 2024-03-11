package com.example.bootshelf.review.controller;

import com.example.bootshelf.common.BaseRes;
import com.example.bootshelf.review.model.request.PostCreateReviewReq;
import com.example.bootshelf.review.service.ReviewService;
import com.example.bootshelf.user.model.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
@CrossOrigin("*")
@Api(value = "후기 컨트롤러 v1", tags = "후기 API")
public class ReviewController {

    private final ReviewService reviewService;

    @ApiOperation(value = "후기 등록", response = BaseRes.class, notes = "인증회원은 수료한 과정 또는 강사에 대한 후기를 등록할 수 있다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK ( 요청 성공 )", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = BaseRes.class))})})
    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public ResponseEntity createReview(
            @RequestPart(value = "review") @Valid PostCreateReviewReq postCreateReviewReq,
            @RequestPart(value = "reviewImage") MultipartFile[] reviewImages
    ) {
        User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        BaseRes baseRes = reviewService.createReview(user, postCreateReviewReq, reviewImages);

        return ResponseEntity.ok().body(baseRes);
    }
}
