package com.example.bootshelf.course.querydsl;

import com.example.bootshelf.course.Course;
import com.example.bootshelf.course.QCourse;
import com.example.bootshelf.user.model.entity.User;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.Optional;

public class CourseRepositoryCustomImpl extends QuerydslRepositorySupport implements CourseRepositoryCustom {
    public CourseRepositoryCustomImpl() {
        super(Course.class);
    }

    @Override
    public Optional<Course> findProgramName(String programName) {
        QCourse course = new QCourse("course");

        Optional<Course> result = Optional.ofNullable(from(course)
                .where(course.programName.contains(programName))
                .fetchOne());

        return result;
    }
}
