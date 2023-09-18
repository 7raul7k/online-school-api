package ro.myclass.onlineschoolapi.student.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class StudentDTO {

    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private String adress;
}
