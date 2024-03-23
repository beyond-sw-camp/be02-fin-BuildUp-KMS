package com.example.bootshelf.course.repository;



import com.example.bootshelf.course.Course;
import com.example.bootshelf.course.querydsl.CourseRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer>, CourseRepositoryCustom {
    public Optional<Course> findByProgramName(String programName);

}
