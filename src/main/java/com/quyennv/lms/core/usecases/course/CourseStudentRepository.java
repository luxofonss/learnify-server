package com.quyennv.lms.core.usecases.course;

import com.quyennv.lms.core.domain.entities.CourseStudent;
import com.quyennv.lms.core.domain.entities.Identity;

import java.util.List;
import java.util.Optional;

public interface CourseStudentRepository {
    CourseStudent persist(CourseStudent courseStudent);
    Optional<CourseStudent> findByCourseAndStudent(Identity courseId, Identity studentId);
    List<CourseStudent> findByCourse(Identity courseId);
    List<CourseStudent> findCoursesByStudent(Identity studentId);
}
