package com.quyennv.lms.data.db.jpa.entities;

import com.quyennv.lms.core.domain.entities.Identity;
import com.quyennv.lms.core.domain.entities.Lecture;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Objects;

@EqualsAndHashCode(callSuper = true)
@Entity(name="lectures")
@Data
@ToString(exclude = {"section"})
@Table(name="lectures")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LectureData extends BaseEntity {
    private String name;
    private String description;
    private String background;

    @OneToMany(mappedBy = "lecture")
    private List<AssignmentPlacementData> assignmentPlacements;

    @ManyToOne
    @JoinColumn(name="section_id", nullable = false)
    private SectionData section;

    public static LectureData from(Lecture l) {
        LectureData result = LectureData
                .builder()
                .name(l.getName())
                .description(l.getDescription())
                .background(l.getBackground())
                .build();


        result.setCreatedAt(l.getCreatedAt());
        result.setUpdatedAt(l.getUpdatedAt());
        result.setDeletedAt(l.getDeletedAt());

        if (Objects.nonNull(l.getId())) {
            result.setId(l.getId().getId());
        }

        if (Objects.nonNull(l.getSection())) {
            result.setSection(SectionData.from(l.getSection()));
        }

        if (Objects.nonNull(l.getAssignmentPlacements())) {
            result.setAssignmentPlacements(l.getAssignmentPlacements().stream().map(AssignmentPlacementData::from).toList());
        }

        return result;
    }

    public Lecture fromThis() {
        Lecture result = Lecture
                .builder()
                .name(this.name)
                .description(this.description)
                .build();

        if (Objects.nonNull(this.getId())) {
            result.setId(Identity.from(this.getId()));
        }

        if (Objects.nonNull(this.assignmentPlacements)) {
            result.setAssignmentPlacements(this.assignmentPlacements.stream().map(AssignmentPlacementData::fromThis).toList());
        }

        return result;
    }
}
