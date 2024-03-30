package com.quyennv.lms.presenter.usecases.security;

import com.quyennv.lms.presenter.rest.dto.auth.AuthLoginRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class AuthenticationUseCaseInputMapper {
    public static AuthenticationUseCase.InputValues map(AuthLoginRequest authLoginRequest) {
        return new AuthenticationUseCase.InputValues(
            new UsernamePasswordAuthenticationToken(
                authLoginRequest.getUsername(),
                authLoginRequest.getPassword()
            )
        );
    }
}
