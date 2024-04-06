package com.quyennv.lms.presenter.rest.api.assignment;

import com.quyennv.lms.core.domain.entities.Identity;
import com.quyennv.lms.core.usecases.UseCaseExecutor;
import com.quyennv.lms.core.usecases.assignment.AddQuestionsToAssignmentUseCase;
import com.quyennv.lms.core.usecases.assignment.CreateAssignmentUseCase;
import com.quyennv.lms.core.usecases.assignment.GetOneAssignmentUseCase;
import com.quyennv.lms.core.usecases.assignment.UpdateAssignmentDetailUseCase;
import com.quyennv.lms.presenter.rest.dto.ApiResponse;
import com.quyennv.lms.presenter.rest.dto.assignment.CreateAssignmentRequest;
import com.quyennv.lms.presenter.rest.dto.assignment.UpdateAssignmentRequest;
import com.quyennv.lms.presenter.rest.mapper.assignment.AddQuestionToAssignmentInputMapper;
import com.quyennv.lms.presenter.rest.mapper.assignment.CreateAssignmentUseCaseInputMapper;
import com.quyennv.lms.presenter.rest.mapper.assignment.UpdateAssignmentDetailUseCaseRequestMapper;
import com.quyennv.lms.presenter.usecases.security.UserPrincipal;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;

import java.util.concurrent.CompletableFuture;

@Controller
public class AssignmentController implements AssignmentResource{
    private final UseCaseExecutor useCaseExecutor;
    private final CreateAssignmentUseCase createAssignmentUseCase;
    private final UpdateAssignmentDetailUseCase updateAssignmentDetailUseCase;
    private final AddQuestionsToAssignmentUseCase addQuestionsToAssignmentUseCase;
    private final GetOneAssignmentUseCase getOneAssignmentUseCase;

    public AssignmentController(UseCaseExecutor useCaseExecutor,
                                CreateAssignmentUseCase createAssignmentUseCase,
                                UpdateAssignmentDetailUseCase updateAssignmentDetailUseCase,
                                AddQuestionsToAssignmentUseCase addQuestionsToAssignmentUseCase,
                                GetOneAssignmentUseCase getOneAssignmentUseCase) {
        this.useCaseExecutor = useCaseExecutor;
        this.createAssignmentUseCase = createAssignmentUseCase;
        this.updateAssignmentDetailUseCase = updateAssignmentDetailUseCase;
        this.addQuestionsToAssignmentUseCase = addQuestionsToAssignmentUseCase;
        this.getOneAssignmentUseCase = getOneAssignmentUseCase;
    }

    @Override
    public CompletableFuture<ApiResponse> create(
            CreateAssignmentRequest req,
            UserPrincipal requester,
            HttpServletRequest httpServletRequest) {
        return useCaseExecutor.execute(
                createAssignmentUseCase,
                CreateAssignmentUseCaseInputMapper.map(requester, req),
                outputValues -> new ApiResponse(true, "ok", outputValues.getAssignment())
        );
    }

    @Override
    public CompletableFuture<ApiResponse> getOne(
            String id,
            UserPrincipal requester,
            HttpServletRequest httpServletRequest) {
        return useCaseExecutor.execute(
                getOneAssignmentUseCase,
                GetOneAssignmentUseCase.InputValues
                        .builder()
                        .assignmentId(Identity.fromString(id))
                        .requesterId(Identity.from(requester.getId()))
                        .build(),
                outputValues -> new ApiResponse(true, "ok", outputValues.getAssignment())
        );
    }

    @Override
    public CompletableFuture<ApiResponse> update(
            UpdateAssignmentRequest req,
            String id,
            UserPrincipal requester,
            HttpServletRequest httpServletRequest) {
        return useCaseExecutor.execute(
                updateAssignmentDetailUseCase,
                UpdateAssignmentDetailUseCaseRequestMapper.map(requester, req, id),
                outputValues -> new ApiResponse(true, "ok", outputValues.getAssignment())
        );
    }

    @Override
    public CompletableFuture<ApiResponse> addQuestions(
            UpdateAssignmentRequest req,
            String id,
            UserPrincipal requester,
            HttpServletRequest httpServletRequest) {
        return useCaseExecutor.execute(
                addQuestionsToAssignmentUseCase,
                AddQuestionToAssignmentInputMapper.map(requester, req, id),
                outputValues -> new ApiResponse(true, "ok", outputValues.getAssignment())
        );
    }
}
