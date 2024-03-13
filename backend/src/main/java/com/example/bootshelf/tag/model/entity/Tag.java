package com.example.bootshelf.tag.model.entity;

import com.example.bootshelf.boardsvc.boardtag.model.entity.BoardTag;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    @OneToMany(mappedBy = "tag", fetch = FetchType.LAZY)
    private List<BoardTag> boardTagList = new ArrayList<>();

    @Column(nullable = false, length = 45)
    private String tagName;

    @Column(nullable = false)
    private Boolean status;

}
