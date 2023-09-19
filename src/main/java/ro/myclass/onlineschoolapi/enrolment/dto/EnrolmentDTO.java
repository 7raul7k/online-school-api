package ro.myclass.onlineschoolapi.enrolment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ro.myclass.onlineschoolapi.course.dto.CourseDTO;
import ro.myclass.onlineschoolapi.student.dto.StudentDTO;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class EnrolmentDTO {
        private String studentName;
        private String courseName;
        private String professorName;
        private String department;
        private String grade;
        private StudentDTO student;
        private CourseDTO course;
}
