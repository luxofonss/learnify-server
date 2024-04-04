package com.quyennv.lms.core.usecases.course;

import com.quyennv.lms.core.domain.entities.Course;
import com.quyennv.lms.core.domain.entities.CourseStudent;
import com.quyennv.lms.core.domain.entities.User;
import com.quyennv.lms.core.domain.enums.EnrollStatus;

import java.util.List;

public class TeacherAddStudentsToCourseUseCase extends RegisterForCourseUseCase{
    private final CourseRepository courseRepository;
    private final CourseStudentRepository courseStudentRepository;

    public TeacherAddStudentsToCourseUseCase(CourseRepository courseRepository, CourseStudentRepository courseStudentRepository) {
        super(courseRepository);
        this.courseRepository = courseRepository;
        this.courseStudentRepository = courseStudentRepository;
    }

    @Override
    public List<CourseStudent> createCourseStudents(InputValues input) {
        return input.getStudentIds().stream().map(
                user -> courseStudentRepository.persist(
                        CourseStudent
                                .builder()
                                .course(Course.builder().id(input.getCourseId()).build())
                                .student(User.builder().id(user).build())
                                .status(EnrollStatus.ACTIVE)
                                .build()
                )
        ).toList();
    }
}
