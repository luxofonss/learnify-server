package com.quyennv.lms.data.db.jpa.repositories;

import com.quyennv.lms.data.db.jpa.entities.AssignmentData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaAssignmentRepository extends JpaRepository<AssignmentData, UUID> {

}