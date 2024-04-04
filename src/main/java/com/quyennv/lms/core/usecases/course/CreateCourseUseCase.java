package com.quyennv.lms.core.usecases.course;

import com.quyennv.lms.core.domain.entities.*;
import com.quyennv.lms.core.domain.enums.CourseInfoType;
import com.quyennv.lms.core.domain.enums.CourseLevel;
import com.quyennv.lms.core.usecases.UseCase;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CreateCourseUseCase extends UseCase<CreateCourseUseCase.InputValues, CreateCourseUseCase.OutputValues> {
    private final CourseRepository courseRepository;

    public CreateCourseUseCase(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public OutputValues execute(InputValues input) {
        Course course = createCourse(input);
        return new OutputValues(courseRepository.persist(course));
    }

    private Course createCourse(InputValues input) {
        Course course = Course
                .builder()
                .id(Identity.newIdentity())
                .name(input.getName())
                .description(input.getDescription())
                .backgroundImage(input.getBackgroundImage())
                .thumbnail(input.getThumbnail())
                .startDate(input.getStartDate())
                .endDate(input.getEndDate())
                .price(input.getPrice())
                .level(input.getLevel())
                .isVerified(false)
                .grade(input.getGrade())
                .teacher(User.builder().id(input.getCreatorId()).build())
                .subject(Subject.builder().id(input.getSubjectId()).build())
//                .code("random")
                .build();

        if (Objects.nonNull(input.getCourseInfos())) {
            List<CourseInfo> courseInfos = input.getCourseInfos().stream().map(
                    i -> CourseInfo
                                .builder()
                                .content(i.getContent())
                                .type(i.getType())
                                .build()
            ).toList();
            course.setCourseInfos(courseInfos);
        }

        if (Objects.nonNull(input.getSections())) {
            List<Section> sections = input.getSections().stream().map(
                    s -> {
                        List<Lecture> lectures = new ArrayList<>();
                        if (Objects.nonNull(s.getLectures())) {
                            lectures.addAll(s.getLectures().stream().map(
                                    l -> Lecture
                                            .builder()
                                            .name(l.getName())
                                            .description(l.getDescription())
                                            .build()
                            ).toList());
                        }

                        return Section
                                .builder()
                                .name(s.getName())
                                .description(s.getDescription())
                                .lectures(lectures)
                                .build();
                    }
            ).toList();

            course.setSections(sections);
        }

        return course;
    }

    @Value
    @Builder
    public static class InputValues implements UseCase.InputValues {
        String name;
        String description;
        String backgroundImage;
        String thumbnail;
        LocalDateTime startDate;
        LocalDateTime endDate;
        Long price;
        CourseLevel level;
        Integer grade;
        Identity subjectId;
        Identity creatorId;
        List<CourseInfoInput> courseInfos;
        List<CourseSection> sections;
    }

    @Value
    public static class OutputValues implements UseCase.OutputValues {
        Course course;
    }

    @Value
    @Builder
    public static class CourseInfoInput {
        String content;
        CourseInfoType type;
    }

    @Value
    @Builder
    public static class CourseSection {
        String name;
        String description;
        List<CourseLecture> lectures;
    }

    @Value
    @Builder
    public static class CourseLecture {
        String name;
        String description;
    }
}
