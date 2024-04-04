package com.quyennv.lms.presenter.rest.mapper.assignment;

import com.quyennv.lms.core.domain.entities.Identity;
import com.quyennv.lms.core.usecases.assignment.UpdateAssignmentDetailUseCase;
import com.quyennv.lms.presenter.rest.dto.assignment.UpdateAssignmentRequest;
import com.quyennv.lms.presenter.usecases.security.UserPrincipal;

public class UpdateAssignmentDetailUseCaseRequestMapper extends UpdateAssignmentUseCaseRequestMapper{
    public static UpdateAssignmentDetailUseCase.InputValues map(UserPrincipal requester, UpdateAssignmentRequest req, String assignmentId) {
        return UpdateAssignmentDetailUseCase.InputValues
                .builder()
                .id(Identity.fromString(assignmentId))
                .title(req.getTitle())
                .description(req.getDescription())
                .teacherId(Identity.from(requester.getId()))
                .subjectId(req.getSubjectId())
                .questions(mapQuestions(req.getQuestions()))
                .build();
    }
}
