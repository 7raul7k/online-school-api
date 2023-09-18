package ro.myclass.onlineschoolapi.exceptions;

public class CourseWasFoundException extends RuntimeException{

    public CourseWasFoundException() {
        super("Course was found");
    }
}
