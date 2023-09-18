package ro.myclass.onlineschoolapi.studentIdCard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ro.myclass.onlineschoolapi.student.dto.StudentDTO;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class StudentIdCardDTO {

    private int cardNumber;
    private StudentDTO student;


}
