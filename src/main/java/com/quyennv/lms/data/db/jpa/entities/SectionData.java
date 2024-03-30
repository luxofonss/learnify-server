package com.quyennv.lms.data.db.jpa.entities;

import com.quyennv.lms.core.domain.entities.Identity;
import com.quyennv.lms.core.domain.entities.Section;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Objects;

@EqualsAndHashCode(callSuper = true)
@Entity(name="sections")
@Data
@ToString(exclude = {"course"})
@Table(name="sections")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Slf4j
public class SectionData extends BaseEntity {
    private String name;
    private String description;

    @OneToMany(mappedBy = "section")
    private List<AssignmentPlacementData> assignmentPlacements;
    @ManyToOne
    @JoinColumn(name="course_id", nullable = false)
    private CourseData course;
    @OneToMany(mappedBy = "section", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<LectureData> lectures;

    public static SectionData from(Section s) {
        SectionData result = SectionData
                .builder()
                .name(s.getName())
                .description(s.getDescription())
                .build();

        if (Objects.nonNull(s.getId())) {
            result.setId(s.getId().getId());
        }

        result.setCreatedAt(s.getCreatedAt());
        result.setUpdatedAt(s.getUpdatedAt());
        result.setDeletedAt(s.getDeletedAt());

        if (Objects.nonNull(s.getCourse())) {
            result.setCourse(CourseData.from(s.getCourse()));
        }

        if (Objects.nonNull(s.getAssignmentPlacements())) {
            result.setAssignmentPlacements(s.getAssignmentPlacements().stream().map(AssignmentPlacementData::from).toList());
        }

        if (Objects.nonNull(s.getLectures())) {
            result.setLectures(s.getLectures().stream().map(l -> {
                LectureData lecture = LectureData.from(l);
                lecture.setSection(result);
                return lecture;
            }).toList());
        }

        return result;
    }

    public Section fromThis() {
        Section result = Section
                .builder()
                .id(Identity.from(this.getId()))
                .name(this.getName())
                .description(this.getDescription())
                .createdAt(this.getCreatedAt())
                .updatedAt(this.getUpdatedAt())
                .deletedAt(this.getDeletedAt())
                .build();

        if (Objects.nonNull(this.assignmentPlacements)) {
            result.setAssignmentPlacements(this.assignmentPlacements.stream().map(AssignmentPlacementData::fromThis).toList());
        }
        if (Objects.nonNull(this.lectures)) {
            result.setLectures(this.lectures.stream().map(LectureData::fromThis).toList());
        }

        return result;
    }
}
