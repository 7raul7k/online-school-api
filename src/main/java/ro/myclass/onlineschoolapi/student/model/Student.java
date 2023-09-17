package ro.myclass.onlineschoolapi.student.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

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




}
