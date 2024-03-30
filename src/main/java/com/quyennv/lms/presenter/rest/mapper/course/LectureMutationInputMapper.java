package com.quyennv.lms.presenter.rest.mapper.course;

import com.quyennv.lms.core.domain.entities.Identity;
import com.quyennv.lms.core.usecases.course.CourseUpdateUseCase;
import com.quyennv.lms.presenter.rest.dto.course.UpdateCourseRequest;
import com.quyennv.lms.presenter.usecases.security.UserPrincipal;

public class LectureMutationInputMapper extends UpdateCourseUseCaseInputMapper{
    public static CourseUpdateUseCase.InputValues map(
            UserPrincipal requester,
            UpdateCourseRequest req,
            String courseId,
            String sectionId) {

        return CourseUpdateUseCase.InputValues
                .builder()
                .courseId(Identity.fromString(courseId))
                .sectionId(Identity.fromString(sectionId))
                .lectures(mapLectures(req.getLectures()))
                .requesterId(Identity.from(requester.getId()))
                .build();
    }
}
