package com.quyennv.lms.presenter.usecases.security;

import com.quyennv.lms.core.domain.enums.AuthType;
import com.quyennv.lms.core.usecases.user.CreateUserUseCase;
import com.quyennv.lms.presenter.rest.dto.auth.AuthRegisterRequest;
import com.quyennv.lms.presenter.utils.DateHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateUserUseCaseInputMapper {
    private final PasswordEncoder passwordEncoder;

    public CreateUserUseCaseInputMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public CreateUserUseCase.InputValues map(AuthRegisterRequest input) {
        return CreateUserUseCase.InputValues
                .builder()
                .username(input.getUsername())
                .email(input.getEmail())
                .password(passwordEncoder.encode(input.getPassword()))
                .firstName(input.getFirstName())
                .lastName(input.getLastName())
                .phoneNumber(input.getPhoneNumber())
                .gender(input.getGender())
                .dateOfBirth(DateHelper.toLocalDateTime(input.getDateOfBirth()))
                .avatar(input.getAvatar())
                .role(input.getRole())
                .authType(AuthType.EMAIL_PASSWORD)
                .learnerInfo(input.getLearnerInfo())
                .teacherInfo(input.getTeacherInfo())
                .build();
    }
}
