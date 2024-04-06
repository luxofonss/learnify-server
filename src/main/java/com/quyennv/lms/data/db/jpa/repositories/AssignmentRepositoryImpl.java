package com.quyennv.lms.data.db.jpa.repositories;

import com.quyennv.lms.core.domain.entities.Assignment;
import com.quyennv.lms.core.domain.entities.Identity;
import com.quyennv.lms.core.usecases.assignment.AssignmentRepository;
import com.quyennv.lms.data.db.jpa.entities.AssignmentData;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class AssignmentRepositoryImpl implements AssignmentRepository {
    private final  JpaAssignmentRepository jpaAssignmentRepository;
    private final CriteriaAssignmentRepository criteriaAssignmentRepository;

    public AssignmentRepositoryImpl(JpaAssignmentRepository jpaAssignmentRepository, CriteriaAssignmentRepository criteriaAssignmentRepository) {
        this.jpaAssignmentRepository = jpaAssignmentRepository;
        this.criteriaAssignmentRepository = criteriaAssignmentRepository;
    }

    @Override
    @Transactional
    public Assignment persist(Assignment assignment) {
        return jpaAssignmentRepository.save(AssignmentData.from(assignment)).fromThis();
    }

    @Override
    @Transactional
    public Optional<Assignment> findById(Identity id) {
        return jpaAssignmentRepository.findById(id.getId()).map(AssignmentData::fromThis);
    }

    @Override
    public List<Assignment> findAllWithFilter(
            Identity id,
            String title,
            Identity teacherId,
            Identity subjectId) {
        return criteriaAssignmentRepository.findAllWithFilter(id, title, teacherId, subjectId).stream().map(AssignmentData::fromThis).toList();
    }
}
