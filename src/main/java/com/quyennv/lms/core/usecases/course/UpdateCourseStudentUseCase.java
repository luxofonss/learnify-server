package com.quyennv.lms.core.usecases.course;

import com.quyennv.lms.core.domain.entities.Course;
import com.quyennv.lms.core.domain.entities.CourseStudent;
import com.quyennv.lms.core.domain.entities.Identity;
import com.quyennv.lms.core.domain.enums.EnrollStatus;
import com.quyennv.lms.core.usecases.UseCase;
import lombok.Builder;
import lombok.Value;

public abstract class UpdateCourseStudentUseCase extends UseCase<UpdateCourseStudentUseCase.InputValues, UpdateCourseStudentUseCase.OutputValues> {
    public final CourseStudentRepository courseStudentRepository;

    protected UpdateCourseStudentUseCase(CourseStudentRepository courseStudentRepository) {
        this.courseStudentRepository = courseStudentRepository;
    }

    @Override
    public OutputValues execute(InputValues input) {
        CourseStudent courseStudent = courseStudentRepository.findByCourseAndStudent(input.courseId, input.userId)
                .orElseThrow(() -> new IllegalArgumentException("Course student not found"));

        courseStudent.setCourse(Course.builder().id(input.getCourseId()).build());
        CourseStudent newCourseStudent = update(courseStudent, input);

        return new OutputValues(
                courseStudentRepository.persist(newCourseStudent)
        );
    }

    public abstract CourseStudent update(CourseStudent courseStudent, InputValues input);

    @Value
    @Builder
    public static class InputValues implements UseCase.InputValues {
        Identity userId;
        Identity courseId;
        EnrollStatus status;
        Integer price;
    }

    @Value
    public static class OutputValues implements UseCase.OutputValues {
        CourseStudent courseStudent;
    }
}
