package ro.myclass.onlineschoolapi.book.repo;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ro.myclass.onlineschoolapi.OnlineSchoolApiApplication;
import ro.myclass.onlineschoolapi.book.model.Book;
import ro.myclass.onlineschoolapi.student.model.Student;
import ro.myclass.onlineschoolapi.student.repo.StudentRepo;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = OnlineSchoolApiApplication.class)
@Transactional
class BookRepoTest {

    @Autowired
    public BookRepo bookRepo;

    @Autowired
    public StudentRepo studentRepo;

    @BeforeEach
    public void clean() {
        bookRepo.deleteAll();
    }

    @Test
    public void getAllBooks() {
        Book book = Book.builder().bookName("Matematica").id(1).build();
        Book book1 = Book.builder().bookName("Romana").id(2).build();
        Book book2 = Book.builder().bookName("Istorie").id(3).build();
        Book book3 = Book.builder().bookName("Geografie").id(4).build();

        bookRepo.save(book);
        bookRepo.save(book1);
        bookRepo.save(book2);
        bookRepo.save(book3);

        List<Book> bookList = new ArrayList<>();
        bookList.add(book);
        bookList.add(book1);
        bookList.add(book2);
        bookList.add(book3);

        assertEquals(bookList, bookRepo.getAllBooks());
    }

    @Test
    public void getBookByName() {
        Book book = Book.builder().bookName("Matematica").id(1).build();
        bookRepo.save(book);
        assertEquals(book, bookRepo.getBookByName("Matematica").get());
    }

    @Test
    public void getBookById() {
        Book book = Book.builder().bookName("Matematica").id(1).build();
        bookRepo.save(book);
        assertEquals(book, bookRepo.getBookById(1).get());
    }

     @Test
    public void getBookByStudent(){
         Student student = Student.builder().firstName("Popescu Andrei").lastName("Popescu").age(12).email("").adress("").build();

            Book book = Book.builder().bookName("Matematica").id(1).student(student).build();

            bookRepo.save(book);

            studentRepo.save(student);

            assertEquals(book, bookRepo.getBookByStudent(1).get());
     }

}