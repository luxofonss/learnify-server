package com.quyennv.lms.data.db.jpa.repositories;

import com.quyennv.lms.data.db.jpa.entities.CourseStudentData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface JpaCourseStudentRepository extends JpaRepository<CourseStudentData, UUID> {
    Optional<CourseStudentData> findByCourseIdAndStudentId(UUID courseId, UUID studentId);
    List<CourseStudentData> findByCourseId(UUID courseId);
    List<CourseStudentData> findAllByStudentId(UUID studentId);
}
