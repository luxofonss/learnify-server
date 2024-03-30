package com.quyennv.lms.presenter.rest.api.course;

import com.quyennv.lms.presenter.rest.dto.ApiResponse;
import com.quyennv.lms.presenter.rest.dto.course.CreateCourseRequest;
import com.quyennv.lms.presenter.rest.dto.course.UpdateCourseRequest;
import com.quyennv.lms.presenter.usecases.security.CurrentUser;
import com.quyennv.lms.presenter.usecases.security.UserPrincipal;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/v1/courses")
public interface CourseResource {
    @PostMapping
    CompletableFuture<ApiResponse> create(
            @Valid @RequestBody CreateCourseRequest req,
            @CurrentUser UserPrincipal requester,
            HttpServletRequest httpServletRequest
            );

    @GetMapping("/{id}")
    CompletableFuture<ApiResponse> getById(
            @PathVariable String id,
            @CurrentUser UserPrincipal requester,
            HttpServletRequest httpServletRequest
    );

    @PostMapping("/{id}/sections")
    CompletableFuture<ApiResponse> addSections(
            @Valid @RequestBody UpdateCourseRequest req,
            @PathVariable String id,
            @CurrentUser UserPrincipal requester,
            HttpServletRequest httpServletRequest
            );

    @PutMapping("/{id}/sections")
    CompletableFuture<ApiResponse> updateSections(
            @Valid @RequestBody UpdateCourseRequest req,
            @PathVariable String id,
            @CurrentUser UserPrincipal requester,
            HttpServletRequest httpServletRequest
    );

    @DeleteMapping("/{id}/sections")
    CompletableFuture<ApiResponse> deleteSections(
            @Valid @RequestBody UpdateCourseRequest req,
            @PathVariable String id,
            @CurrentUser UserPrincipal requester,
            HttpServletRequest httpServletRequest
    );

    @PostMapping("/{courseId}/sections/{sectionId}/lectures")
    CompletableFuture<ApiResponse> addLectures(
            @Valid @RequestBody UpdateCourseRequest req,
            @PathVariable String courseId,
            @PathVariable String sectionId,
            @CurrentUser UserPrincipal requester,
            HttpServletRequest httpServletRequest
    );

    @PutMapping("/{courseId}/sections/{sectionId}/lectures")
    CompletableFuture<ApiResponse> updateLectures(
            @Valid @RequestBody UpdateCourseRequest req,
            @PathVariable String courseId,
            @PathVariable String sectionId,
            @CurrentUser UserPrincipal requester,
            HttpServletRequest httpServletRequest
    );

    @DeleteMapping("/{courseId}/sections/{sectionId}/lectures")
    CompletableFuture<ApiResponse> deleteLectures(
            @Valid @RequestBody UpdateCourseRequest req,
            @PathVariable String courseId,
            @PathVariable String sectionId,
            @CurrentUser UserPrincipal requester,
            HttpServletRequest httpServletRequest
    );
}
