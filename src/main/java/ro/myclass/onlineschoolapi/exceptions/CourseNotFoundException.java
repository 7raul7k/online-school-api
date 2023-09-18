package ro.myclass.onlineschoolapi.exceptions;


public class CourseNotFoundException extends RuntimeException{

    public CourseNotFoundException() {
        super("Course not found");
    }
}
