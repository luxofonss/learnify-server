package com.quyennv.lms.core.usecases.course;

import com.quyennv.lms.core.domain.entities.Course;

import java.util.List;

public class GetAllCourseUseCase extends GetCoursesUseCase{
    public GetAllCourseUseCase(CourseRepository repository) {
        super(repository);
    }

    @Override
    public List<Course> getCourses(InputValues input) {
        return courseRepository.getWithFilters(
                input.getKeyword(),
                input.getLevel(),
                input.getGrade(),
                input.getCode(),
                input.getTeacherIds()
        );
    }
}
