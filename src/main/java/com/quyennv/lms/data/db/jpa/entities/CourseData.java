package com.quyennv.lms.data.db.jpa.entities;

import com.quyennv.lms.core.domain.entities.Course;
import com.quyennv.lms.core.domain.entities.Identity;
import com.quyennv.lms.core.domain.enums.CourseLevel;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity(name="courses")
@Data
@Table(name="courses")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Slf4j
public class CourseData extends BaseEntity{
    private String name;
    private String description;
    @Column(name="background_img")
    private String backgroundImage;
    private String thumbnail;
    @Column(name="start_date")
    private LocalDateTime startDate;
    @Column(name="end_date")
    private LocalDateTime endDate;
    private Long price;
    @Enumerated(EnumType.STRING)
    private CourseLevel level;
    @Column(name="is_verified")
    private Boolean isVerified;
    private Integer grade;
    private String code;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY ,cascade = CascadeType.ALL)
    private List<SectionData> sections;
    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY ,cascade = CascadeType.ALL)
    private List<CourseInfoData> courseInfos;
    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY ,cascade = CascadeType.ALL)
    private List<AssignmentPlacementData> assignmentPlacements;
    @ManyToOne
    @JoinColumn(name="subject_id")
    private SubjectData subject;
    @ManyToOne
    @JoinColumn(name="teacher_id", nullable = false)
    private UserData teacher;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY ,cascade = CascadeType.ALL)
    private List<CourseStudentData> courseStudents;

    public static CourseData from(Course course) {
        CourseData result = CourseData
                .builder()
                .name(course.getName())
                .description(course.getDescription())
                .backgroundImage(course.getBackgroundImage())
                .thumbnail(course.getThumbnail())
                .startDate(course.getStartDate())
                .endDate(course.getEndDate())
                .price(course.getPrice())
                .level(course.getLevel())
                .isVerified(course.getIsVerified())
                .grade(course.getGrade())
                .code(course.getCode())
                .subject(SubjectData.from(course.getSubject()))
                .teacher(UserData.from(course.getTeacher()))
                .build();

        if (Objects.nonNull(course.getId())) {
            result.setId(course.getId().getId());
        } else {
            result.setId(UUID.randomUUID());
        }

        result.setCreatedAt(course.getCreatedAt());
        result.setUpdatedAt(course.getUpdatedAt());
        result.setDeletedAt(course.getDeletedAt());

        if (Objects.nonNull(course.getSections())) {
            result.setSections(course.getSections().stream().map(s -> {
                SectionData section = SectionData.from(s);
                section.setCourse(result);
                return section;
            }).toList());
        }

        if (Objects.nonNull(course.getCourseInfo())) {
            result.setCourseInfos(course.getCourseInfo().stream().map(i -> {
                CourseInfoData courseInfo = CourseInfoData.from(i);
                courseInfo.setCourse(result);
                return courseInfo;
            }).toList());
        }

        if (Objects.nonNull(course.getAssignmentPlacements())) {
            result.setAssignmentPlacements(course.getAssignmentPlacements().stream().map(ap -> {
                AssignmentPlacementData apData = AssignmentPlacementData.from(ap);
                apData.setCourse(result);
                return apData;
            }).toList());
        }

        if (Objects.nonNull(course.getStudents())) {
            result.setCourseStudents(course.getStudents().stream().map(s -> {
                CourseStudentData student = CourseStudentData.from(s);
                student.setCourse(result);
                return student;
            }).toList());
        }

        return result;
    }

    public Course fromThis() {
        Course course = Course
                .builder()
                .id(Identity.from(this.getId()))
                .name(this.name)
                .description(this.description)
                .backgroundImage(this.backgroundImage)
                .thumbnail(this.thumbnail)
                .startDate(this.startDate)
                .endDate(this.endDate)
                .price(this.price)
                .level(this.level)
                .isVerified(this.isVerified)
                .grade(this.grade)
                .code(this.code)
                .createdAt(this.getCreatedAt())
                .updatedAt(this.getUpdatedAt())
                .deletedAt(this.getDeletedAt())
                .build();

        if (Objects.nonNull(this.courseInfos)) {
            course.setCourseInfo(this.courseInfos.stream().map(CourseInfoData::fromThis).toList());
        }

        if (Objects.nonNull(this.sections)) {
            course.setSections(this.sections.stream().map(SectionData::fromThis).toList());
        }

        if (Objects.nonNull(this.assignmentPlacements)) {
            course.setAssignmentPlacements(this.assignmentPlacements.stream().map(AssignmentPlacementData::fromThis).toList());
        }

        if (Objects.nonNull(this.subject)) {
            course.setSubject(this.subject.fromThis());
        }

        if (Objects.nonNull(this.teacher)) {
            course.setTeacher(this.teacher.fromThis());
        }

        if (Objects.nonNull(this.courseStudents)) {
            course.setStudents(this.courseStudents.stream().map(CourseStudentData::fromThis).toList());
        }

        return course;
    }

}
