package com.quyennv.lms.data.db.jpa.entities;

import com.quyennv.lms.core.domain.enums.QuestionLevel;
import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity(name="question_placement")
@Data
@Table(name="question_placement")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionPlacementData extends BaseEntity{
    @Enumerated(EnumType.STRING)
    private QuestionLevel level;
    @Column(name="ord")
    private Integer order;
    private Integer mark;

    @ManyToOne
    @JoinColumn(name="question_id", nullable = false)
    private QuestionData question;

    @ManyToOne
    @JoinColumn(name="assignment_id", nullable = false)
    private AssignmentData assignment;

    @ManyToOne
    @JoinColumn(name="creator_id", nullable = false)
    private UserData creator;
}
