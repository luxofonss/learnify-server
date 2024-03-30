package com.quyennv.lms.data.db.jpa.entities;

import com.quyennv.lms.core.domain.entities.CourseStudent;
import com.quyennv.lms.core.domain.enums.EnrollStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@EqualsAndHashCode(of = "id")
@Entity(name="course_students")
@Data
@Table(name="course_students")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseStudentData {
    @EmbeddedId
    CourseStudentDataKey id;

    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name="course_id")
    CourseData course;
    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name="student_id")
    UserData student;
    @Enumerated(EnumType.STRING)
    private EnrollStatus status;
    private Integer price;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    public static CourseStudentData from(CourseStudent cs) {
        return CourseStudentData
                .builder()
                .course(CourseData.from(cs.getCourse()))
                .student(UserData.from(cs.getStudent()))
                .price(cs.getPrice())
                .status(cs.getStatus())
                .createdAt(cs.getCreatedAt())
                .updatedAt(cs.getUpdatedAt())
                .deletedAt(cs.getDeletedAt())
                .build();
    }

    public CourseStudent fromThis() {
        return CourseStudent
                .builder()
                .student(this.student.fromThis())
                .course(this.course.fromThis())
                .price(this.price)
                .status(this.status)
                .createdAt(this.createdAt)
                .updatedAt(this.updatedAt)
                .deletedAt(this.deletedAt)
                .build();
    }
}
