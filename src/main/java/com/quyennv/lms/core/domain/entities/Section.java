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
public class Section {
    private Identity id;
    private String name;
    private String description;
    private List<AssignmentPlacement> assignmentPlacements;
    private Course course;
    private List<Lecture> lectures;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    public Section delete() {
        this.setDeletedAt(LocalDateTime.now());
        return this;
    }
}
