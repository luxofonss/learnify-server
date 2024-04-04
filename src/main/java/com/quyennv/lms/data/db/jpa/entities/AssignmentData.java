package com.quyennv.lms.data.db.jpa.entities;

import com.quyennv.lms.core.domain.entities.Assignment;
import com.quyennv.lms.core.domain.entities.Identity;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Objects;

@EqualsAndHashCode(callSuper = true)
@Entity(name="assignments")
@Getter
@Setter
@Table(name="assignments")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Slf4j
public class AssignmentData extends BaseEntity{
    private String title;
    private String description;
    @Column(name="total_marks")
    private Integer totalMarks;

    @OneToMany(mappedBy = "assignment", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<QuestionData> questions;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="teacher_id", nullable = false)
    private UserData teacher;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="subject_id", nullable = false)
    private SubjectData subject;
    @OneToMany(mappedBy = "assignment", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<AssignmentPlacementData> placements;

    public static AssignmentData from(Assignment assignment) {
        AssignmentData assignmentData = AssignmentData.builder()
                .title(assignment.getTitle())
                .description(assignment.getDescription())
                .totalMarks(assignment.getTotalMark())
                .teacher(Objects.nonNull(assignment.getTeacher()) ? UserData.from(assignment.getTeacher()) : null)
                .subject(Objects.nonNull(assignment.getSubject()) ? SubjectData.from(assignment.getSubject()) : null)
                .build();

        if (Objects.nonNull(assignment.getId())) {
            assignmentData.setId(assignment.getId().getId());
        }

        if (Objects.nonNull(assignment.getQuestions())) {
            List<QuestionData> questions = assignment.getQuestions().stream().map(
                    q -> {
                        QuestionData question = QuestionData.from(q);
                        question.setAssignment(assignmentData);
                        return question;
                    }
            ).toList();
            assignmentData.setQuestions(questions);
        }

        if (Objects.nonNull(assignment.getPlacements())) {
            List<AssignmentPlacementData> placements = assignment.getPlacements().stream().map(
                    p -> {
                        AssignmentPlacementData placement = AssignmentPlacementData.from(p);
                        placement.setAssignment(assignmentData);
                        return placement;
                    }
            ).toList();
            assignmentData.setPlacements(placements);
        }

        assignmentData.setCreatedAt(assignment.getCreatedAt());
        assignmentData.setUpdatedAt(assignment.getUpdatedAt());
        assignmentData.setDeletedAt(assignment.getDeletedAt());

        return assignmentData;
    }

    public Assignment fromThis() {
        return Assignment
                .builder()
                .id(Objects.nonNull(this.getId()) ? Identity.from(this.getId()) : null)
                .title(this.title)
                .description(this.description)
                .totalMark(this.totalMarks)
                .teacher(Objects.nonNull(this.teacher) ? this.teacher.fromThis() : null)
                .subject(Objects.nonNull(this.subject) ? this.subject.fromThis() : null)
                .questions(Objects.nonNull(this.questions) && !this.questions.isEmpty()
                        ? this.questions.stream().map(QuestionData::fromThis).toList()
                        : null)
                .createdAt(this.getCreatedAt())
                .updatedAt(this.getUpdatedAt())
                .deletedAt(this.getDeletedAt())
                .build();
    }
}
