package ro.myclass.onlineschoolapi.professor.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Table(name = "professor_db")
@Entity(name = "Professor")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Professor {
    @Id
    @SequenceGenerator(name = "professor_sequence",
            sequenceName = "professor_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "professor_sequence")
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
    @Column(name = "subject",
            nullable = false,
            columnDefinition = "TEXT")
    private String subject;

    @Override
    public String toString() {
        return id + "," + firstName + "," + lastName + "," + age + "," + email + "," + adress + "," + subject;
    }

    @Override
    public boolean equals(Object obj){
        Professor professor = (Professor) obj;

        if(professor.firstName.equals(this.firstName) && professor.lastName.equals(this.lastName) && professor.age == this.age && professor.email.equals(this.email) && professor.adress.equals(this.adress) && professor.subject.equals(this.subject)){
            return true;
        }
        return false;
    }


}
