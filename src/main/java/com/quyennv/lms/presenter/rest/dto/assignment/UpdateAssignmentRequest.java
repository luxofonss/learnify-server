package com.quyennv.lms.presenter.rest.dto.assignment;

import com.quyennv.lms.core.domain.entities.Identity;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UpdateAssignmentRequest {
    @NotBlank
    String id;
    @NotBlank 
    String title;
    String description;
    Identity subjectId;
    List<AssignmentQuestionsMutationRequest> questions;
}
