package com.example.bootshelf.course.repository;



import com.example.bootshelf.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
    public Optional<Course> findByProgramName(String programName);

}
