package ro.myclass.onlineschoolapi.exceptions;

public class StudentWasFoundException extends RuntimeException{
    public StudentWasFoundException() {
        super("Student was found");
    }
}
