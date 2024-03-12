package com.example.bootshelf.tag.model.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class PostCreateTagRes {
    private Integer idx;
    private String tagName;
}
