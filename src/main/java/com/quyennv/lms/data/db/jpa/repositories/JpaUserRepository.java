package com.quyennv.lms.data.db.jpa.repositories;

import com.quyennv.lms.data.db.jpa.entities.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface JpaUserRepository extends JpaRepository<UserData, UUID> {
    Optional<UserData> findByEmail(String email);
    Optional<UserData> findByUsername(String username);
}
