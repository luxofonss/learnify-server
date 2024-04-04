package com.quyennv.lms.core.usecases.course;

import com.quyennv.lms.core.domain.entities.CourseStudent;

public class DeleteCourseStudentUseCase extends UpdateCourseStudentUseCase{
    public DeleteCourseStudentUseCase(CourseStudentRepository courseStudentRepository) {
        super(courseStudentRepository);
    }

    @Override
    public CourseStudent update(CourseStudent courseStudent, InputValues input) {
        return courseStudent.delete();
    }
}
