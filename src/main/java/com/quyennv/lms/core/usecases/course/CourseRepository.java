package com.quyennv.lms.core.usecases.course;

import com.quyennv.lms.core.domain.entities.Course;
import com.quyennv.lms.core.domain.entities.Identity;

import java.util.Optional;

public interface CourseRepository {
    Course persist(Course course);
    Optional<Course> findById(Identity id);
}
