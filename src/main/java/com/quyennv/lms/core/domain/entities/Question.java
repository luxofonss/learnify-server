package com.quyennv.lms.core.domain.entities;

import com.quyennv.lms.core.domain.enums.QuestionChoiceType;
import com.quyennv.lms.core.domain.enums.QuestionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Question {
    private Identity id;

    private String title;
    private String image;
    private String audio;
    private QuestionType type;
    private String answerExplanation;
    private List<QuestionChoice> choices;
    private List<QuestionTextAnswer> textAnswers;
    private Assignment assignment;
    private User creator;
    private Subject subject;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}