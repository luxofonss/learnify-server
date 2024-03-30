package com.quyennv.lms.data.db.jpa.entities;

import com.quyennv.lms.core.domain.entities.Identity;
import com.quyennv.lms.core.domain.entities.QuestionAnswer;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Objects;

@EqualsAndHashCode(callSuper = true)
@Entity(name="question_answer")
@Data
@Table(name="question_answer")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionAnswerData extends BaseEntity{
    @Column(name="text_answer")
    private String textAnswer;
    private Integer score;

    @ManyToOne
    @JoinColumn(name="question_id")
    private QuestionData question;
    @ManyToOne
    @JoinColumn(name="creator_id")
    private UserData creator;
    @ManyToMany
    @JoinTable(
            name="question_answer_quetion_choices",
            joinColumns = @JoinColumn(name="question_answer_id"),
            inverseJoinColumns = @JoinColumn(name="question_choice_id")
    )
    private List<QuestionChoiceData> selectedOptions;
    @OneToMany(mappedBy = "answer")
    private List<QuestionAnswerFeedbackData> feedbacks;
    @ManyToOne
    @JoinColumn(name="assignment_attempt_id")
    private AssignmentAttemptData assignmentAttempt;

    public static QuestionAnswerData from(QuestionAnswer qa) {
        QuestionAnswerData result = QuestionAnswerData
                .builder()
                .textAnswer(qa.getTextAnswer())
                .question(QuestionData.from(qa.getQuestion()))
                .creator(UserData.from(qa.getCreator()))
                .build();

        if (Objects.nonNull(qa.getSelectedOptions())) {
            result.setSelectedOptions(qa.getSelectedOptions().stream().map(s -> QuestionChoiceData.from(s)).toList());
        }

        if (Objects.nonNull(qa.getFeedbacks())) {
            result.setFeedbacks(qa.getFeedbacks().stream().map(f -> QuestionAnswerFeedbackData.from(f)).toList());
        }

        result.setId(qa.getId().getId());
        result.setCreatedAt(qa.getCreatedAt());
        result.setUpdatedAt(qa.getUpdatedAt());
        result.setDeletedAt(qa.getDeletedAt());

        return result;
    }

    public QuestionAnswer fromThis() {
        QuestionAnswer result = QuestionAnswer
                .builder()
                .id(Identity.from(this.getId()))
                .textAnswer(this.textAnswer)
                .score(this.score)
                .creator(this.creator.fromThis())
                .question(this.question.fromThis())
                .createdAt(this.getCreatedAt())
                .updatedAt(this.getUpdatedAt())
                .deletedAt(this.getDeletedAt())
                .build();

        if (Objects.nonNull(this.getSelectedOptions())) {
            result.setSelectedOptions(this.selectedOptions.stream().map(QuestionChoiceData::fromThis).toList());
        }

        if (Objects.nonNull(this.getFeedbacks())) {
            result.setFeedbacks(this.feedbacks.stream().map(QuestionAnswerFeedbackData::fromThis).toList());
        }

        return result;
    }
}
