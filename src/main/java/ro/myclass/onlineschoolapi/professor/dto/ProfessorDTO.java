package ro.myclass.onlineschoolapi.professor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ProfessorDTO {

    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private String adress;
    private String subject;
}
