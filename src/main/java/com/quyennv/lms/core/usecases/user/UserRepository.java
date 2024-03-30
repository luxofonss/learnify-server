package com.quyennv.lms.core.usecases.user;

import com.quyennv.lms.core.domain.entities.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> getUserById(String userId);
    Optional<User> getByUsername(String email);
    User persist(User user);
}
