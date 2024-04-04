package com.quyennv.lms.presenter.rest.api.course;

import com.quyennv.lms.core.domain.entities.Identity;
import com.quyennv.lms.core.domain.enums.EnrollStatus;
import com.quyennv.lms.core.usecases.UseCaseExecutor;
import com.quyennv.lms.core.usecases.course.*;
import com.quyennv.lms.presenter.rest.dto.ApiResponse;
import com.quyennv.lms.presenter.rest.dto.course.*;
import com.quyennv.lms.presenter.rest.mapper.course.CreateCourseUseCaseInputMapper;
import com.quyennv.lms.presenter.rest.mapper.course.GetCourseUseCaseInputMapper;
import com.quyennv.lms.presenter.rest.mapper.course.LectureMutationInputMapper;
import com.quyennv.lms.presenter.rest.mapper.course.SectionMutationInputMapper;
import com.quyennv.lms.presenter.rest.mapper.course.CourseMutationDetailInputMapper;
import com.quyennv.lms.presenter.usecases.security.UserPrincipal;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;

import java.util.List;
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
    private final GetCreatedCoursesUseCase getCreatedCoursesUseCase;
    private final GetAllCourseUseCase getAllCourseUseCase;
    private final UpdateCourseDetailUseCase updateCourseDetailUseCase;
    private final DeleteCourseUseCase deleteCourseUseCase;
    private final StudentRegisterForCourseUseCase studentRegisterForCourseUseCase;
    private final TeacherAddStudentsToCourseUseCase teacherAddStudentsToCourseUseCase;
    private final UpdateCourseStudentStatusUseCase updateCourseStudentStatusUseCase;
    private final DeleteCourseStudentUseCase deleteCourseStudentUseCase;
    private final CourseStudentsGetAllUseCase courseStudentsGetAllUseCase;
    private final StudentGetRegisteredCoursesUseCase studentGetRegisteredCoursesUseCase;

    public CourseController(UseCaseExecutor useCaseExecutor,
                            CreateCourseUseCase createCourseUseCase,
                            CreateSectionsUseCase createSectionsUseCase,
                            UpdateSectionsUseCase updateSectionsUseCase,
                            DeleteSectionsUseCase deleteSectionsUseCase,
                            GetOneCourseUseCase getOneCourseUseCase,
                            CreateLecturesUseCase createLecturesUseCase,
                            UpdateLecturesUseCase updateLecturesUseCase,
                            DeleteLecturesUseCase deleteLecturesUseCase,
                            GetCreatedCoursesUseCase getCreatedCoursesUseCase,
                            GetAllCourseUseCase getAllCourseUseCase,
                            UpdateCourseDetailUseCase updateCourseDetailUseCase,
                            DeleteCourseUseCase deleteCourseUseCase,
                            StudentRegisterForCourseUseCase studentRegisterForCourseUseCase,
                            TeacherAddStudentsToCourseUseCase teacherAddStudentsToCourseUseCase,
                            UpdateCourseStudentStatusUseCase updateCourseStudentStatusUseCase,
                            DeleteCourseStudentUseCase deleteCourseStudentUseCase,
                            CourseStudentsGetAllUseCase courseStudentsGetAllUseCase,
                            StudentGetRegisteredCoursesUseCase studentGetRegisteredCoursesUseCase) {
        this.useCaseExecutor = useCaseExecutor;
        this.createCourseUseCase = createCourseUseCase;
        this.createSectionsUseCase = createSectionsUseCase;
        this.updateSectionsUseCase = updateSectionsUseCase;
        this.deleteSectionsUseCase = deleteSectionsUseCase;
        this.getOneCourseUseCase = getOneCourseUseCase;
        this.createLecturesUseCase = createLecturesUseCase;
        this.updateLecturesUseCase = updateLecturesUseCase;
        this.deleteLecturesUseCase = deleteLecturesUseCase;
        this.getCreatedCoursesUseCase = getCreatedCoursesUseCase;
        this.getAllCourseUseCase = getAllCourseUseCase;
        this.updateCourseDetailUseCase = updateCourseDetailUseCase;
        this.deleteCourseUseCase = deleteCourseUseCase;
        this.studentRegisterForCourseUseCase = studentRegisterForCourseUseCase;
        this.teacherAddStudentsToCourseUseCase = teacherAddStudentsToCourseUseCase;
        this.updateCourseStudentStatusUseCase = updateCourseStudentStatusUseCase;
        this.deleteCourseStudentUseCase = deleteCourseStudentUseCase;
        this.courseStudentsGetAllUseCase = courseStudentsGetAllUseCase;
        this.studentGetRegisteredCoursesUseCase = studentGetRegisteredCoursesUseCase;
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
    public CompletableFuture<ApiResponse> getAllCourses(
            GetCoursesRequest request,
            UserPrincipal requester,
            HttpServletRequest httpServletRequest) {
        return useCaseExecutor.execute(
                getAllCourseUseCase,
                GetCourseUseCaseInputMapper.map(request, requester),
                outputValues -> new ApiResponse(true, "ok", outputValues.getCourses())
        );
    }

    @Override
    public CompletableFuture<ApiResponse> updateCourse(
            UpdateCourseRequest req,
            String id,
            UserPrincipal requester,
            HttpServletRequest httpServletRequest) {
        return useCaseExecutor.execute(
                updateCourseDetailUseCase,
                CourseMutationDetailInputMapper.map(requester, req, id),
                outputValues -> new ApiResponse(true, "ok", outputValues.getCourse().getId())

        );
    }

    @Override
    public CompletableFuture<ApiResponse> deleteCourse(
            String id,
            UserPrincipal requester,
            HttpServletRequest httpServletRequest) {
        return useCaseExecutor.execute(
                deleteCourseUseCase,
                CourseMutationDetailInputMapper.map(requester, id),
                outputValues -> new ApiResponse(true, "ok", outputValues.getCourse().getId())
        );
    }

    @Override
    public CompletableFuture<ApiResponse> getAllMyCourses(
            GetCoursesRequest request,
            UserPrincipal requester,
            HttpServletRequest httpServletRequest) {
        return useCaseExecutor.execute(
                getCreatedCoursesUseCase,
                GetCourseUseCaseInputMapper.map(request, requester),
                outputValues -> new ApiResponse(true, "ok", outputValues.getCourses())
        );
    }

    @Override
    public CompletableFuture<ApiResponse> getAllMyRegisteredCourses(
            UserPrincipal requester,
            HttpServletRequest httpServletRequest) {
        return useCaseExecutor.execute(
                studentGetRegisteredCoursesUseCase,
                StudentGetRegisteredCoursesUseCase.InputValues.builder().userId(Identity.from(requester.getId())).build(),
                outputValues -> new ApiResponse(true, "ok", outputValues.getCourses())
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

    @Override
    public CompletableFuture<ApiResponse> studentRegisterForCourse(
            String courseId,
            UserPrincipal requester,
            HttpServletRequest httpServletRequest) {
        return useCaseExecutor.execute(
                studentRegisterForCourseUseCase,
                RegisterForCourseUseCase.InputValues
                        .builder()
                        .courseId(Identity.fromString(courseId))
                        .studentIds(List.of(Identity.from(requester.getId())))
                        .build(),
                outputValues -> new ApiResponse(true, "ok", outputValues.getCourseStudents().get(0))
        );
    }

    @Override
    public CompletableFuture<ApiResponse> getAllStudentsInCourse(
            String courseId,
            UserPrincipal requester,
            HttpServletRequest httpServletRequest) {
        return useCaseExecutor.execute(
                courseStudentsGetAllUseCase,
                CourseStudentsGetAllUseCase.InputValues
                        .builder()
                        .courseId(Identity.fromString(courseId))
                        .requesterId(Identity.from(requester.getId()))
                        .build(),
                outputValues -> new ApiResponse(true, "ok", outputValues.getStudents())
        );
    }

    @Override
    public CompletableFuture<ApiResponse> updateStudentInCourse(
            String courseId,
            String studentId,
            UpdateCourseStudentStatusRequest req,
            UserPrincipal requester,
            HttpServletRequest httpServletRequest) {
        return useCaseExecutor.execute(
                updateCourseStudentStatusUseCase,
                UpdateCourseStudentUseCase.InputValues
                        .builder()
                        .courseId(Identity.fromString(courseId))
                        .userId(Identity.fromString(studentId))
                        .status(EnrollStatus.valueOf(req.getStatus()))
                        .build(),
                outputValues -> new ApiResponse(true, "ok", outputValues.getCourseStudent())

        );
    }

    @Override
    public CompletableFuture<ApiResponse> deleteStudentInCourse(
            String courseId,
            String studentId,
            UserPrincipal requester,
            HttpServletRequest httpServletRequest) {
        return useCaseExecutor.execute(
                deleteCourseStudentUseCase,
                UpdateCourseStudentUseCase.InputValues
                        .builder()
                        .courseId(Identity.fromString(courseId))
                        .userId(Identity.fromString(studentId))
                        .build(),
                outputValues -> new ApiResponse(true, "ok", outputValues.getCourseStudent())
        );
    }

    @Override
    public CompletableFuture<ApiResponse> teacherAddStudentsToCourse(
            AddStudentToCourseRequest req,
            String courseId,
            UserPrincipal requester,
            HttpServletRequest httpServletRequest) {
        return useCaseExecutor.execute(
                teacherAddStudentsToCourseUseCase,
                RegisterForCourseUseCase.InputValues
                        .builder()
                        .courseId(Identity.fromString(courseId))
                        .studentIds(req.getStudentIds().stream().map(Identity::fromString).toList())
                        .build(),
                outputValues -> new ApiResponse(true, "ok", outputValues.getCourseStudents())
        );
    }
}
