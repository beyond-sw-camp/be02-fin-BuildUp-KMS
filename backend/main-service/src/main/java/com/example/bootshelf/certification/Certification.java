package com.example.bootshelf.certification;

import com.example.bootshelf.course.Course;
import com.example.bootshelf.user.model.entity.User;
import lombok.*;
import org.joda.time.LocalDateTime;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Certification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "User_idx")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Course_idx")
    private Course course;

    @Column(nullable = false)
    private Boolean status;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;
}
