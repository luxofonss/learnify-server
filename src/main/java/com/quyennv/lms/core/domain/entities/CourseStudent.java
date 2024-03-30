package com.quyennv.lms.core.domain.entities;

import com.quyennv.lms.core.domain.enums.EnrollStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CourseStudent {
    private Course course;
    private User student;
    private Integer price;
    private EnrollStatus status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
