package com.quyennv.lms.core.domain.entities;

import com.quyennv.lms.core.domain.enums.AssignmentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Assignment {
    private Identity id;
    private String title;
    private String description;
    private Integer totalMark;
    private User teacher;
    private Subject subject;
    private List<Question> questions;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
