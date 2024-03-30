package com.quyennv.lms.data.db.jpa.entities;

import com.quyennv.lms.core.domain.entities.Assignment;
import com.quyennv.lms.core.domain.entities.Identity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity(name="assignments")
@Data
@Table(name="assignments")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AssignmentData extends BaseEntity{
    private String title;
    private String description;
    @Column(name="total_marks")
    private Integer totalMarks;

    @OneToMany(mappedBy = "assignment")
    private List<QuestionData> questions;
    @ManyToOne
    @JoinColumn(name="teacher_id", nullable = false)
    private UserData teacher;
    @ManyToOne
    @JoinColumn(name="subject_id", nullable = false)
    private SubjectData subject;

    public static AssignmentData from(Assignment assignment) {
        AssignmentData assignmentData = AssignmentData.builder()
                .title(assignment.getTitle())
                .description(assignment.getDescription())
                .totalMarks(assignment.getTotalMark())
                .teacher(UserData.from(assignment.getTeacher()))
                .subject(SubjectData.from(assignment.getSubject()))
                .build();

        assignmentData.setId(assignment.getId().getId());
        assignmentData.setCreatedAt(assignment.getCreatedAt());
        assignmentData.setUpdatedAt(assignment.getUpdatedAt());
        assignmentData.setDeletedAt(assignment.getDeletedAt());

        return assignmentData;
    }

    public Assignment fromThis() {
        return Assignment
                .builder()
                .id(Identity.from(this.getId()))
                .title(this.title)
                .description(this.description)
                .totalMark(this.totalMarks)
                .teacher(this.teacher.fromThis())
                .subject(this.subject.fromThis())
                .questions(this.questions.stream().map(QuestionData::fromThis).toList())
                .createdAt(this.getCreatedAt())
                .updatedAt(this.getUpdatedAt())
                .deletedAt(this.getDeletedAt())
                .build();
    }
}
