package com.quyennv.lms.core.usecases.course;

import com.quyennv.lms.core.domain.entities.Course;
import com.quyennv.lms.core.domain.entities.Lecture;

import java.util.List;

public class UpdateLecturesUseCase extends CourseUpdateUseCase{
    public UpdateLecturesUseCase(CourseRepository courseRepository) {
        super(courseRepository);
    }

    @Override
    protected Course update(Course course, InputValues input) {
        List<Lecture> lectures = super.mapLectures(input.getLectures());
        return course.updateLectures(lectures, input.getSectionId());
    }
}
