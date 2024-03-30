package com.quyennv.lms.core.domain.entities;

import com.quyennv.lms.core.domain.enums.AssignmentPlacementType;
import com.quyennv.lms.core.domain.enums.AssignmentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AssignmentPlacement {
    private Identity id;
    private AssignmentPlacementType type;
    private Long duration;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private AssignmentType assignmentType;
    private Assignment assignment;
    private Course course;
    private Section section;
    private Lecture lecture;
    private User creator;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
