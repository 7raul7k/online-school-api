package ro.myclass.onlineschoolapi.enrolment.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ro.myclass.onlineschoolapi.course.model.Course;
import ro.myclass.onlineschoolapi.student.model.Student;

import java.util.ArrayList;
import java.util.List;

@Table(name = "enrolment_db")
@Entity(name = "Enrolment")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Enrolment {
    @Id
    @SequenceGenerator(name = "enrolment_sequence",
            sequenceName = "enrolment_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "enrolment_sequence")
    @Column(name = "id")
    private long id;

    @OneToMany(mappedBy = "enrolment",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Student> student = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "course_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "course_id_fk"))
    private Course course;
}
