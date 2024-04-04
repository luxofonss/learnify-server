package com.quyennv.lms.presenter.rest.mapper.assignment;

import com.quyennv.lms.core.domain.entities.Identity;
import com.quyennv.lms.core.domain.enums.QuestionLevel;
import com.quyennv.lms.core.domain.enums.QuestionType;
import com.quyennv.lms.core.usecases.assignment.CreateAssignmentUseCase;
import com.quyennv.lms.core.usecases.assignment.UpdateAssignmentUseCase;
import com.quyennv.lms.presenter.rest.dto.assignment.AssignmentQuestionsMutationRequest;
import com.quyennv.lms.presenter.rest.dto.assignment.QuestionsInputChoicesRequest;
import com.quyennv.lms.presenter.rest.dto.assignment.QuestionsInputTextAnswersRequest;
import com.quyennv.lms.presenter.rest.dto.assignment.UpdateAssignmentRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class UpdateAssignmentUseCaseRequestMapper {
    static List<UpdateAssignmentUseCase.QuestionInput> mapQuestions(List<AssignmentQuestionsMutationRequest> questions) {
        if (Objects.isNull(questions)) {
            return new ArrayList<>();
        }
        return questions.stream().map(
                q -> {
                    UpdateAssignmentUseCase.QuestionInput input = UpdateAssignmentUseCase.QuestionInput
                            .builder()
                            .id(Identity.fromString(q.getId()))
                            .level(QuestionLevel.valueOf(q.getLevel()))
                            .title(q.getTitle())
                            .image(q.getImage())
                            .audio(q.getAudio())
                            .mark(q.getMark())
                            .type(QuestionType.valueOf(q.getType()))
                            .answerExplanation(q.getAnswerExplanation())
                            .build();

                    if (Objects.nonNull(q.getChoices())) {
                        List<UpdateAssignmentUseCase.QuestionChoiceInput> choices = mapChoices(q.getChoices());
                        input.setChoices(choices);
                    }

                    if (Objects.nonNull(q.getTextAnswers())) {
                        List<UpdateAssignmentUseCase.QuestionTextAnswerInput> textAnswers = mapTextAnswers(q.getTextAnswers());
                        input.setTextAnswers(textAnswers);
                    }

                    return input;
                }
        ).toList();
    }

    static List<UpdateAssignmentUseCase.QuestionChoiceInput> mapChoices(List<QuestionsInputChoicesRequest> choices) {
        return choices.stream().map(
                q -> UpdateAssignmentUseCase.QuestionChoiceInput
                        .builder()
                        .id(Identity.fromString(q.getId()))
                        .content(q.getContent())
                        .order(q.getOrder())
                        .isCorrect(q.getIsCorrect())
                        .explanation(q.getExplanation())
                        .build()
        ).toList();
    }

    static List<UpdateAssignmentUseCase.QuestionTextAnswerInput> mapTextAnswers(
            List<QuestionsInputTextAnswersRequest> textAnswers) {
        return textAnswers.stream().map(
                ta -> UpdateAssignmentUseCase.QuestionTextAnswerInput
                        .builder()
                        .id(Identity.fromString(ta.getId()))
                        .answer(ta.getAnswer())
                        .explanation(ta.getExplanation())
                        .build()
        ).toList();
    }

}
