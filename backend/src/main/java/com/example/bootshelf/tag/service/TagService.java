package com.example.bootshelf.tag.service;
import com.amazonaws.services.kms.model.NotFoundException;
import com.example.bootshelf.common.BaseRes;
import com.example.bootshelf.tag.model.entity.Tag;
import com.example.bootshelf.tag.model.response.PostCreateTagRes;
import com.example.bootshelf.tag.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import javax.swing.text.html.Option;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class TagService {
    private final TagRepository tagRepository;

    public Tag saveTag(String tagName) {
        Optional<Tag> result = tagRepository.findByTagName(tagName);
        if (!result.isPresent()) {
            Tag tag = Tag.builder()
                    .tagName(tagName)
                    .status(true)
                    .build();

            tag = tagRepository.save(tag);

            return tag;
//            PostCreateTagRes res = PostCreateTagRes.builder()
//                    .tagName(tag.getTagName())
//                    .build();
//
//            BaseRes baseRes = BaseRes.builder()
//                    .isSuccess(true)
//                    .message("태그 생성 성공")
//                    .result(res)
//                    .build();

//            return baseRes;
        }
        throw new NotFoundException("해당 이름의 태그가 이미 존재합니다.");
    }
}
