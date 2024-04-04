package com.quyennv.lms.core.usecases.assignment;

import com.quyennv.lms.core.domain.entities.Assignment;
import com.quyennv.lms.core.domain.entities.Identity;

import java.util.Optional;

public interface AssignmentRepository {
    Assignment persist(Assignment assignment);
    Optional<Assignment> findById(Identity id);
}
