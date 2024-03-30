package com.quyennv.lms.core.domain.entities;

import com.quyennv.lms.core.domain.enums.QuestionChoiceType;
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
public class QuestionAnswer {
    private Identity id;
    private String textAnswer;
    private Integer score;
    private User creator;
    private Question question;
    private List<QuestionChoice> selectedOptions;
    private List<QuestionAnswerFeedback> feedbacks;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
