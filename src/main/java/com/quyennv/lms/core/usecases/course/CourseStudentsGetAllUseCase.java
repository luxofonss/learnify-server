package com.quyennv.lms.core.usecases.course;

import com.quyennv.lms.core.domain.entities.Course;
import com.quyennv.lms.core.domain.entities.CourseStudent;
import com.quyennv.lms.core.domain.entities.Identity;
import com.quyennv.lms.core.usecases.UseCase;
import lombok.Builder;
import lombok.Value;

import java.util.List;

public class CourseStudentsGetAllUseCase extends UseCase<
        CourseStudentsGetAllUseCase.InputValues, CourseStudentsGetAllUseCase.OutputValues> {
    private final CourseRepository courseRepository;
    private final CourseStudentRepository courseStudentRepository;

    public CourseStudentsGetAllUseCase(CourseRepository courseRepository, CourseStudentRepository courseStudentRepository) {
        this.courseRepository = courseRepository;
        this.courseStudentRepository = courseStudentRepository;
    }

    @Override
    public OutputValues execute(InputValues input) {
        return new OutputValues(courseStudentRepository.findByCourse(input.courseId));
    }

    @Value
    @Builder
    public static class InputValues implements UseCase.InputValues {
        Identity requesterId;
        Identity courseId;
    }

    @Value
    public static class OutputValues implements UseCase.OutputValues {
        List<CourseStudent> students;
    }
}
