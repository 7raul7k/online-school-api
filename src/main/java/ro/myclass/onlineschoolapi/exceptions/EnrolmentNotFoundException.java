package ro.myclass.onlineschoolapi.exceptions;

public class EnrolmentNotFoundException extends RuntimeException{

        public EnrolmentNotFoundException() {
            super("Enrolment not found");
        }
}
