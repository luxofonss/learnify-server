package com.quyennv.lms.core.usecases.course;


import com.quyennv.lms.core.domain.entities.Course;
import com.quyennv.lms.core.domain.entities.Identity;
import com.quyennv.lms.core.domain.entities.Lecture;
import com.quyennv.lms.core.domain.entities.Section;
import com.quyennv.lms.core.domain.enums.CourseInfoType;
import com.quyennv.lms.core.domain.enums.CourseLevel;
import com.quyennv.lms.core.domain.enums.EnrollStatus;
import com.quyennv.lms.core.usecases.UseCase;
import lombok.Builder;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
public abstract class CourseUpdateUseCase extends UseCase<CourseUpdateUseCase.InputValues, CourseUpdateUseCase.OutputValues> {
    public final CourseRepository courseRepository;

    protected CourseUpdateUseCase(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public OutputValues execute(InputValues input) {
        Course existCourse = courseRepository.findById(input.getCourseId()).orElseThrow(
                () -> new RuntimeException("No course found")
        );

        if (!existCourse.getTeacher().getId().equals(input.getRequesterId())) {
            throw new RuntimeException("No permission");
        }

        Course course = update(existCourse, input);
        log.info("course:: {}", course);
        return persistAndReturn(course);
    }

    private OutputValues persistAndReturn(Course course) {
        return new OutputValues(
                courseRepository.persist(course)
        );
    }

    protected abstract Course update(Course course, CourseUpdateUseCase.InputValues input);

    public List<Section> mapSection(List<CourseSection> courseSections) {
        return courseSections.stream().map(
                section -> {
                    Section returnSection = Section
                            .builder()
                            .id(section.getId())
                            .name(section.getName())
                            .description(section.getDescription())
                            .build();
                    List<Lecture> lectures = new ArrayList<>();
                    if (Objects.nonNull(section.getLectures())) {
                        lectures.addAll(mapLectures(section.getLectures()));
                    }
                    returnSection.setLectures(lectures);

                    return returnSection;
                }
        ).toList();
    }

    protected List<Lecture> mapLectures(List<CourseLecture> lectures) {
        return lectures.stream().map(
                l -> Lecture
                        .builder()
                        .id(l.getId())
                        .name(l.getName())
                        .description(l.getDescription())
                        .build()
        ).toList();
    }

    @Value
    @Builder
    public static class InputValues implements  UseCase.InputValues {
        Identity courseId;
        Identity sectionId;
        Identity lectureId;
        Identity requesterId;
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
        List<CourseUpdateUseCase.CourseInfoInput> courseInfos;
        List<CourseUpdateUseCase.CourseSection> sections;
        List<CourseUpdateUseCase.CourseStudent> students;
        List<CourseUpdateUseCase.CourseLecture> lectures;
    }

    @Value
    public static class OutputValues implements  UseCase.OutputValues {
        Course course;
    }

    @Value
    @Builder
    public static class CourseInfoInput {
        Identity id;
        String content;
        CourseInfoType type;
    }

    @Value
    @Builder
    public static class CourseSection {
        Identity id;
        String name;
        String description;
        List<CourseUpdateUseCase.CourseLecture> lectures;
    }

    @Value
    @Builder
    public static class CourseLecture {
        Identity id;
        String name;
        String description;
    }

    @Value
    @Builder
    public static class CourseStudent {
        Identity studentId;
        EnrollStatus status;
        Integer price;
    }
}
