package com.quyennv.lms.presenter.rest.api.course;

import com.quyennv.lms.presenter.rest.dto.ApiResponse;
import com.quyennv.lms.presenter.rest.dto.course.*;
import com.quyennv.lms.presenter.usecases.security.CurrentUser;
import com.quyennv.lms.presenter.usecases.security.UserPrincipal;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Null;
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

    @GetMapping
    CompletableFuture<ApiResponse> getAllCourses(
            @Valid @ModelAttribute GetCoursesRequest request,
            @CurrentUser UserPrincipal requester,
            HttpServletRequest httpServletRequest
    );

    @PutMapping("/{id}")
    CompletableFuture<ApiResponse> updateCourse(
            @Valid @RequestBody UpdateCourseRequest req,
            @PathVariable String id,
            @CurrentUser UserPrincipal requester,
            HttpServletRequest httpServletRequest
    );

    @GetMapping("/my-courses")
    CompletableFuture<ApiResponse> getAllMyCourses(
            @Valid @ModelAttribute GetCoursesRequest request,
            @CurrentUser UserPrincipal requester,
            HttpServletRequest httpServletRequest
    );

    @GetMapping("/my-registered-courses")
    CompletableFuture<ApiResponse> getAllMyRegisteredCourses(
            @CurrentUser UserPrincipal requester,
            HttpServletRequest httpServletRequest
    );

    @GetMapping("/{id}")
    CompletableFuture<ApiResponse> getById(
            @PathVariable String id,
            @CurrentUser UserPrincipal requester,
            HttpServletRequest httpServletRequest
    );

    @DeleteMapping("/{id}")
    CompletableFuture<ApiResponse> deleteCourse(
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

    @PostMapping("/{courseId}/students")
    CompletableFuture<ApiResponse> studentRegisterForCourse(
            @PathVariable String courseId,
            @CurrentUser UserPrincipal requester,
            HttpServletRequest httpServletRequest
    );

    @GetMapping("/{courseId}/students")
    CompletableFuture<ApiResponse> getAllStudentsInCourse(
            @PathVariable String courseId,
            @CurrentUser UserPrincipal requester,
            HttpServletRequest httpServletRequest
    );

    @PutMapping("/{courseId}/students/{studentId}")
    CompletableFuture<ApiResponse> updateStudentInCourse(
            @PathVariable String courseId,
            @PathVariable String studentId,
            @Valid @RequestBody UpdateCourseStudentStatusRequest req,
            @CurrentUser UserPrincipal requester,
            HttpServletRequest httpServletRequest
    );

    @DeleteMapping("/{courseId}/students/{studentId}")
    CompletableFuture<ApiResponse> deleteStudentInCourse(
            @PathVariable String courseId,
            @PathVariable String studentId,
            @CurrentUser UserPrincipal requester,
            HttpServletRequest httpServletRequest
    );

    @PostMapping("/{courseId}/students/add-many")
    CompletableFuture<ApiResponse> teacherAddStudentsToCourse(
            @Valid @RequestBody AddStudentToCourseRequest req,
            @PathVariable String courseId,
            @CurrentUser UserPrincipal requester,
            HttpServletRequest httpServletRequest
    );
}
