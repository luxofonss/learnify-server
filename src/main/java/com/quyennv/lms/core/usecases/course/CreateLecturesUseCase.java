package com.quyennv.lms.core.usecases.course;

import com.quyennv.lms.core.domain.entities.Course;
import com.quyennv.lms.core.domain.entities.Lecture;

import java.util.List;

public class CreateLecturesUseCase extends CourseUpdateUseCase{
    public CreateLecturesUseCase(CourseRepository courseRepository) {
        super(courseRepository);
    }

    @Override
    protected Course update(Course course, InputValues input) {
        List<Lecture> lectures = super.mapLectures(input.getLectures());

        System.out.println("lectures:: " + lectures);
        return course.addLectures(lectures, input.getSectionId());
    }
}
