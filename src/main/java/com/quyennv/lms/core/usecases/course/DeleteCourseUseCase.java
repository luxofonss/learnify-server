package com.quyennv.lms.core.usecases.course;

import com.quyennv.lms.core.domain.entities.Course;

public class DeleteCourseUseCase extends CourseUpdateUseCase{
    public DeleteCourseUseCase(CourseRepository courseRepository) {
        super(courseRepository);
    }

    @Override
    protected Course update(Course course, InputValues input) {
        return course.delete();
    }
}
