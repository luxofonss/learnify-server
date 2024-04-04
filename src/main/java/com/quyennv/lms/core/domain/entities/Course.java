package com.quyennv.lms.core.domain.entities;

import com.quyennv.lms.core.domain.enums.CourseInfoType;
import com.quyennv.lms.core.domain.enums.CourseLevel;
import com.quyennv.lms.core.utils.FunctionHelper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Slf4j
public class Course {
    private Identity id;
    private String name;
    private String description;
    private String backgroundImage;
    private String thumbnail;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long price;
    private CourseLevel level;
    private Boolean isVerified;
    private Integer grade;
    private String code;

    private List<Section> sections;
    private List<CourseInfo> courseInfos;
    private List<AssignmentPlacement> assignmentPlacements;
    private Subject subject;
    private User teacher;
    private List<CourseStudent> students;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    public Course updateCourse(Course course) {
        BeanUtils.copyProperties(course, this, FunctionHelper.getNullPropertyNames(course));

        if (Objects.nonNull(course.getSections())) {
            this.updateSections(course.getSections());
        }

        if (Objects.nonNull(course.getCourseInfos())) {
            this.setCourseInfos(this.courseInfos.stream().map(info -> {
                for (CourseInfo i : course.getCourseInfos()) {
                    if (i.getId().equals(info.getId())) {
                        BeanUtils.copyProperties(i, info, FunctionHelper.getNullPropertyNames(i));
                        break;
                    }
                }

                return info;
            }).toList());
            this.setCourseInfos(course.getCourseInfos());
        }

        return this;
    }

    public Course delete() {
        this.deletedAt = LocalDateTime.now();
        return this;
    }

    public Course addSections(List<Section> sections) {
        Set<Section> allSections = new HashSet<>(this.sections);
        allSections.addAll(sections);
        this.setSections(allSections.stream().toList());

        return this;
    }

    public Course updateSections(List<Section> sections) {

        this.setSections(this.sections.stream().map(section -> {
            for (Section s : sections ){
                if (section.getId().equals(s.getId())) {
                    BeanUtils.copyProperties(s, section, FunctionHelper.getNullPropertyNames(s));
                    break;
                }
            }
            return section;
        }).toList());

        return this;
    }

    public Course deleteSections(List<Section> sections) {
        this.setSections(this.sections.stream().map(section -> {
            for (Section s : sections) {
                if (section.getId().equals(s.getId())) {
                    section.delete();
                    break;
                }
            }
            return section;
        }).toList());

        return this;
    }

    public Course addLectures(List<Lecture> lectures, Identity sectionId) {
        this.sections.forEach(section -> {
            if (section.getId().equals(sectionId)) {
                Set<Lecture> newLectures = new HashSet<>(section.getLectures());
                newLectures.addAll(lectures);
                section.setLectures(newLectures.stream().toList());
            }
        });

        return this;
    }

    public Course updateLectures(List<Lecture> lectures, Identity sectionId) {
        this.sections.forEach(section -> {
            if (section.getId().equals(sectionId)) {
                for(Lecture lecture : section.getLectures()) {
                    for(Lecture updateLecture : lectures) {
                        if (lecture.getId().equals(updateLecture.getId())) {
                            BeanUtils.copyProperties(updateLecture, lecture, FunctionHelper.getNullPropertyNames(updateLecture));
                        }
                    }
                }
            }
        });

        return this;
    }

    public Course deleteLectures(List<Lecture> lectures, Identity sectionId) {
        this.sections.forEach(section -> {
            if (section.getId().equals(sectionId)) {
                for(Lecture lecture : section.getLectures()) {
                    for(Lecture updateLecture : lectures) {
                        if (lecture.getId().equals(updateLecture.getId())) {
                           lecture.delete();
                        }
                    }
                }
            }
        });

        return this;
    }
}
