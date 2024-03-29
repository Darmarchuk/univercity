package ado.rush.university.entity;


import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class Course {
    @Id
    @SequenceGenerator(name = "course_id_seq", sequenceName = "course_id_sec", initialValue = 1)
    @GeneratedValue(generator = "course_id_seq", strategy = GenerationType.SEQUENCE)
    private Long Id;
    private String name;
    private String description;
    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName = "id",nullable = true)
    private Trainer trainer;

    @OneToMany(mappedBy = "course")
    private List<Enrollment> enrollment;

    @LastModifiedDate
    private LocalDateTime modifyDt;

}

