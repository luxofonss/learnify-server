package com.quyennv.lms.core.usecases.assignment;

import com.quyennv.lms.core.domain.entities.Assignment;
import com.quyennv.lms.core.domain.entities.Identity;

import java.util.List;
import java.util.Optional;

public interface AssignmentRepository {
    Assignment persist(Assignment assignment);
    Optional<Assignment> findById(Identity id);
    List<Assignment> findAllWithFilter(
            Identity id,
            String title,
            Identity teacherId,
            Identity subjectId
    );
}
