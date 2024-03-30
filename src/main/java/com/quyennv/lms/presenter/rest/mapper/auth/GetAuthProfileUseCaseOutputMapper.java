package com.quyennv.lms.presenter.rest.mapper.auth;

import com.quyennv.lms.core.domain.entities.User;
import com.quyennv.lms.presenter.rest.dto.ApiResponse;
import com.quyennv.lms.presenter.rest.dto.user.UserProfileResponse;
import org.springframework.http.ResponseEntity;

public class GetAuthProfileUseCaseOutputMapper {
    public static ResponseEntity<ApiResponse> map(
            User user
    ) {
        return ResponseEntity.ok(new ApiResponse(true, "Get user successfully!",
                new UserProfileResponse(
                    user.getId().getId().toString(),
                    user.getEmail(),
                    user.getUsername(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getAvatar(),
                    user.getPhoneNumber(),
                    user.getGender(),
                    user.getRole(),
                    user.getDateOfBirth().toString(),
                    user.getIsVerified(),
                    user.getAddress(),
                    user.getLearnerInfo(),
                    user.getTeacherInfo(),
                    user.getCreatedAt()
                )
        ));
    }
}
