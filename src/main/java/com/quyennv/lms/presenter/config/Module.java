package com.quyennv.lms.presenter.config;

import com.quyennv.lms.core.usecases.course.*;
import com.quyennv.lms.core.usecases.user.CreateUserUseCase;
import com.quyennv.lms.core.usecases.user.GetAuthProfileUseCase;
import com.quyennv.lms.core.usecases.user.UserRepository;
import com.quyennv.lms.presenter.usecases.security.AppUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Module {
    @Bean
    AppUserDetailsService userDetailsHrmsService(UserRepository userRepository) {
        return new AppUserDetailsService(userRepository);
    }

    @Bean
    CreateUserUseCase createUserUseCase(UserRepository userRepository) {
        return new CreateUserUseCase(userRepository);
    }

    @Bean
    GetAuthProfileUseCase getAuthProfileUseCase(UserRepository userRepository) {
        return new GetAuthProfileUseCase(userRepository);
    }

    @Bean
    CreateCourseUseCase createCourseUseCase(CourseRepository repository) {
        return new CreateCourseUseCase(repository);
    }

    @Bean
    CreateSectionsUseCase createSectionUseCase(CourseRepository repository) {
        return new CreateSectionsUseCase(repository);
    }

    @Bean
    UpdateSectionsUseCase updateSectionsUseCase(CourseRepository repository) {
        return new UpdateSectionsUseCase(repository);
    }

    @Bean
    DeleteSectionsUseCase deleteSectionsUseCase(CourseRepository repository){
        return new DeleteSectionsUseCase(repository);
    }

    @Bean
    CreateLecturesUseCase createLectureUseCase(CourseRepository repository) {
        return new CreateLecturesUseCase(repository);
    }

    @Bean
    UpdateLecturesUseCase updateLecturesUseCase(CourseRepository repository) {
        return new UpdateLecturesUseCase(repository);
    }

    @Bean
    DeleteLecturesUseCase deleteLecturesUseCase(CourseRepository repository){
        return new DeleteLecturesUseCase(repository);
    }

    @Bean
    GetOneCourseUseCase getOneCourseUseCase(CourseRepository repository){
        return new GetOneCourseUseCase(repository);
    }
}
