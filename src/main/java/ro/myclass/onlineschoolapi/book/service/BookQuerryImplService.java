package ro.myclass.onlineschoolapi.book.service;

import org.springframework.stereotype.Service;
import ro.myclass.onlineschoolapi.book.model.Book;
import ro.myclass.onlineschoolapi.book.repo.BookRepo;
import ro.myclass.onlineschoolapi.exceptions.BookNotFoundException;
import ro.myclass.onlineschoolapi.exceptions.ListEmptyException;

import java.util.List;
import java.util.Optional;

@Service
public class BookQuerryImplService implements BookQuerryService{

    private BookRepo bookRepo;

    public BookQuerryImplService(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    public List<Book> getAllBooks() {
       List<Book> bookList = bookRepo.getAllBooks();

         if (bookList.isEmpty()) {
              throw new ListEmptyException();
         }

            return bookList;
    }

    public Book getBookById(long id) {
        Optional<Book> book = bookRepo.getBookById(id);

        if (book.isEmpty()) {
            throw new BookNotFoundException();
        }

        return book.get();
    }

    public Book getBookByName(String name) {
        Optional<Book> book = bookRepo.getBookByName(name);

        if (book.isEmpty()) {
            throw new BookNotFoundException();
        }

        return book.get();
    }





}
