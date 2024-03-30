package com.quyennv.lms.data.db.jpa.repositories;

import com.quyennv.lms.data.db.jpa.entities.CourseData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaCourseRepository extends JpaRepository<CourseData, UUID> {
}
