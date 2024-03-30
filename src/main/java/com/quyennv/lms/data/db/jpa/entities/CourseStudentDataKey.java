package com.quyennv.lms.data.db.jpa.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Embeddable
@Data
@EqualsAndHashCode
public class CourseStudentDataKey implements Serializable {
    @Column(name="course_id")
    UUID courseId;

    @Column(name="student_id")
    UUID studentId;
}
