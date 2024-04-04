package com.quyennv.lms.presenter.rest.dto.assignment;

import com.quyennv.lms.core.domain.entities.Identity;
import jakarta.validation.constraints.NotBlank;
import lombok.Value;

import java.util.List;

@Value
public class CreateAssignmentRequest {
    @NotBlank
    String title;
    String description;
    Identity subjectId;
    List<AssignmentQuestionsMutationRequest> questions;
    PlacementInputRequest placement;
}
