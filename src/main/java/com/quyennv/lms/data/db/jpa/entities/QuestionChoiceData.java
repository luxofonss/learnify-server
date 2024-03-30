package com.quyennv.lms.data.db.jpa.entities;

import com.quyennv.lms.core.domain.entities.Identity;
import com.quyennv.lms.core.domain.entities.QuestionChoice;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Objects;

@EqualsAndHashCode(callSuper = true)
@Entity(name="question_choices")
@Data
@Table(name="question_choices")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionChoiceData extends BaseEntity {
    private String content;
    @Column(name="ord")
    private Integer order;
    @Column(name="is_correct")
    private Boolean isCorrect;
    private String explanation;
    @ManyToOne
    @JoinColumn(name="question_id")
    private QuestionData question;

    @ManyToMany
    private List<QuestionAnswerData> chosenAnswers;

    public static QuestionChoiceData from (QuestionChoice c) {
        QuestionChoiceData result = QuestionChoiceData
                .builder()
                .content(c.getContent())
                .order(c.getOrder())
                .isCorrect(c.getIsCorrect())
                .explanation(c.getExplanation())
                .question(QuestionData.from(c.getQuestion()))
                .build();

        if (Objects.nonNull(c.getChosenAnswers())) {
            result.setChosenAnswers(c.getChosenAnswers().stream().map(QuestionAnswerData::from).toList());
        }

        return result;
    }

    public QuestionChoice fromThis() {
        QuestionChoice result = QuestionChoice
                .builder()
                .id(Identity.from(this.getId()))
                .order(this.order)
                .isCorrect(this.isCorrect)
                .content(this.content)
                .explanation(this.explanation)
                .question(this.question.fromThis())
                .createdAt(this.getCreatedAt())
                .updatedAt(this.getUpdatedAt())
                .deletedAt(this.getDeletedAt())
                .build();

        if (Objects.nonNull(this.getChosenAnswers())) {
            result.setChosenAnswers(this.getChosenAnswers().stream().map(QuestionAnswerData::fromThis).toList());
        }

        return result;
    }
}