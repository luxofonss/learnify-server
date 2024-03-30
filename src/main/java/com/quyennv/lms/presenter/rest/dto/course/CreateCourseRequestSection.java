package com.quyennv.lms.presenter.rest.dto.course;

import jakarta.validation.constraints.NotBlank;
import lombok.Value;

import java.util.List;

@Value
public class CreateCourseRequestSection {
    @NotBlank
    String name;
    String description;
    List<CreateCourseRequestLecture> lectures;
}
