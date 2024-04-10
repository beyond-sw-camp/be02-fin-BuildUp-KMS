package com.example.bootshelf.course;

import com.example.bootshelf.certification.Certification;
import lombok.*;
import org.joda.time.LocalDateTime;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    private List<Certification> certificationList = new ArrayList<>();

    @Column(nullable = false, length = 50)
    private String institutionName;

    @Column(nullable = false, length = 45)
    private String programName;

    @Column(nullable = false)
    private LocalDateTime startDate;

    @Column(nullable = false)
    private LocalDateTime endDate;
}
