package com.quyennv.lms.presenter.rest.dto.assignment;

import com.quyennv.lms.core.domain.enums.AssignmentPlacementType;
import com.quyennv.lms.core.domain.enums.AssignmentType;
import com.quyennv.lms.core.domain.enums.AttemptType;
import com.quyennv.lms.presenter.config.annotations.ValueOfEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

@Value
public class PlacementInputRequest {
    @NotNull
    String id;
    @ValueOfEnum(enumClass = AssignmentPlacementType.class)
    String type;
    @ValueOfEnum(enumClass = AttemptType.class)
    String attemptType;
    Long duration;
    String startTime;
    String endTime;
    @ValueOfEnum(enumClass = AssignmentType.class)
    String assignmentType;
}
