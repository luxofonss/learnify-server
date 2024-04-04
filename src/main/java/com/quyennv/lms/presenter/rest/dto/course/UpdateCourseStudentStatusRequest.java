package com.quyennv.lms.presenter.rest.dto.course;

import com.quyennv.lms.core.domain.enums.EnrollStatus;
import com.quyennv.lms.presenter.config.annotations.ValueOfEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCourseStudentStatusRequest {
    @ValueOfEnum(enumClass = EnrollStatus.class, message = "Invalid status")
    private String status;
}
