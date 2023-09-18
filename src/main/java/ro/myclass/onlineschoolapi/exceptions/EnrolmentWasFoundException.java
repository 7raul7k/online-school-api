package ro.myclass.onlineschoolapi.exceptions;

public class EnrolmentWasFoundException extends RuntimeException{

        public EnrolmentWasFoundException() {
            super("Enrolment was found");
        }
}
