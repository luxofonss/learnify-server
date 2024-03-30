package com.quyennv.lms.presenter.rest.mapper.course;

import com.quyennv.lms.core.domain.entities.Identity;
import com.quyennv.lms.core.usecases.course.CourseUpdateUseCase;
import com.quyennv.lms.presenter.rest.dto.course.UpdateCourseRequest;
import com.quyennv.lms.presenter.usecases.security.UserPrincipal;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SectionMutationInputMapper extends UpdateCourseUseCaseInputMapper{
//    @Override
    public static CourseUpdateUseCase.InputValues map(UserPrincipal requester, UpdateCourseRequest req, String courseId) {

        return CourseUpdateUseCase.InputValues
                .builder()
                .courseId(Identity.fromString(courseId))
                .sections(mapSections(req.getSections()))
                .requesterId(Identity.from(requester.getId()))
                .build();
    }
}
