package ro.myclass.onlineschoolapi.book.service;

import org.springframework.stereotype.Service;
import ro.myclass.onlineschoolapi.book.model.Book;

import java.util.List;

@Service
public interface BookQuerryService {

    List<Book> getAllBooks();
    Book getBookById(long id);
    Book getBookByName(String name);

    
}
