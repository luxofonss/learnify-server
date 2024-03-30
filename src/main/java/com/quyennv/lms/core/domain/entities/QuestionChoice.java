package com.quyennv.lms.core.domain.entities;

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
public class QuestionChoice {
    private Identity id;
    private Integer order;
    private Boolean isCorrect;
    private String content;
    private String explanation;
    private Question question;

    private List<QuestionAnswer> chosenAnswers;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
