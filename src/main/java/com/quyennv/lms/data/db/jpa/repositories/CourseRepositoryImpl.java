package com.quyennv.lms.data.db.jpa.repositories;

import com.quyennv.lms.core.domain.entities.Course;
import com.quyennv.lms.core.domain.entities.Identity;
import com.quyennv.lms.core.domain.enums.CourseLevel;
import com.quyennv.lms.core.usecases.course.CourseRepository;
import com.quyennv.lms.data.db.jpa.entities.CourseData;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Slf4j
public class CourseRepositoryImpl implements CourseRepository {
    private final JpaCourseRepository jpaCourseRepository;
    private final CriteriaCourseRepository criteriaCourseRepository;

    public CourseRepositoryImpl(JpaCourseRepository jpaCourseRepository,
                                CriteriaCourseRepository criteriaCourseRepository) {
        this.jpaCourseRepository = jpaCourseRepository;
        this.criteriaCourseRepository = criteriaCourseRepository;
    }

    @Override
    @Transactional
    public Course persist(Course course) {
        CourseData courseData = CourseData.from(course);
        return jpaCourseRepository.save(courseData).fromThis();
    }

    @Override
    @Transactional
    public Optional<Course> findById(Identity id) {
        return jpaCourseRepository.findById(id.getId()).map(CourseData::fromThis);
    }

    @Override
    @Transactional
    public List<Course> getWithFilters(
            String keyword,
            CourseLevel level,
            Integer grade,
            String code,
            List<Identity> teacherIds) {
        return criteriaCourseRepository.getWithFilters(
                keyword, level, grade, code, teacherIds
        ).stream().map(CourseData::fromThis).toList();
    }
}
