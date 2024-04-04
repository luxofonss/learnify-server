package com.quyennv.lms.core.usecases.course;

import com.quyennv.lms.core.domain.entities.Course;
import com.quyennv.lms.core.domain.entities.Subject;

import java.util.Objects;

public class UpdateCourseDetailUseCase extends CourseUpdateUseCase{
    public UpdateCourseDetailUseCase(CourseRepository courseRepository) {
        super(courseRepository);
    }

    @Override
    protected Course update(Course course, InputValues input) {
        Course updateCourse = Course
                .builder()
                .id(input.getCourseId())
                .name(input.getName())
                .description(input.getDescription())
                .backgroundImage(input.getBackgroundImage())
                .thumbnail(input.getThumbnail())
                .startDate(input.getStartDate())
                .endDate(input.getEndDate())
                .price(input.getPrice())
                .level(input.getLevel())
                .grade(input.getGrade())
                .subject(Objects.nonNull(input.getSubjectId()) ? Subject.builder().id(input.getSubjectId()).build() : null)
                .courseInfos(mapCourseInfos(input.getCourseInfos()))
                .sections(mapSection(input.getSections()))
                .build();
        return course.updateCourse(updateCourse);
    }
}
