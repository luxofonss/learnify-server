package com.quyennv.lms.presenter.rest.dto.course;

import com.quyennv.lms.core.domain.enums.CourseInfoType;
import com.quyennv.lms.presenter.config.annotations.ValueOfEnum;
import jakarta.validation.constraints.NotBlank;
import lombok.Value;

@Value
public class UpdateCourseRequestCourseInfo {
    String id;
    @NotBlank
    String content;
    @NotBlank
    @ValueOfEnum(enumClass = CourseInfoType.class)
    String type;
}
