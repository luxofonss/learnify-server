package com.quyennv.lms.data.db.jpa.repositories;

import com.quyennv.lms.core.domain.entities.CourseStudent;
import com.quyennv.lms.core.domain.entities.Identity;
import com.quyennv.lms.core.usecases.course.CourseStudentRepository;
import com.quyennv.lms.data.db.jpa.entities.CourseStudentData;
import com.quyennv.lms.data.db.jpa.entities.CourseStudentDataKey;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Slf4j
public class CourseStudentRepositoryImpl implements CourseStudentRepository {
    private final JpaCourseStudentRepository jpaCourseStudentRepository;
    private final EntityManager em;
    private final CriteriaBuilder builder;

    public CourseStudentRepositoryImpl(JpaCourseStudentRepository jpaCourseStudentRepository, EntityManager em) {
        this.jpaCourseStudentRepository = jpaCourseStudentRepository;
        this.em = em;
        this.builder = em.getCriteriaBuilder();
    }

    @Override
    @Transactional
    public CourseStudent persist(CourseStudent courseStudent) {
        CourseStudentDataKey key = new CourseStudentDataKey(courseStudent.getCourse().getId().getId(), courseStudent.getStudent().getId().getId());
        CourseStudentData courseStudentData = CourseStudentData.from(courseStudent);
        courseStudentData.setId(key);
        return jpaCourseStudentRepository.save(courseStudentData).fromThis();
    }

    @Override
    public Optional<CourseStudent> findByCourseAndStudent(Identity courseId, Identity studentId) {
        return jpaCourseStudentRepository.findByCourseIdAndStudentId(courseId.getId(), studentId.getId()).map(CourseStudentData::fromThis);
    }

    @Override
    public List<CourseStudent> findByCourse(Identity courseId) {
        return jpaCourseStudentRepository.findByCourseId(courseId.getId()).stream().map(CourseStudentData::fromThis).toList();
    }

    @Override
    @Transactional
    public List<CourseStudent> findCoursesByStudent(Identity studentId) {
        CriteriaQuery<CourseStudentData> cq = builder.createQuery(CourseStudentData.class);
        Root<CourseStudentData> root = cq.from(CourseStudentData.class);

        cq.where(builder.equal(root.get("student").get("id"), studentId.getId()));
        return em.createQuery(cq).getResultList().stream().map(CourseStudentData::fromThis).toList();
    }
}
