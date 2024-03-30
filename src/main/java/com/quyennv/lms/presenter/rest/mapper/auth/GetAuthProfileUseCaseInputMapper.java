package com.quyennv.lms.presenter.rest.mapper.auth;

import com.quyennv.lms.core.usecases.user.GetAuthProfileUseCase;
import com.quyennv.lms.presenter.usecases.security.UserPrincipal;

public class GetAuthProfileUseCaseInputMapper {
    public static GetAuthProfileUseCase.InputValues map(UserPrincipal requester) {
        return new GetAuthProfileUseCase.InputValues(requester.getId().toString());
    }
}
