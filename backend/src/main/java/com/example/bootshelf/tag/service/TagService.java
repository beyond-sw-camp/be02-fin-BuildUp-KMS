package com.example.bootshelf.tag.service;


import com.example.bootshelf.common.BaseRes;
import com.example.bootshelf.common.error.ErrorCode;
import com.example.bootshelf.common.error.entityexception.TagException;
import com.example.bootshelf.tag.model.entity.Tag;
import com.example.bootshelf.tag.model.request.PatchUpdateTagReq;
import com.example.bootshelf.tag.model.response.PostCreateTagRes;
import com.example.bootshelf.tag.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;

    // [관리자] 태그 생성 또는 [사용자] 태그 생성
    @Transactional(readOnly = false)
    public Tag createTag(String tagName) {
        Optional<Tag> result = tagRepository.findByTagName(tagName);

        if (result.isPresent()) {
            throw new TagException(ErrorCode.DUPLICATED_TAG_NAME, String.format("Tag Name [ %s ] is duplicated.", tagName));
        }

        Tag tag = Tag.builder()
                .tagName(tagName)
                .status(true)
                .build();

        tag = tagRepository.save(tag);

        return tag;
    }

    // [관리자] 태그 목록 조회
    @Transactional(readOnly = true)
    public BaseRes listTag(Pageable pageable) {

        Page<Tag> tagList = tagRepository.findAll(pageable);

        List<PostCreateTagRes> postCreateTagResList = new ArrayList<>();

        for(Tag tag : tagList) {
            PostCreateTagRes postCreateTagRes = PostCreateTagRes.builder()
                    .idx(tag.getIdx())
                    .tagName(tag.getTagName())
                    .build();

            postCreateTagResList.add(postCreateTagRes);
        }

        BaseRes baseRes = BaseRes.builder()
                .isSuccess(true)
                .message("태그 목록 조회 요청 성공")
                .result(postCreateTagResList)
                .build();

        return baseRes;
    }

    // [관리자] 태그 수정
    @Transactional(readOnly = false)
    public BaseRes updateTag(PatchUpdateTagReq patchUpdateTagReq) {

        Optional<Tag> result = tagRepository.findById(patchUpdateTagReq.getTagIdx());

        if(!result.isPresent()) {
            throw new TagException(ErrorCode.TAG_NOT_EXISTS, String.format("Tag Idx [ %s ] is not exists.", patchUpdateTagReq.getTagIdx()));
        }

        Optional<Tag> tagName = tagRepository.findByTagName(patchUpdateTagReq.getTagName());

        if (tagName.isPresent()) {
            throw new TagException(ErrorCode.DUPLICATED_TAG_NAME, String.format("Tag Name [ %s ] is duplicated.", patchUpdateTagReq.getTagName()));
        }

        Tag tag = result.get();
        tag.setTagName(patchUpdateTagReq.getTagName());
        tagRepository.save(tag);

        BaseRes baseRes = BaseRes.builder()
                .isSuccess(true)
                .message("태그 수정 성공")
                .result(patchUpdateTagReq.getTagName())
                .build();

        return baseRes;
    }

    // [관리자] 태그 삭제
    @Transactional(readOnly = false)
    public BaseRes deleteTag(Integer tagIdx) {

        Optional<Tag> result = tagRepository.findById(tagIdx);

        if(!result.isPresent()) {
            throw new TagException(ErrorCode.TAG_NOT_EXISTS, String.format("Tag Idx [ %s ] is not exists.", tagIdx));
        }

        tagRepository.deleteById(tagIdx);

        BaseRes baseRes = BaseRes.builder()
                .isSuccess(true)
                .message("태그 삭제 성공")
                .result("요청 성공")
                .build();

        return baseRes;
    }
}
