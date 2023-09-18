package ro.myclass.onlineschoolapi.exceptions;

public class ProfessorNotFoundException extends RuntimeException{

    public ProfessorNotFoundException() {
        super("Professor not found");
    }
}

