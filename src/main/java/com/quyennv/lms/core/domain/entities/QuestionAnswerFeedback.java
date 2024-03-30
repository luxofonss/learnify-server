package com.quyennv.lms.core.domain.entities;

import com.quyennv.lms.core.domain.enums.QuestionAnswerFeedbackType;
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
public class QuestionAnswerFeedback {
    private Identity id;

    private User creator;
    private String message;
    private QuestionAnswerFeedbackType type;
    private QuestionAnswer answer;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
