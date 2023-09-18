package ro.myclass.onlineschoolapi.exceptions;

public class BookNotFoundException extends RuntimeException{

            public BookNotFoundException() {
                super("Book not found");
            }
}
