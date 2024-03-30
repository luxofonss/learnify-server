package com.quyennv.lms.presenter.rest.dto.course;

import com.quyennv.lms.core.domain.enums.CourseLevel;
import com.quyennv.lms.presenter.config.annotations.ValueOfEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import lombok.Value;

import java.util.List;

@Value
public class UpdateCourseRequest {
    String courseId;
    String name;
    String description;
    String backgroundImage;
    String thumbnail;
    String startDate;
    String endDate;
    Long price;
    @Null
    @ValueOfEnum(enumClass = CourseLevel.class)
    String level;
    Integer grade;
    String subjectId;

    List<UpdateCourseRequestCourseInfo> courseInfos;
    List<UpdateCourseRequestSection> sections;
    List<UpdateCourseRequestCourseStudent> students;
    List<UpdateCourseRequestLecture> lectures;
}
