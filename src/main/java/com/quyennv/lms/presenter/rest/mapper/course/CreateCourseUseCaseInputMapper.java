package com.quyennv.lms.presenter.rest.mapper.course;

import com.quyennv.lms.core.domain.entities.Identity;
import com.quyennv.lms.core.domain.enums.CourseInfoType;
import com.quyennv.lms.core.domain.enums.CourseLevel;
import com.quyennv.lms.core.usecases.course.CreateCourseUseCase;
import com.quyennv.lms.presenter.rest.dto.course.CreateCourseRequest;
import com.quyennv.lms.presenter.rest.dto.course.CreateCourseRequestCourseInfo;
import com.quyennv.lms.presenter.rest.dto.course.CreateCourseRequestLecture;
import com.quyennv.lms.presenter.rest.dto.course.CreateCourseRequestSection;
import com.quyennv.lms.presenter.usecases.security.UserPrincipal;
import com.quyennv.lms.presenter.utils.DateHelper;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
public class CreateCourseUseCaseInputMapper {
    public static CreateCourseUseCase.InputValues map(UserPrincipal requester, CreateCourseRequest req) {
        return CreateCourseUseCase.InputValues
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
                .subjectId(Identity.fromString(req.getSubjectId()))
                .creatorId(Identity.from(requester.getId()))
                .courseInfos(mapInfos(req.getCourseInfos()))
                .sections(mapSections(req.getSections()))
                .build();
    }

    private static List<CreateCourseUseCase.CourseInfoInput> mapInfos(List<CreateCourseRequestCourseInfo> infos) {
        if (Objects.nonNull(infos)) {
            return infos.stream().map(
                    i -> CreateCourseUseCase.CourseInfoInput
                            .builder()
                            .content(i.getContent())
                            .type(CourseInfoType.valueOf(i.getType()))
                            .build()
            ).toList();
        } else {
            return new ArrayList<>();
        }
    }

    private static List<CreateCourseUseCase.CourseSection> mapSections(List<CreateCourseRequestSection> sections) {
        if (Objects.nonNull(sections)) {
            return sections.stream().map(
                    s -> CreateCourseUseCase.CourseSection
                            .builder()
                            .name(s.getName())
                            .description(s.getDescription())
                            .lectures(mapLectures(s.getLectures()))
                            .build()
            ).toList();

        } else {
            return new ArrayList<>();
        }
    }

    private static List<CreateCourseUseCase.CourseLecture> mapLectures(List<CreateCourseRequestLecture> lectures) {
        if (Objects.nonNull(lectures)) {
            return lectures.stream().map(
                    l -> CreateCourseUseCase.CourseLecture
                            .builder()
                            .name(l.getName())
                            .description(l.getDescription())
                            .build()
            ).toList();

        } else {
            return new ArrayList<>();
        }
    }
}
