package ro.myclass.onlineschoolapi.student.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ro.myclass.onlineschoolapi.book.model.Book;
import ro.myclass.onlineschoolapi.enrolment.model.Enrolment;
import ro.myclass.onlineschoolapi.studentIdCard.model.StudentIdCard;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Table(name = "student_db")
@Entity(name = "Student")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Student {
    @Id
    @SequenceGenerator(name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE,
            generator = "student_sequence")
    @Column(name = "id")
    private long id;
    @Column(name = "first_name",
            nullable = false,
            columnDefinition = "TEXT")
    private String firstName;
    @Column(name = "last_name",
            nullable = false,
            columnDefinition = "TEXT")
    private String lastName;
    @Column(name = "age",
            nullable = false,
            columnDefinition = "INT")
    private int age;
    @Column(name = "email",
            nullable = false,
            columnDefinition = "TEXT")
    private String email;
    @Column(name = "adress",
            nullable = false,
            columnDefinition = "TEXT")
    private String adress;

    @Override
    public String toString(){
        return id+","+firstName+","+lastName+","+age+","+email+","+adress;
    }

    @Override
    public boolean equals(Object obj){
        Student student = (Student) obj;

        if(student.firstName.equals(this.firstName) && student.lastName.equals(this.lastName) && student.age == this.age && student.email.equals(this.email) && student.adress.equals(this.adress)){
            return true;
        }
            return false;

    }

    @ManyToOne
    @JoinColumn(name = "enrolment_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "enrolment_id_fk"))
    private Enrolment enrolment;

    @OneToMany(mappedBy = "student",
            cascade = CascadeType.ALL,
            orphanRemoval = true)

    private List<Enrolment> enrolments = new ArrayList<>();


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name ="book_db_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "book_db_id_fk"))
    private Book book;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id_card_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "student_id_card_id_fk"))
    private StudentIdCard studentIdCard;


}
