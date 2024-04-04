package com.quyennv.lms.presenter.rest.mapper.assignment;

import com.quyennv.lms.core.domain.entities.Identity;
import com.quyennv.lms.core.domain.enums.*;
import com.quyennv.lms.core.usecases.assignment.CreateAssignmentUseCase;
import com.quyennv.lms.presenter.rest.dto.assignment.CreateAssignmentRequest;
import com.quyennv.lms.presenter.rest.dto.assignment.AssignmentQuestionsMutationRequest;
import com.quyennv.lms.presenter.rest.dto.assignment.PlacementInputRequest;
import com.quyennv.lms.presenter.usecases.security.UserPrincipal;
import com.quyennv.lms.presenter.utils.DateHelper;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Builder
public class CreateAssignmentUseCaseInputMapper {
    public static CreateAssignmentUseCase.InputValues map(UserPrincipal requester, CreateAssignmentRequest req) {
        return CreateAssignmentUseCase.InputValues
                .builder()
                .title(req.getTitle())
                .description(req.getDescription())
                .teacherId(Identity.from(requester.getId()))
                .subjectId(req.getSubjectId())
                .questions(mapQuestions(req.getQuestions()))
                .placement(mapPlacement(req.getPlacement()))
                .build();
    }

    private static List<CreateAssignmentUseCase.QuestionInput> mapQuestions(List<AssignmentQuestionsMutationRequest> questions) {
        if (Objects.isNull(questions)) {
            return new ArrayList<>();
        }
        return questions.stream().map(
                q -> CreateAssignmentUseCase.QuestionInput
                        .builder()
                        .level(QuestionLevel.valueOf(q.getLevel()))
                        .title(q.getTitle())
                        .image(q.getImage())
                        .audio(q.getAudio())
                        .mark(q.getMark())
                        .type(QuestionType.valueOf(q.getType()))
                        .answerExplanation(q.getAnswerExplanation())
                        .choices(Objects.isNull(q.getChoices()) ? null : q.getChoices().stream().map(
                                c -> CreateAssignmentUseCase.QuestionChoiceInput
                                        .builder()
                                        .content(c.getContent())
                                        .order(c.getOrder())
                                        .isCorrect(c.getIsCorrect())
                                        .explanation(c.getExplanation())
                                        .build()
                        ).toList())
                        .textAnswers(Objects.isNull(q.getTextAnswers()) ? null : q.getTextAnswers().stream().map(
                                ta -> CreateAssignmentUseCase.QuestionTextAnswerInput
                                        .builder()
                                        .answer(ta.getAnswer())
                                        .explanation(ta.getExplanation())
                                        .build()
                        ).toList())
                        .build()
        ).toList();
    }

    private static CreateAssignmentUseCase.AssignmentPlacementInput mapPlacement(PlacementInputRequest placement) {
        if (Objects.isNull(placement)) {
            return null;
        }
        return CreateAssignmentUseCase.AssignmentPlacementInput
                .builder()
                .id(Identity.fromString(placement.getId()))
                .type(AssignmentPlacementType.valueOf(placement.getType()))
                .assignmentType(AssignmentType.valueOf(placement.getAssignmentType()))
                .attemptType(Objects.isNull(placement.getAttemptType()) ? null : AttemptType.valueOf(placement.getAttemptType()))
                .assignmentType(Objects.isNull(placement.getAttemptType()) ? null : AssignmentType.valueOf(placement.getAssignmentType()))
                .duration(placement.getDuration())
                .startTime(DateHelper.toLocalDateTime(placement.getStartTime()))
                .endTime(DateHelper.toLocalDateTime(placement.getEndTime()))
                .assignmentType(AssignmentType.valueOf(placement.getAssignmentType()))
                .build();
    }
}
