package ro.myclass.onlineschoolapi.book.rest;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.myclass.onlineschoolapi.book.dto.BookDTO;
import ro.myclass.onlineschoolapi.book.model.Book;
import ro.myclass.onlineschoolapi.book.service.BookCommandService;
import ro.myclass.onlineschoolapi.book.service.BookQuerryService;
import ro.myclass.onlineschoolapi.student.CreateRestResponse;

import java.util.List;

@RestController
@RequestMapping("/api/v1/book")
@Slf4j
public class BookResource {

    private BookCommandService bookCommandService;

    private BookQuerryService bookQuerryService;

    public BookResource(BookCommandService bookCommandService, BookQuerryService bookQuerryService) {
        this.bookCommandService = bookCommandService;
        this.bookQuerryService = bookQuerryService;
    }

    @GetMapping("/allBooks")
    public ResponseEntity<List<Book>> getAllBooks(){
        List<Book> bookList = bookQuerryService.getAllBooks();

        log.info("REST request to get all books", bookList);

        return new ResponseEntity<>(bookList, HttpStatus.OK);

    }

    @GetMapping("/bookById")
    public ResponseEntity<Book> getBookById(@RequestParam long id){
        Book book = bookQuerryService.getBookById(id);

        log.info("REST request to get book by id", book);

        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @GetMapping("/bookByName")
    public ResponseEntity<Book> getBookByName(@RequestParam String name){
        Book book = bookQuerryService.getBookByName(name);

        log.info("REST request to get book by name", book);

        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @GetMapping("/getBooksByStudentId")
    public ResponseEntity<Book> getBooksByStudentId(@RequestParam int studentId){
       Book book = bookQuerryService.getBookByStudent((long) studentId);

        log.info("REST request to get book by student id", book);

        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @PostMapping("/addBook")
    public ResponseEntity<CreateRestResponse> addBook(@RequestBody BookDTO book){
        bookCommandService.addBook(book);

        log.info("REST request to add book", book);

        return new ResponseEntity<>(new CreateRestResponse("Book was added"), HttpStatus.OK);
    }

    @PutMapping("/updateBook")
    public ResponseEntity<CreateRestResponse> updateBook(@RequestBody BookDTO book){
        bookCommandService.updateBook(book);

        log.info("REST request to update book", book);

        return new ResponseEntity<>(new CreateRestResponse("Book was updated"), HttpStatus.OK);
    }

    @DeleteMapping("/deleteBook")
    public ResponseEntity<CreateRestResponse> deleteBook(@RequestParam String name){
        bookCommandService.deleteBook(name);

        log.info("REST request to delete book", name);

        return new ResponseEntity<>(new CreateRestResponse("Book was deleted"), HttpStatus.OK);
    }



}
