package com.quyennv.lms.presenter.rest.dto.course;

import com.quyennv.lms.core.domain.enums.CourseLevel;
import com.quyennv.lms.presenter.config.annotations.ValueOfEnum;
import jakarta.validation.constraints.NotBlank;
import lombok.Value;

import java.util.List;

@Value
public class CreateCourseRequest {
    @NotBlank
    String name;
    @NotBlank
    String description;
    String backgroundImage;
    String thumbnail;
    String startDate;
    String endDate;
    Long price;
    @ValueOfEnum(enumClass = CourseLevel.class)
    String level;
    Integer grade;
    String subjectId;
    List<CreateCourseRequestCourseInfo> courseInfos;
    List<CreateCourseRequestSection> sections;
}