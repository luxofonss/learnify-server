package com.quyennv.lms.data.db.jpa.entities;

import com.quyennv.lms.core.domain.entities.Identity;
import com.quyennv.lms.core.domain.entities.Question;
import com.quyennv.lms.core.domain.enums.QuestionType;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Objects;

@EqualsAndHashCode(callSuper = true)
@Entity(name="questions")
@Data
@Table(name="questions")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionData extends BaseEntity{
    private String title;
    private String image;
    private String audio;
    private QuestionType type;
    @Column(name="answer_explaination")
    private String answerExplanation;

    @OneToMany(mappedBy="question")
    private List<QuestionChoiceData> choices;
    @OneToMany(mappedBy = "question")
    private List<QuestionTextAnswerData> textAnswers;
    @ManyToOne
    @JoinColumn(name="assignment_id")
    private AssignmentData assignment;
    @ManyToOne
    @JoinColumn(name="creator_id", nullable = false)
    private UserData creator;
    @ManyToOne
    @JoinColumn(name="subject_id")
    private SubjectData subject;

    public static QuestionData from(Question q) {
        QuestionData result = QuestionData
                .builder()
                .title(q.getTitle())
                .image(q.getImage())
                .audio(q.getAudio())
                .type(q.getType())
                .answerExplanation(q.getAnswerExplanation())
                .creator(UserData.from(q.getCreator()))
                .build();

        if (Objects.nonNull(q.getAssignment())) {
            result.setAssignment(AssignmentData.from(q.getAssignment()));
        }

        if (Objects.nonNull(q.getSubject())) {
            result.setSubject(SubjectData.from(q.getSubject()));
        }

        if (Objects.nonNull(q.getTextAnswers())) {
            result.setTextAnswers(q.getTextAnswers().stream().map(a -> QuestionTextAnswerData.from(a)).toList());
        }

        return result;
    }

    public Question fromThis() {
        Question result = Question
                .builder()
                .id(Identity.from(this.getId()))
                .title(this.title)
                .image(this.image)
                .audio(this.audio)
                .type(this.type)
                .answerExplanation(this.answerExplanation)
                .creator(this.creator.fromThis())
                .createdAt(this.getCreatedAt())
                .updatedAt(this.getUpdatedAt())
                .deletedAt(this.getDeletedAt())
                .build();

        if (Objects.nonNull(this.choices)) {
            result.setChoices(this.choices.stream().map(c -> c.fromThis()).toList());
        }

        if (Objects.nonNull(this.subject)) {
            result.setSubject(this.subject.fromThis());
        }

        if (Objects.nonNull(this.textAnswers)) {
            result.setTextAnswers(this.textAnswers.stream().map(a -> a.fromThis()).toList());
        }

        return result;
    }
}
