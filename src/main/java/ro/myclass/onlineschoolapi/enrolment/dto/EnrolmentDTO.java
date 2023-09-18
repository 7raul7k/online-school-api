package ro.myclass.onlineschoolapi.enrolment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnrolmentDTO {

        private Long id;
        private String studentName;
        private String courseName;
        private String professorName;
        private String department;
        private String grade;
        private String feedback;
}
