package com.quyennv.lms.core.usecases.course;

import com.quyennv.lms.core.domain.entities.Course;
import com.quyennv.lms.core.domain.entities.Identity;
import com.quyennv.lms.core.usecases.UseCase;
import lombok.Value;

public class GetOneCourseUseCase extends UseCase<GetOneCourseUseCase.InputValues, GetOneCourseUseCase.OutputValues> {
    private final CourseRepository courseRepository;

    public GetOneCourseUseCase(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public OutputValues execute(InputValues input) {
        Course course = courseRepository.findById(input.getCourseId()).orElseThrow(
                () -> new RuntimeException("Not found")
        );
        System.out.println("course:: " + course);
        return new OutputValues(course);
    }

    @Value
    public static class InputValues implements UseCase.InputValues {
        Identity courseId;
    }

    @Value
    public static class OutputValues implements UseCase.OutputValues {
        Course course;
    }
}
