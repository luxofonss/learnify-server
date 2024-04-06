package com.quyennv.lms.data.db.jpa.repositories;

import com.quyennv.lms.data.db.jpa.entities.QuestionData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JPAQuestionRepository extends JpaRepository<QuestionData, UUID> {
}
