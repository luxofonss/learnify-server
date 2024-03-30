package com.quyennv.lms.data.db.jpa.entities;

import com.quyennv.lms.core.domain.entities.AssignmentAttempt;
import com.quyennv.lms.core.domain.entities.Identity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@EqualsAndHashCode(callSuper = true)
@Entity(name="assignment_attempts")
@Data
@Table(name="assignment_attempts")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AssignmentAttemptData extends BaseEntity{
    @Column(name="start_time")
    private LocalDateTime startTime;
    @Column(name="end_time")
    private LocalDateTime endTime;
    @Column(name="teacher_comment")
    private String teacherComment;
    @Column(name="total_marks")
    private Integer totalMark;

    @OneToMany
    private List<QuestionAnswerData> answers;
    @ManyToOne
    @JoinColumn(name="assignment_placement_id")
    private AssignmentPlacementData assignmentPlacement;
    @ManyToOne
    @JoinColumn(name="student_id")
    private UserData student;

    public static AssignmentAttemptData from(AssignmentAttempt aa) {
        AssignmentAttemptData result = AssignmentAttemptData.builder()
                .startTime(aa.getStartTime())
                .endTime(aa.getEndTime())
                .teacherComment(aa.getTeacherComment())
                .totalMark(aa.getTotalMark())
                .assignmentPlacement(AssignmentPlacementData.from(aa.getAssignmentPlacement()))
                .student(UserData.from(aa.getStudent()))
                .build();

        if (Objects.nonNull(aa.getAnswers())) {
            result.setAnswers(aa.getAnswers().stream().map(QuestionAnswerData::from).toList());
        }

        result.setId(aa.getId().getId());
        result.setCreatedAt(aa.getCreatedAt());
        result.setUpdatedAt(aa.getUpdatedAt());
        result.setDeletedAt(aa.getDeletedAt());

        return result;
    }

    public AssignmentAttempt fromThis() {
        return AssignmentAttempt.builder()
                .id(Identity.from(this.getId()))
                .student(this.student.fromThis())
                .assignmentPlacement(this.assignmentPlacement.fromThis())
                .answers(this.getAnswers().stream().map(a->a.fromThis()).toList())
                .startTime(this.startTime)
                .endTime(this.endTime)
                .createdAt(this.getCreatedAt())
                .updatedAt(this.getUpdatedAt())
                .deletedAt(this.getDeletedAt())
                .build();
    }
}
