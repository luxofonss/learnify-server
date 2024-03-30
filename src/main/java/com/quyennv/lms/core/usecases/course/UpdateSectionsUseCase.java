package com.quyennv.lms.core.usecases.course;

import com.quyennv.lms.core.domain.entities.Course;
import com.quyennv.lms.core.domain.entities.Section;

import java.util.List;

public class UpdateSectionsUseCase extends CourseUpdateUseCase{
    public UpdateSectionsUseCase(CourseRepository courseRepository) {
        super(courseRepository);
    }

    @Override
    protected Course update(Course course, InputValues input) {
        List<Section> sections = super.mapSection(input.getSections());
        return course.updateSections(sections);
    }
}
