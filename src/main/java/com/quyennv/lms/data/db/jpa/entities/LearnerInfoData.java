package com.quyennv.lms.data.db.jpa.entities;

import com.quyennv.lms.core.domain.entities.Identity;
import com.quyennv.lms.core.domain.entities.LearnerInfo;
import com.quyennv.lms.core.domain.enums.LearnerType;
import jakarta.persistence.*;
import lombok.*;

@Entity(name="learner_info")
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name="learner_info")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LearnerInfoData extends BaseEntity{
    @Enumerated(EnumType.STRING)
    private LearnerType type;
    private Integer grade;
    private String school;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserData user;

    public static LearnerInfoData from(LearnerInfo learner) {
        LearnerInfoData res = LearnerInfoData.builder()
                .type(learner.getType())
                .grade(learner.getGrade())
                .school(learner.getSchool())
                .build();

        res.setId(learner.getId().getId());
        res.setCreatedAt(learner.getCreatedAt());
        res.setUpdatedAt(learner.getUpdatedAt());
        res.setDeletedAt(learner.getDeletedAt());

        return res;
    }

    public LearnerInfo fromThis() {
        return LearnerInfo.builder()
                .id(Identity.from(this.getId()))
                .userId(Identity.from(this.user.getId()))
                .type(this.type)
                .grade(this.grade)
                .school(this.school)
                .createdAt(this.getCreatedAt())
                .updatedAt(this.getUpdatedAt())
                .deletedAt(this.getDeletedAt())
                .build();
    }
}
