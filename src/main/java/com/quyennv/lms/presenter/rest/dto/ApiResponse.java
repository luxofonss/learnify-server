package com.quyennv.lms.presenter.rest.dto;

import jakarta.annotation.Nullable;
import lombok.Value;

@Value
public class ApiResponse {
    Boolean success;
    String message;
    @Nullable
    Object data;
}
