package ro.myclass.onlineschoolapi.exceptions;

public class ProfessorWasFoundException extends RuntimeException{

    public ProfessorWasFoundException() {
        super("Professor was found");
    }
}
