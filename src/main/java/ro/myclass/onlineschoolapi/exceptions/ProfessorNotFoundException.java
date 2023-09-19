package ro.myclass.onlineschoolapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProfessorNotFoundException extends RuntimeException{

    public ProfessorNotFoundException() {
        super("Professor not found");
    }
}

