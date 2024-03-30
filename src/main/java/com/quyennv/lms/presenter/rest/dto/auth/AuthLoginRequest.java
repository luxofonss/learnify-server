package com.quyennv.lms.presenter.rest.dto.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Value;


@Value
public class AuthLoginRequest {
    @NotBlank
    String username;
    @NotBlank
    String password;
}
