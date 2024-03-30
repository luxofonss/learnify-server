package com.quyennv.lms.data.db.jpa.entities;

import com.quyennv.lms.core.domain.entities.Identity;
import com.quyennv.lms.core.domain.entities.QuestionAnswerFeedback;
import com.quyennv.lms.core.domain.enums.QuestionAnswerFeedbackType;
import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity(name="question_answer_feedbacks")
@Data
@Table(name="question_answer_feedbacks")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionAnswerFeedbackData extends BaseEntity{
    private String message;
    @Enumerated(EnumType.STRING)
    private QuestionAnswerFeedbackType type;
    @ManyToOne
    @JoinColumn(name="creator_id")
    private UserData creator;

    @ManyToOne
    @JoinColumn(name="question_id")
    private QuestionAnswerData answer;

    public static QuestionAnswerFeedbackData from (QuestionAnswerFeedback f) {
        QuestionAnswerFeedbackData result = QuestionAnswerFeedbackData
                .builder()
                .message(f.getMessage())
                .type(f.getType())
                .creator(UserData.from(f.getCreator()))
                .answer(QuestionAnswerData.from(f.getAnswer()))
                .build();

        result.setId(f.getId().getId());
        result.setCreatedAt(f.getCreatedAt());
        result.setUpdatedAt(f.getUpdatedAt());
        result.setDeletedAt(f.getDeletedAt());

        return result;
    }

    public QuestionAnswerFeedback fromThis() {
        return QuestionAnswerFeedback
                .builder()
                .id(Identity.from(this.getId()))
                .creator(this.getCreator().fromThis())
                .message(this.message)
                .type(this.type)
                .answer(this.answer.fromThis())
                .createdAt(this.getCreatedAt())
                .updatedAt(this.getUpdatedAt())
                .deletedAt(this.getDeletedAt())
                .build();
    }
}
