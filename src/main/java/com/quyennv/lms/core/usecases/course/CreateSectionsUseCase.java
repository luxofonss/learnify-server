package com.quyennv.lms.core.usecases.course;

import com.quyennv.lms.core.domain.entities.Course;
import com.quyennv.lms.core.domain.entities.Section;

import java.util.List;

public class CreateSectionsUseCase extends CourseUpdateUseCase{
    public CreateSectionsUseCase(CourseRepository courseRepository) {
        super(courseRepository);
    }

    @Override
    protected Course update(Course course, InputValues input) {
        List<Section> sections = super.mapSection(input.getSections());
        return course.addSections(sections);
    }
}
