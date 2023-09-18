package ro.myclass.onlineschoolapi.book.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ro.myclass.onlineschoolapi.student.dto.StudentDTO;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class BookDTO {


        private String name;
        private String author;
        private StudentDTO student;
}
