package com.example.bootshelf.review.exception;


import com.example.bootshelf.common.error.ErrorCode;
import com.example.bootshelf.common.error.exception.EntityDuplicateException;

public class ReviewTitleDuplicateException extends EntityDuplicateException {

    public ReviewTitleDuplicateException(String reviewTitle) {
        super(ErrorCode.DUPLICATE_REVIEW_TITLE, String.format("Review Title [ %s ] is duplicated.", reviewTitle));
    }
}
