package ro.myclass.onlineschoolapi.exceptions;

public class ListEmptyException extends RuntimeException{
    public ListEmptyException() {
        super("List is empty");
    }
}
