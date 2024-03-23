package com.example.bootshelf.course.querydsl;

import com.example.bootshelf.course.Course;

import java.util.Optional;

public interface CourseRepositoryCustom {

    Optional<Course> findProgramName(String programName);
}
