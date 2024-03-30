package com.quyennv.lms.presenter.usecases.security;

import com.quyennv.lms.core.domain.entities.User;
import com.quyennv.lms.core.usecases.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

@Slf4j
public class AppUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public AppUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getByUsername(username).orElseThrow(
                ()->new UsernameNotFoundException("User not found")
        );

        return UserPrincipal.from(user);
    }
}
