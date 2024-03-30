package com.quyennv.lms.core.usecases.user;

import com.quyennv.lms.core.domain.entities.Address;
import com.quyennv.lms.core.domain.entities.LearnerInfo;
import com.quyennv.lms.core.domain.entities.TeacherInfo;
import com.quyennv.lms.core.domain.entities.User;
import com.quyennv.lms.core.domain.enums.Gender;
import com.quyennv.lms.core.domain.enums.Role;
import com.quyennv.lms.core.usecases.UseCase;
import lombok.Value;

import java.time.LocalDateTime;

public class GetAuthProfileUseCase extends UseCase<GetAuthProfileUseCase.InputValues, GetAuthProfileUseCase.OutputValues> {
    private final UserRepository userRepository;

    public GetAuthProfileUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public OutputValues execute(InputValues input) {
        User user = userRepository.getUserById(input.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        return new OutputValues(user);
    }

    @Value
    public static class InputValues implements UseCase.InputValues {
        String userId;
    }

    @Value
    public static class OutputValues implements UseCase.OutputValues {
        User user;
    }
}
