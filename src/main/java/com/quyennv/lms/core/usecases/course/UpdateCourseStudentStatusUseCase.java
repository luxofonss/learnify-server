package com.quyennv.lms.core.usecases.course;

import com.quyennv.lms.core.domain.entities.CourseStudent;
import com.quyennv.lms.core.domain.enums.EnrollStatus;

public class UpdateCourseStudentStatusUseCase extends UpdateCourseStudentUseCase {
    public UpdateCourseStudentStatusUseCase(CourseStudentRepository courseStudentRepository) {
        super(courseStudentRepository);
    }

    @Override
    public CourseStudent update(CourseStudent courseStudent, InputValues input) {
        EnrollStatus status = courseStudent.getStatus();
        if (courseStudent.getStatus().equals(input.getStatus())) {
            throw new RuntimeException("Status is already " + status);
        }

        return courseStudent.withStatus(input.getStatus());
    }

}
