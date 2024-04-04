package com.quyennv.lms.data.db.jpa.entities;

import com.quyennv.lms.core.domain.entities.Identity;
import com.quyennv.lms.core.domain.entities.Subject;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Objects;

@EqualsAndHashCode(callSuper = true)
@Entity(name="subjects")
@Data
@Table(name="subjects")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubjectData extends BaseEntity {
    private String name;
    private String description;
    private String thumbnail;

    public static SubjectData from(Subject s) {
        SubjectData result = SubjectData
                .builder()
                .name(s.getName())
                .description(s.getDescription())
                .thumbnail(s.getThumbnail())
                .build();

        if (Objects.nonNull(s.getId())) {
            result.setId(s.getId().getId());
        }
        result.setCreatedAt(s.getCreatedAt());
        result.setUpdatedAt(s.getUpdatedAt());
        result.setDeletedAt(s.getDeletedAt());

        return result;
    }

    public Subject fromThis() {
        return Subject
                .builder()
                .id(Identity.from(this.getId()))
                .name(this.name)
                .description(this.description)
                .thumbnail(this.thumbnail)
                .createdAt(this.getCreatedAt())
                .updatedAt(this.getUpdatedAt())
                .deletedAt(this.getDeletedAt())
                .build();
    }
}
