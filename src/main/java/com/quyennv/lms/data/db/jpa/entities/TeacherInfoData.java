package com.quyennv.lms.data.db.jpa.entities;

import com.quyennv.lms.core.domain.entities.Identity;
import com.quyennv.lms.core.domain.entities.TeacherInfo;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name="teacher_info")
@Getter
@Setter
@ToString(exclude = {"user"})
@Table(name="teacher_info")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeacherInfoData extends BaseEntity{
    @Column(name="edu_qualification")
    private String educationQualification;
    private String biography;

    @OneToOne
    @JoinColumn(name="user_id", nullable = false)
    private UserData user;

    public static TeacherInfoData from(TeacherInfo teacherInfo) {
        return TeacherInfoData.builder()
                .educationQualification(teacherInfo.getEduQualification())
                .biography(teacherInfo.getBiography())
                .build();
    }

    public TeacherInfo fromThis() {
        return TeacherInfo.builder()
                .userId(Identity.from(this.user.getId()))
                .eduQualification(this.educationQualification)
                .biography(this.biography)
                .build();
    }
}
