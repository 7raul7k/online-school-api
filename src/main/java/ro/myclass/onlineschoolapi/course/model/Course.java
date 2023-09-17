package ro.myclass.onlineschoolapi.course.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ro.myclass.onlineschoolapi.enrolment.model.Enrolment;
import ro.myclass.onlineschoolapi.professor.model.Professor;

import java.util.ArrayList;
import java.util.List;

@Table(name = "course_db")
@Entity(name = "Course")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Course {
    @Id
    @SequenceGenerator(name = "course_sequence",
            sequenceName = "course_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "course_sequence")
    @Column(name = "id")
    private long id;
    @Column(name = "name",
            nullable = false,
            columnDefinition = "TEXT")
    private String name;
    @Column(name ="department",
            nullable = false,
            columnDefinition = "TEXT")
    private String department;

    @ManyToOne
    @JoinColumn(name = "professor_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "professor_id_fk"))
    private Professor professor;

    @OneToMany(mappedBy = "course",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Enrolment> enrolment = new ArrayList<>();
}
