package com.quyennv.lms.presenter.rest.dto.course;

import com.quyennv.lms.core.domain.enums.EnrollStatus;
import com.quyennv.lms.presenter.config.annotations.ValueOfEnum;
import lombok.Value;

@Value
public class UpdateCourseRequestCourseStudent {
    String id;
    String studentId;
    @ValueOfEnum(enumClass = EnrollStatus.class)
    String status;
    Integer price;
}
