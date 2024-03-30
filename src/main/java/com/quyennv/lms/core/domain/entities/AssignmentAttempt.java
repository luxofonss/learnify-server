package com.quyennv.lms.core.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AssignmentAttempt {
    private Identity id;
    private User student;
    private AssignmentPlacement assignmentPlacement;
    private List<QuestionAnswer> answers;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String teacherComment;
    private Integer totalMark;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
