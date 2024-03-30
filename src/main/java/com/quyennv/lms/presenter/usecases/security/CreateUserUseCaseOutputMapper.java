package com.quyennv.lms.presenter.usecases.security;

import com.quyennv.lms.core.usecases.user.CreateUserUseCase;
import com.quyennv.lms.presenter.rest.dto.ApiResponse;
import com.quyennv.lms.presenter.rest.dto.auth.AuthRegisterResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

@Slf4j
public class CreateUserUseCaseOutputMapper {
    public static ResponseEntity<ApiResponse> map(CreateUserUseCase.OutputValues outputValues) {
        return ResponseEntity.ok(new ApiResponse(
                true,
                "Register successfully!",
                new AuthRegisterResponse(outputValues.getUserId()))
        );
    }
}
