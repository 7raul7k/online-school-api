package ro.myclass.onlineschoolapi.exceptions;

public class StudentIdCardNotFoundException extends RuntimeException{

    public StudentIdCardNotFoundException() {
      super("StudentIdCard not found");
    }
}
