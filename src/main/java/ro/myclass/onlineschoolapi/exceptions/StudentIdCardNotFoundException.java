package ro.myclass.onlineschoolapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class StudentIdCardNotFoundException extends RuntimeException{

    public StudentIdCardNotFoundException() {
      super("StudentIdCard not found");
    }
}
