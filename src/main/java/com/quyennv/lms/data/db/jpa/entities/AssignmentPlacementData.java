package com.quyennv.lms.data.db.jpa.entities;

import com.quyennv.lms.core.domain.entities.AssignmentPlacement;
import com.quyennv.lms.core.domain.entities.Identity;
import com.quyennv.lms.core.domain.enums.AssignmentPlacementType;
import com.quyennv.lms.core.domain.enums.AssignmentType;
import com.quyennv.lms.core.domain.enums.AttemptType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;

@EqualsAndHashCode(callSuper = true)
@Entity(name="assignment_placements")
@Setter
@Getter
@ToString(exclude = {"assignment","course","section","lecture","creator"})
@Table(name="assignment_placements")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AssignmentPlacementData extends BaseEntity  {
    @Enumerated(EnumType.STRING)
    private AssignmentPlacementType type;
    private Long duration;
    @Column(name="start_time")
    private LocalDateTime startTime;
    @Column(name="end_time")
    private LocalDateTime endTime;
    @Enumerated(EnumType.STRING)
    private AssignmentType assignmentType;
    @Enumerated(EnumType.STRING)
    @Column(name="attempt_type")
    private AttemptType attemptType;

    @ManyToOne
    @JoinColumn(name="assignment_id")
    private AssignmentData assignment;
    @ManyToOne
    @JoinColumn(name="course_id")
    private CourseData course;
    @ManyToOne
    @JoinColumn(name="section_id")
    private SectionData section;
    @ManyToOne
    @JoinColumn(name="lecture_id")
    private LectureData lecture;
    @ManyToOne
    @JoinColumn(name="creator_id")
    private UserData creator;

    public static AssignmentPlacementData from(AssignmentPlacement ap) {
        AssignmentPlacementData result = AssignmentPlacementData.builder()
                .duration(ap.getDuration())
                .startTime(ap.getStartTime())
                .endTime(ap.getEndTime())
                .type(ap.getType())
                .assignmentType(ap.getAssignmentType())
                .assignmentType(ap.getAssignmentType())
                .assignment(Objects.nonNull(ap.getAssignment()) ? AssignmentData.from(ap.getAssignment()) : null)
                .course(Objects.nonNull(ap.getCourse()) ? CourseData.from(ap.getCourse()) : null)
                .section(Objects.nonNull(ap.getSection()) ? SectionData.from(ap.getSection()) : null)
                .lecture(Objects.nonNull(ap.getLecture()) ? LectureData.from(ap.getLecture()) : null)
                .creator(Objects.nonNull(ap.getCreator()) ?  UserData.from(ap.getCreator()) : null)
                .build();

        if(Objects.nonNull(ap.getId())) {
            result.setId(ap.getId().getId());
        }
        result.setCreatedAt(ap.getCreatedAt());
        result.setUpdatedAt(ap.getUpdatedAt());
        result.setDeletedAt(ap.getDeletedAt());

        return result;
    }

    public AssignmentPlacement fromThis() {
        AssignmentPlacement result = AssignmentPlacement.builder()
                .id(Identity.from(this.getId()))
                .type(this.type)
                .assignmentType(this.assignmentType)
                .duration(this.duration)
                .startTime(this.startTime)
                .endTime(this.endTime)
                .assignmentType(this.assignmentType)
                .attemptType(this.attemptType)
                .assignment(Objects.nonNull(this.assignment) ? this.assignment.fromThis() : null)
                .creator(Objects.nonNull(this.creator) ? this.creator.fromThis() : null)
                .createdAt(this.getCreatedAt())
                .updatedAt(this.getUpdatedAt())
                .deletedAt(this.getDeletedAt())
                .build();
//
//        if (Objects.nonNull(this.course)) {
//            result.setCourse(this.course.fromThis());
//        }

        if (Objects.nonNull(this.section)) {
            result.setSection(this.section.fromThis());
        }

        if (Objects.nonNull(this.lecture)) {
            result.setLecture(this.lecture.fromThis());
        }

        return result;
    }
}
