package com.example.bootshelf.tag.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetListTagRes {
    private Integer idx;
    private String tagName;
}
