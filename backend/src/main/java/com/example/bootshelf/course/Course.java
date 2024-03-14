package com.example.bootshelf.course;

import com.example.bootshelf.certification.Certification;
import lombok.*;

import javax.persistence.*;

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

    @OneToOne(mappedBy = "course", fetch = FetchType.LAZY)
    private Certification certification;

    @Column(nullable = false, length = 50)
    private String institutionName;

    @Column(nullable = false, length = 45)
    private String programName;

    @Column(nullable = false)
    private String startDate;

    @Column(nullable = false)
    private String endDate;
}
