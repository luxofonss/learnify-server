package com.quyennv.lms.data.db.jpa.repositories;

import com.quyennv.lms.core.domain.entities.User;
import com.quyennv.lms.core.usecases.user.UserRepository;
import com.quyennv.lms.data.db.jpa.entities.UserData;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final JpaUserRepository jpaUserRepository;

    public UserRepositoryImpl(JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }


    @Override
    public Optional<User> getUserById(String userId) {
        return jpaUserRepository.findById(UUID.fromString(userId)).map(UserData::fromThis);
    }

    @Override
    public Optional<User> getByUsername(String email) {
        return jpaUserRepository.findByUsername(email).map(UserData::fromThis);
    }

    @Override
    public User persist(User user) {
        return jpaUserRepository.save(UserData.from(user)).fromThis();
    }
}
