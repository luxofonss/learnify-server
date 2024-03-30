package com.quyennv.lms.presenter.rest.mapper.course;

import com.quyennv.lms.core.domain.entities.Identity;
import com.quyennv.lms.core.domain.enums.CourseLevel;
import com.quyennv.lms.core.usecases.course.CourseUpdateUseCase;
import com.quyennv.lms.presenter.rest.dto.course.UpdateCourseRequest;
import com.quyennv.lms.presenter.usecases.security.UserPrincipal;
import com.quyennv.lms.presenter.utils.DateHelper;

public class UpdateCourseDetailInputMapper extends UpdateCourseUseCaseInputMapper{
//    @Override
    public CourseUpdateUseCase.InputValues map(UserPrincipal requester, UpdateCourseRequest req) {
        return CourseUpdateUseCase.InputValues
                .builder()
                .name(req.getName())
                .description(req.getDescription())
                .backgroundImage(req.getBackgroundImage())
                .thumbnail(req.getThumbnail())
                .startDate(DateHelper.toLocalDateTime(req.getStartDate()))
                .endDate(DateHelper.toLocalDateTime(req.getEndDate()))
                .price(req.getPrice())
                .level(CourseLevel.valueOf(req.getLevel()))
                .grade(req.getGrade())
                .requesterId(Identity.from(requester.getId()))
                .subjectId(Identity.fromString(req.getSubjectId()))
                .courseInfos(mapInfos(req.getCourseInfos()))
                .students(mapStudents(req.getStudents()))
                .sections(mapSections(req.getSections()))
                .build();
    }
}
