package com.quyennv.lms.presenter.rest.mapper.course;

import com.quyennv.lms.core.domain.entities.Identity;
import com.quyennv.lms.core.domain.enums.CourseLevel;
import com.quyennv.lms.core.usecases.course.CourseUpdateUseCase;
import com.quyennv.lms.presenter.rest.dto.course.UpdateCourseRequest;
import com.quyennv.lms.presenter.usecases.security.UserPrincipal;
import com.quyennv.lms.presenter.utils.DateHelper;

import java.util.Objects;

public class CourseMutationDetailInputMapper extends UpdateCourseUseCaseInputMapper{
//    @Override
    public static CourseUpdateUseCase.InputValues map(UserPrincipal requester, UpdateCourseRequest req, String courseId) {
        return CourseUpdateUseCase.InputValues
                .builder()
                .courseId(Identity.fromString(courseId))
                .name(req.getName())
                .description(req.getDescription())
                .backgroundImage(req.getBackgroundImage())
                .thumbnail(req.getThumbnail())
                .startDate(Objects.nonNull(req.getStartDate()) ? DateHelper.toLocalDateTime(req.getStartDate()) : null)
                .endDate(Objects.nonNull(req.getEndDate()) ? DateHelper.toLocalDateTime(req.getEndDate()) : null)
                .price(req.getPrice())
                .level(Objects.nonNull(req.getLevel()) ? CourseLevel.valueOf(req.getLevel()) : null)
                .grade(req.getGrade())
                .requesterId(Objects.nonNull(requester.getId()) ? Identity.from(requester.getId()) : null)
                .subjectId(Objects.nonNull(req.getSubjectId()) ? Identity.fromString(req.getSubjectId()) : null)
                .courseInfos(mapInfos(req.getCourseInfos()))
                .students(mapStudents(req.getStudents()))
                .sections(mapSections(req.getSections()))
                .build();
    }

    public static CourseUpdateUseCase.InputValues map(UserPrincipal requester, String courseId) {
        return CourseUpdateUseCase.InputValues
                .builder()
                .courseId(Identity.fromString(courseId))
                .requesterId(Objects.nonNull(requester.getId()) ? Identity.from(requester.getId()) : null)
                .build();
    }
}
