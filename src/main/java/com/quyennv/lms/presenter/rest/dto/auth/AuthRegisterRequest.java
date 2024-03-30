package com.quyennv.lms.presenter.rest.dto.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.quyennv.lms.core.domain.entities.LearnerInfo;
import com.quyennv.lms.core.domain.entities.TeacherInfo;
import com.quyennv.lms.core.domain.enums.Gender;
import com.quyennv.lms.core.domain.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Value;


@Value
public class AuthRegisterRequest {
    @NotBlank(message = "Username is required")
    String username;
    @NotBlank(message = "Email is required")
    @Email(message = "Email is invalid")
    String email;
    @NotBlank(message = "Password is required")
    String password;
    @NotBlank(message = "First name is required")
    @JsonProperty("first_name")
    String firstName;
    @NotBlank(message = "Last name is required")
    @JsonProperty("last_name")
    String lastName;
    @NotBlank(message = "Phone number is required")
    @JsonProperty("phone_number")
    String phoneNumber;
    @NotNull(message = "gender is required")
    Gender gender;
    @NotBlank(message = "Date of birth is required")
    @JsonProperty("dob")
    String dateOfBirth;
    String avatar;
    @JsonProperty("auth_type")
    String authType;
    Role role;
    @JsonProperty("learner_info")
    LearnerInfo learnerInfo;
    @JsonProperty("teacher_info")
    TeacherInfo teacherInfo;

}
