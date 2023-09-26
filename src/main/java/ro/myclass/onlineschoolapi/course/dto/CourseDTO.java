package ro.myclass.onlineschoolapi.course.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class CourseDTO {

    private String name;
    private String description;
    private String department;
}
