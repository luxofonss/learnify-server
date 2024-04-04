package com.quyennv.lms.presenter.rest.dto.course;

import com.quyennv.lms.core.domain.enums.CourseLevel;
import com.quyennv.lms.presenter.config.annotations.ValueOfEnum;
import jakarta.validation.constraints.Null;
import lombok.Value;

import java.util.List;

@Value
public class GetCoursesRequest {
    String keyword;
    @Null
    @ValueOfEnum(enumClass = CourseLevel.class)
    String level;
    Integer grade;
    String code;
    List<String> teacherIds;
}
