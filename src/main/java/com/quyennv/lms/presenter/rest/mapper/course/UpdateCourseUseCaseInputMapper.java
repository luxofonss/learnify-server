package com.quyennv.lms.presenter.rest.mapper.course;

import com.quyennv.lms.core.domain.entities.Identity;
import com.quyennv.lms.core.domain.enums.CourseInfoType;
import com.quyennv.lms.core.domain.enums.EnrollStatus;
import com.quyennv.lms.core.usecases.course.CourseUpdateUseCase;
import com.quyennv.lms.presenter.rest.dto.course.*;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
public abstract class UpdateCourseUseCaseInputMapper {
    public static List<CourseUpdateUseCase.CourseInfoInput> mapInfos(List<UpdateCourseRequestCourseInfo> infos) {
        if (Objects.nonNull(infos)) {
            return infos.stream().map(
                    i -> CourseUpdateUseCase.CourseInfoInput
                            .builder()
                            .id(Objects.nonNull(i.getId()) ? Identity.fromString(i.getId()) : null)
                            .content(i.getContent())
                            .type(CourseInfoType.valueOf(i.getType()))
                            .build()
            ).toList();
        } else {
            return new ArrayList<>();
        }
    }

    public static List<CourseUpdateUseCase.CourseSection> mapSections(List<UpdateCourseRequestSection> sections) {
        if (Objects.nonNull(sections)) {
            return sections.stream().map(
                    s -> CourseUpdateUseCase.CourseSection
                            .builder()
                            .id(Objects.nonNull(s.getId()) ? Identity.fromString(s.getId()) : null)
                            .name(s.getName())
                            .description(s.getDescription())
                            .lectures(mapLectures(s.getLectures()))
                            .build()
            ).toList();

        } else {
            return new ArrayList<>();
        }
    }

    public static List<CourseUpdateUseCase.CourseLecture> mapLectures(List<UpdateCourseRequestLecture> lectures) {
        if (Objects.nonNull(lectures)) {
            return lectures.stream().map(
                    l -> CourseUpdateUseCase.CourseLecture
                            .builder()
                            .id(Objects.nonNull(l.getId()) ? Identity.fromString(l.getId()) : null)
                            .name(l.getName())
                            .description(l.getDescription())
                            .build()
            ).toList();

        } else {
            return new ArrayList<>();
        }
    }

    public static List<CourseUpdateUseCase.CourseStudent> mapStudents(List<UpdateCourseRequestCourseStudent> students) {
        if (Objects.nonNull(students)) {
            return students.stream().map(
                    s -> CourseUpdateUseCase.CourseStudent
                            .builder()
                            .studentId(Identity.fromString(s.getStudentId()))
                            .status(EnrollStatus.valueOf(s.getStatus()))
                            .price(s.getPrice())
                            .build()
            ).toList();

        } else {
            return new ArrayList<>();
        }
    }
}
