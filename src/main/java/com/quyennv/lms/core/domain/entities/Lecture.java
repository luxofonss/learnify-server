package com.quyennv.lms.core.domain.entities;

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
public class Lecture {
    private Identity id;

    private String name;
    private String description;
    private String background;

    private Section section;
    private List<AssignmentPlacement> assignmentPlacements;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    public void delete() {
        this.setDeletedAt(LocalDateTime.now());
    }
}
