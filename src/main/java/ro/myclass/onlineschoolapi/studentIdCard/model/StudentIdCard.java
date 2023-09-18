package ro.myclass.onlineschoolapi.studentIdCard.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ro.myclass.onlineschoolapi.student.model.Student;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Table(name = "student_id_card_db")
@Entity(name = "StudentIdCard")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class StudentIdCard {
    @Id
    @SequenceGenerator(name = "student_id_card_sequence",
            sequenceName = "student_id_card_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE,
            generator = "student_id_card_sequence")
    @Column(name = "id")
    private long id;

    @Column(name ="card_number",
            columnDefinition = "INT")
    private int cardNumber;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student student;



}
