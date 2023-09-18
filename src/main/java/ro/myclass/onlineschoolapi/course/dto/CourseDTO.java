package ro.myclass.onlineschoolapi.course.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ro.myclass.onlineschoolapi.professor.dto.ProfessorDTO;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class CourseDTO {

    private String name;
    private String description;
    private ProfessorDTO professor;
    private String department;
}
