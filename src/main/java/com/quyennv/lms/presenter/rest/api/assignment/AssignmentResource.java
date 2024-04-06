package com.quyennv.lms.presenter.rest.api.assignment;

import com.quyennv.lms.presenter.rest.dto.ApiResponse;
import com.quyennv.lms.presenter.rest.dto.assignment.CreateAssignmentRequest;
import com.quyennv.lms.presenter.rest.dto.assignment.UpdateAssignmentRequest;
import com.quyennv.lms.presenter.rest.dto.course.CreateCourseRequest;
import com.quyennv.lms.presenter.usecases.security.CurrentUser;
import com.quyennv.lms.presenter.usecases.security.UserPrincipal;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/v1/assignments")
public interface AssignmentResource {
    @PostMapping
    CompletableFuture<ApiResponse> create(
            @Valid @RequestBody CreateAssignmentRequest req,
            @CurrentUser UserPrincipal requester,
            HttpServletRequest httpServletRequest
    );
    @GetMapping("/{id}")
    CompletableFuture<ApiResponse> getOne(
            @PathVariable @NotNull String id,
            @CurrentUser UserPrincipal requester,
            HttpServletRequest httpServletRequest
    );


    @PutMapping("/{id}")
    CompletableFuture<ApiResponse> update(
            @Valid @RequestBody UpdateAssignmentRequest req,
            @PathVariable String id,
            @CurrentUser UserPrincipal requester,
            HttpServletRequest httpServletRequest
    );

    @PostMapping("/{id}/questions")
    CompletableFuture<ApiResponse> addQuestions(
            @Valid @RequestBody UpdateAssignmentRequest req,
            @PathVariable String id,
            @CurrentUser UserPrincipal requester,
            HttpServletRequest httpServletRequest
    );
}
