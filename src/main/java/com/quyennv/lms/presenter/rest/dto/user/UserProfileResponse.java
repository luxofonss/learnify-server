package com.quyennv.lms.presenter.rest.dto.user;

import com.quyennv.lms.core.domain.entities.Address;
import com.quyennv.lms.core.domain.entities.LearnerInfo;
import com.quyennv.lms.core.domain.entities.TeacherInfo;
import com.quyennv.lms.core.domain.enums.Gender;
import com.quyennv.lms.core.domain.enums.Role;
import lombok.Value;

import java.time.LocalDateTime;

@Value
public class UserProfileResponse {
    String id;
    String email;
    String username;
    String firstName;
    String lastName;
    String avatar;
    String phoneNumber;
    Gender gender;
    Role role;
    String dateOfBirth;
    Boolean isVerified;
    Address address;
    LearnerInfo learnerInfo;
    TeacherInfo teacherInfo;
    LocalDateTime createdAt;
}
