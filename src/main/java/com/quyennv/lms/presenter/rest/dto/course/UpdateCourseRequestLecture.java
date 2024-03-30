package com.quyennv.lms.presenter.rest.dto.course;

import jakarta.validation.constraints.NotBlank;
import lombok.Value;

@Value
public class UpdateCourseRequestLecture {
    String id;
    @NotBlank
    String name;
    String description;
}
