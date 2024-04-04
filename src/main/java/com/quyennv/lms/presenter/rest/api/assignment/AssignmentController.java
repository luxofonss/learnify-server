package com.quyennv.lms.presenter.rest.api.assignment;

import com.quyennv.lms.core.usecases.UseCaseExecutor;
import com.quyennv.lms.core.usecases.assignment.CreateAssignmentUseCase;
import com.quyennv.lms.core.usecases.assignment.UpdateAssignmentDetailUseCase;
import com.quyennv.lms.presenter.rest.dto.ApiResponse;
import com.quyennv.lms.presenter.rest.dto.assignment.CreateAssignmentRequest;
import com.quyennv.lms.presenter.rest.dto.assignment.UpdateAssignmentRequest;
import com.quyennv.lms.presenter.rest.mapper.assignment.CreateAssignmentUseCaseInputMapper;
import com.quyennv.lms.presenter.rest.mapper.assignment.UpdateAssignmentDetailUseCaseRequestMapper;
import com.quyennv.lms.presenter.rest.mapper.assignment.UpdateAssignmentUseCaseRequestMapper;
import com.quyennv.lms.presenter.usecases.security.UserPrincipal;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;

import java.util.concurrent.CompletableFuture;

@Controller
public class AssignmentController implements AssignmentResource{
    private final UseCaseExecutor useCaseExecutor;
    private final CreateAssignmentUseCase createAssignmentUseCase;
    private final UpdateAssignmentDetailUseCase updateAssignmentDetailUseCase;

    public AssignmentController(UseCaseExecutor useCaseExecutor,
                                CreateAssignmentUseCase createAssignmentUseCase,
                                UpdateAssignmentDetailUseCase updateAssignmentDetailUseCase) {
        this.useCaseExecutor = useCaseExecutor;
        this.createAssignmentUseCase = createAssignmentUseCase;
        this.updateAssignmentDetailUseCase = updateAssignmentDetailUseCase;
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
}
