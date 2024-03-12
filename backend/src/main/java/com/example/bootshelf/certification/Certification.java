package com.example.bootshelf.certification;

import com.example.bootshelf.course.Course;
import com.example.bootshelf.user.model.entity.User;
import lombok.*;

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

    @ManyToOne
    @JoinColumn(name = "User_idx")
    private User user;

    @OneToOne
    @JoinColumn(name = "Course_idx")
    private Course course;

    @Column(nullable = false)
    private Boolean status;

    @Column(nullable = false)
    private String createdAt;

    @Column(nullable = false)
    private String updatedAt;
}
