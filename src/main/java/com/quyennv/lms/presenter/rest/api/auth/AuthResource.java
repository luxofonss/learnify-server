package com.quyennv.lms.presenter.rest.api.auth;

import com.quyennv.lms.presenter.rest.dto.ApiResponse;
import com.quyennv.lms.presenter.rest.dto.auth.AuthLoginRequest;
import com.quyennv.lms.presenter.rest.dto.auth.AuthRegisterRequest;
import com.quyennv.lms.presenter.usecases.security.CurrentUser;
import com.quyennv.lms.presenter.usecases.security.UserPrincipal;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/v1/auth")
public interface AuthResource {
    @PostMapping("/login")
    CompletableFuture<ResponseEntity<ApiResponse>> login(@Valid @RequestBody AuthLoginRequest loginRequest);

    @PostMapping("/register")
    CompletableFuture<ResponseEntity<ApiResponse>> register(@Valid @RequestBody AuthRegisterRequest loginRequest);

    @GetMapping("/who-am-i")
    CompletableFuture<ResponseEntity<ApiResponse>> whoAmI(@CurrentUser UserPrincipal requester);
}
