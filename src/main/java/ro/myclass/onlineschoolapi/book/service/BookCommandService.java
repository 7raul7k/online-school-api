package ro.myclass.onlineschoolapi.book.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ro.myclass.onlineschoolapi.book.dto.BookDTO;

@Service
@Transactional
public interface BookCommandService {

    void addBook(BookDTO bookDTO);

    void deleteBook(String name);

    void updateBook(BookDTO bookDTO);

}
