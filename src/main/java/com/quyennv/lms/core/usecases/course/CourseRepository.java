package com.quyennv.lms.core.usecases.course;

import com.quyennv.lms.core.domain.entities.Course;
import com.quyennv.lms.core.domain.entities.Identity;
import com.quyennv.lms.core.domain.enums.CourseLevel;

import java.util.List;
import java.util.Optional;

public interface CourseRepository {
    Course persist(Course course);
    Optional<Course> findById(Identity id);
    List<Course> getWithFilters(
            String keyword,
            CourseLevel level,
            Integer grade,
            String code,
            List<Identity> teacherId
    );
}
