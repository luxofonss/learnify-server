package com.quyennv.lms.presenter.rest.dto.course;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddStudentToCourseRequest {
    List<String> studentIds;
}
