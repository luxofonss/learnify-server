package com.quyennv.lms.core.usecases.course;

import com.quyennv.lms.core.domain.entities.Course;

import java.util.List;

public class GetCreatedCoursesUseCase extends GetCoursesUseCase{
    public GetCreatedCoursesUseCase(CourseRepository repository) {
        super(repository);
    }

    @Override
    public List<Course> getCourses(InputValues input) {
        return courseRepository.getWithFilters(
                input.getKeyword(),
                input.getLevel(),
                input.getGrade(),
                input.getCode(),
                List.of(input.getRequesterId()) // teacher id is requester id only
        );
    }
}
