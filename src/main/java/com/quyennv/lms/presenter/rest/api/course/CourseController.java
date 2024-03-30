package com.quyennv.lms.presenter.rest.api.course;

import com.quyennv.lms.core.domain.entities.Identity;
import com.quyennv.lms.core.usecases.UseCaseExecutor;
import com.quyennv.lms.core.usecases.course.*;
import com.quyennv.lms.presenter.rest.dto.ApiResponse;
import com.quyennv.lms.presenter.rest.dto.course.CreateCourseRequest;
import com.quyennv.lms.presenter.rest.dto.course.UpdateCourseRequest;
import com.quyennv.lms.presenter.rest.mapper.course.CreateCourseUseCaseInputMapper;
import com.quyennv.lms.presenter.rest.mapper.course.LectureMutationInputMapper;
import com.quyennv.lms.presenter.rest.mapper.course.SectionMutationInputMapper;
import com.quyennv.lms.presenter.usecases.security.UserPrincipal;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;

import java.util.concurrent.CompletableFuture;

@Controller
public class CourseController implements CourseResource{
    private final UseCaseExecutor useCaseExecutor;
    private final CreateCourseUseCase createCourseUseCase;
    private final CreateSectionsUseCase createSectionsUseCase;
    private final UpdateSectionsUseCase updateSectionsUseCase;
    private final DeleteSectionsUseCase deleteSectionsUseCase;
    private final GetOneCourseUseCase getOneCourseUseCase;
    private final CreateLecturesUseCase createLecturesUseCase;
    private final UpdateLecturesUseCase updateLecturesUseCase;
    private final DeleteLecturesUseCase deleteLecturesUseCase;

    public CourseController(UseCaseExecutor useCaseExecutor,
                            CreateCourseUseCase createCourseUseCase,
                            CreateSectionsUseCase createSectionsUseCase,
                            UpdateSectionsUseCase updateSectionsUseCase,
                            DeleteSectionsUseCase deleteSectionsUseCase,
                            GetOneCourseUseCase getOneCourseUseCase,
                            CreateLecturesUseCase createLecturesUseCase,
                            UpdateLecturesUseCase updateLecturesUseCase,
                            DeleteLecturesUseCase deleteLecturesUseCase) {
        this.useCaseExecutor = useCaseExecutor;
        this.createCourseUseCase = createCourseUseCase;
        this.createSectionsUseCase = createSectionsUseCase;
        this.updateSectionsUseCase = updateSectionsUseCase;
        this.deleteSectionsUseCase = deleteSectionsUseCase;
        this.getOneCourseUseCase = getOneCourseUseCase;
        this.createLecturesUseCase = createLecturesUseCase;
        this.updateLecturesUseCase = updateLecturesUseCase;
        this.deleteLecturesUseCase = deleteLecturesUseCase;
    }

    @Override
    public CompletableFuture<ApiResponse> create(
            CreateCourseRequest req,
            UserPrincipal requester,
            HttpServletRequest httpServletRequest) {
        return useCaseExecutor.execute(
                createCourseUseCase,
                CreateCourseUseCaseInputMapper.map(requester, req),
                outputValues -> new ApiResponse(true, "ok", outputValues.getCourse().getId())
        );
    }

    @Override
    public CompletableFuture<ApiResponse> getById(String id, UserPrincipal requester, HttpServletRequest httpServletRequest) {
        return useCaseExecutor.execute(
                getOneCourseUseCase,
                new GetOneCourseUseCase.InputValues(Identity.fromString(id)),
                outputValues -> new ApiResponse(true,"ok", outputValues.getCourse())
        );
    }

    @Override
    public CompletableFuture<ApiResponse> addSections(
            UpdateCourseRequest req,
            String id,
            UserPrincipal requester,
            HttpServletRequest httpServletRequest) {
        return useCaseExecutor.execute(
                createSectionsUseCase,
                SectionMutationInputMapper.map(requester, req, id),
                outputValues -> new ApiResponse(true, "ok", outputValues.getCourse().getId())
        );
    }

    @Override
    public CompletableFuture<ApiResponse> updateSections(
            UpdateCourseRequest req,
            String id,
            UserPrincipal requester,
            HttpServletRequest httpServletRequest) {
        return useCaseExecutor.execute(
                updateSectionsUseCase,
                SectionMutationInputMapper.map(requester, req, id),
                outputValues -> new ApiResponse(true, "ok", outputValues.getCourse().getId())
        );
    }

    @Override
    public CompletableFuture<ApiResponse> deleteSections(
            UpdateCourseRequest req,
            String id,
            UserPrincipal requester,
            HttpServletRequest httpServletRequest) {
        return useCaseExecutor.execute(
                deleteSectionsUseCase,
                SectionMutationInputMapper.map(requester, req, id),
                outputValues -> new ApiResponse(true, "ok", outputValues.getCourse().getId())
        );
    }

    @Override
    public CompletableFuture<ApiResponse> addLectures(
            UpdateCourseRequest req,
            String courseId,
            String sectionId,
            UserPrincipal requester,
            HttpServletRequest httpServletRequest) {
        return useCaseExecutor.execute(
                createLecturesUseCase,
                LectureMutationInputMapper.map(requester, req, courseId, sectionId),
                outputValues -> new ApiResponse(true, "ok", outputValues.getCourse().getId())
        );
    }

    @Override
    public CompletableFuture<ApiResponse> updateLectures(
            UpdateCourseRequest req,
            String courseId,
            String sectionId,
            UserPrincipal requester,
            HttpServletRequest httpServletRequest) {
        return useCaseExecutor.execute(
                updateLecturesUseCase,
                LectureMutationInputMapper.map(requester, req, courseId, sectionId),
                outputValues -> new ApiResponse(true, "ok", outputValues.getCourse().getId())
        );
    }

    @Override
    public CompletableFuture<ApiResponse> deleteLectures(
            UpdateCourseRequest req,
            String courseId,
            String sectionId,
            UserPrincipal requester,
            HttpServletRequest httpServletRequest) {
        return useCaseExecutor.execute(
                deleteLecturesUseCase,
                LectureMutationInputMapper.map(requester, req, courseId, sectionId),
                outputValues -> new ApiResponse(true, "ok", outputValues.getCourse().getId())
        );
    }
}
