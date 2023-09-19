package ro.myclass.onlineschoolapi.exceptions;

public class StudentIdCardWasFoundException extends RuntimeException{

    public StudentIdCardWasFoundException() {
        super("StudentIdCard was found");
    }
}
