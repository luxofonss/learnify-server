package com.quyennv.lms.presenter.usecases.security;

import com.quyennv.lms.presenter.rest.dto.ApiResponse;
import com.quyennv.lms.presenter.rest.dto.auth.AuthLoginResponse;
import org.springframework.http.ResponseEntity;

public class AuthenticationUseCaseOutputMapper {
    public static ResponseEntity<ApiResponse> map(AuthenticationUseCase.OutputValues outputValues) {
        return ResponseEntity.ok(new ApiResponse(
                true,
                "Login successfully!",
                new AuthLoginResponse(
                outputValues.getAccessToken(), outputValues.getRefreshToken()))
        );
    }
}
