package com.quyennv.lms.core.usecases.course;

import com.quyennv.lms.core.domain.entities.Course;
import com.quyennv.lms.core.domain.entities.CourseStudent;
import com.quyennv.lms.core.domain.entities.User;
import com.quyennv.lms.core.domain.enums.EnrollStatus;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class StudentRegisterForCourseUseCase extends RegisterForCourseUseCase{
    private final CourseRepository courseRepository;
    private final CourseStudentRepository courseStudentRepository;

    public StudentRegisterForCourseUseCase(CourseRepository repository, CourseStudentRepository courseStudentRepository) {
        super(repository);
        this.courseRepository = repository;
        this.courseStudentRepository = courseStudentRepository;
    }

    @Override
    public List<CourseStudent> createCourseStudents(InputValues input) {
        return List.of(courseStudentRepository.persist(
                CourseStudent
                        .builder()
                        .course(Course.builder().id(input.getCourseId()).build())
                        .student(User.builder().id(input.getStudentIds().get(0)).build())
                        .price(input.getPrice())
                        .status(EnrollStatus.PENDING)
                        .build()
        ));
    }
}
