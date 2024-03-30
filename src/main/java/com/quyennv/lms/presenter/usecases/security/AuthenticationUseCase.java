package com.quyennv.lms.presenter.usecases.security;

import com.quyennv.lms.core.usecases.UseCase;
import lombok.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationUseCase extends UseCase<AuthenticationUseCase.InputValues, AuthenticationUseCase.OutputValues> {
    private AuthenticationManager authenticationManager;
    private JwtProvider jwtProvider;

    public AuthenticationUseCase(AuthenticationManager authenticationManager, JwtProvider jwtProvider) {
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
    }

    @Override
    public OutputValues execute(InputValues input) {
        Authentication authentication = authenticationManager.authenticate(input.getAuthenticationToken());
        return new OutputValues(
                jwtProvider.generateAccessToken(authentication),
                jwtProvider.generateRefreshToken(authentication)
        );
    }

    @Value
    public static class InputValues implements UseCase.InputValues{
        UsernamePasswordAuthenticationToken authenticationToken;
    }

    @Value
    public static class OutputValues implements UseCase.OutputValues{
        String accessToken;
        String refreshToken;
    }
}
