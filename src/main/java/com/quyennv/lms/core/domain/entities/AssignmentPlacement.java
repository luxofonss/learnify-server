package com.quyennv.lms.core.domain.entities;

import com.quyennv.lms.core.domain.enums.AssignmentPlacementType;
import com.quyennv.lms.core.domain.enums.AssignmentType;
import com.quyennv.lms.core.domain.enums.AttemptType;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AssignmentPlacement {
    private Identity id;
    private AssignmentPlacementType type;
    private Long duration;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private AssignmentType assignmentType;
    private AttemptType attemptType;
    private Assignment assignment;
    private Course course;
    private Section section;
    private Lecture lecture;
    private User creator;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
