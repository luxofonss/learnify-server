package com.quyennv.lms.presenter.rest.mapper.course;

import com.quyennv.lms.core.domain.entities.Identity;
import com.quyennv.lms.core.domain.enums.CourseLevel;
import com.quyennv.lms.core.usecases.course.GetCoursesUseCase;
import com.quyennv.lms.presenter.rest.dto.course.GetCoursesRequest;
import com.quyennv.lms.presenter.usecases.security.UserPrincipal;

import java.util.Objects;

public class GetCourseUseCaseInputMapper {
    public static GetCoursesUseCase.InputValues map(GetCoursesRequest request, UserPrincipal requester) {
         return GetCoursesUseCase.InputValues
                .builder()
                .keyword(request.getKeyword())
                .level(Objects.nonNull(request.getLevel()) ? CourseLevel.valueOf(request.getLevel()) : null)
                .grade(request.getGrade())
                .code(request.getCode())
                .teacherIds(Objects.nonNull(request.getTeacherIds())
                        ? request.getTeacherIds().stream().map(Identity::fromString).toList()
                        : null)
                .requesterId(Identity.from(requester.getId()))
                .build();
    }
}
