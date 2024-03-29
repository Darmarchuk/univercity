package ado.rush.university.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class Enrollment {
    @Id
    @SequenceGenerator(name = "enrollment_id_seq",sequenceName = "enrollment_id_seq",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "enrollment_id_seq")
    private Long id;

    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "student_id"
            , referencedColumnName = "id",
    foreignKey = @ForeignKey(name="enrollement_sutudent_id_fk"))
    private Student student;

    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name = "course_id"
            , referencedColumnName = "id",
            foreignKey = @ForeignKey(name="enrollement_course_id_fk"))
    private Course course;


    @LastModifiedDate
    private LocalDateTime updatedDt;

}
