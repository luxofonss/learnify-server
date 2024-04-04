package com.quyennv.lms.core.domain.entities;

import com.quyennv.lms.core.domain.enums.AssignmentType;
import com.quyennv.lms.core.utils.FunctionHelper;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Slf4j
public class Assignment {
    private Identity id;
    private String title;
    private String description;
    private Integer totalMark;
    private User teacher;
    private Subject subject;
    private List<Question> questions;
    private List<AssignmentPlacement> placements;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    public Assignment update(Assignment updateAssignment) {
        BeanUtils.copyProperties(updateAssignment, this, FunctionHelper.getNullPropertyNames(updateAssignment));

        if (Objects.nonNull(updateAssignment.getQuestions())) {
            this.questions.forEach(q -> {
                updateAssignment.getQuestions().forEach(updatedQuestion -> {
                    if (q.getId().equals(updatedQuestion.getId())) {
                        q.update(updatedQuestion);
                    }
                });
            });
            this.setQuestions(updateAssignment.getQuestions());
        }

        return this;
    }
}
