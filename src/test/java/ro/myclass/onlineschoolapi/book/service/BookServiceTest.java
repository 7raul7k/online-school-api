package ro.myclass.onlineschoolapi.book.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ro.myclass.onlineschoolapi.book.dto.BookDTO;
import ro.myclass.onlineschoolapi.book.model.Book;
import ro.myclass.onlineschoolapi.book.repo.BookRepo;
import ro.myclass.onlineschoolapi.exceptions.BookNotFoundException;
import ro.myclass.onlineschoolapi.exceptions.ListEmptyException;
import ro.myclass.onlineschoolapi.exceptions.StudentNotFoundException;
import ro.myclass.onlineschoolapi.student.dto.StudentDTO;
import ro.myclass.onlineschoolapi.student.model.Student;
import ro.myclass.onlineschoolapi.student.repo.StudentRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepo bookRepo;

    @Mock
    private StudentRepo studentRepo;

    @InjectMocks
    private BookCommandService bookCommandService = new BookCommandImplService(bookRepo, studentRepo);

    @InjectMocks
    private BookQuerryService bookQuerryService = new BookQuerryImplService(bookRepo);

    @Captor
    private ArgumentCaptor<Book> argumentCaptor;

    @Test
    public void getAllBooks() {

        Book book = Book.builder().bookName("Math").build();
        Book book1 = Book.builder().bookName("English").build();
        Book book2 = Book.builder().bookName("French").build();
        Book book3 = Book.builder().bookName("German").build();

        List<Book> bookList = new ArrayList<>();
        bookList.add(book);
        bookList.add(book1);
        bookList.add(book2);
        bookList.add(book3);

        doReturn(bookList).when(bookRepo).getAllBooks();

        assertEquals(bookList, bookQuerryService.getAllBooks());
    }

    @Test
    public void getAllBooksException(){
        List<Book> bookList = new ArrayList<>();

        doReturn(bookList).when(bookRepo).getAllBooks();

        assertThrows(ListEmptyException.class, () -> bookQuerryService.getAllBooks());
    }

    @Test
    public void getBookById() {
        Book book = Book.builder().bookName("Math").build();

        doReturn(Optional.of(book)).when(bookRepo).getBookById(1);

        assertEquals(book, bookQuerryService.getBookById(1));
    }

    @Test
    public void getBookByIdException() {

        doReturn(Optional.empty()).when(bookRepo).getBookById(1);

        assertThrows(BookNotFoundException.class, () -> bookQuerryService.getBookById(1));
    }

    @Test
    public void getBookByName() {
        Book book = Book.builder().bookName("Math").build();

        doReturn(Optional.of(book)).when(bookRepo).getBookByName("Math");

        assertEquals(book, bookQuerryService.getBookByName("Math"));
    }

    @Test
    public void getBookByNameException() {

        doReturn(Optional.empty()).when(bookRepo).getBookByName("Math");

        assertThrows(BookNotFoundException.class, () -> bookQuerryService.getBookByName("Math"));
    }

    @Test
    public void getBookByStudent() {
        Book book = Book.builder().bookName("Math").build();

        doReturn(Optional.of(book)).when(bookRepo).getBookByStudent(1);

        assertEquals(book, bookQuerryService.getBookByStudent(1));
    }


    @Test
    public void getBookByStudentException() {

        doReturn(Optional.empty()).when(bookRepo).getBookByStudent(1);

        assertThrows(BookNotFoundException.class, () -> bookQuerryService.getBookByStudent(1));
    }

    @Test
    public void addBook() {
        Student student = Student.builder().email("email").firstName("Popescu").lastName("Andrei").adress("Bucuresti").build();

        StudentDTO studentDTO = StudentDTO.builder().email("email").firstName("Popescu").lastName("Andrei").adress("Bucuresti").build();
        Book book = Book.builder().bookName("Math").student(student).build();

        BookDTO bookDTO = BookDTO.builder().name("Math").student(studentDTO).build();

        doReturn(Optional.empty()).when(bookRepo).getBookByName("Math");
        doReturn(Optional.of(student)).when(studentRepo).findStudentByEmail("email");

        this.bookCommandService.addBook(bookDTO);

      verify(bookRepo).save(argumentCaptor.capture());

        assertEquals(book, argumentCaptor.getValue());
    }

    @Test
    public void addBookStudentException() {

     
        StudentDTO studentDTO = StudentDTO.builder().email("email").firstName("Popescu").lastName("Andrei").adress("Bucuresti").build();

        BookDTO bookDTO = BookDTO.builder().name("Math").student(studentDTO).build();

        doReturn(Optional.empty()).when(studentRepo).findStudentByEmail("email");

        assertThrows(StudentNotFoundException.class, () -> this.bookCommandService.addBook(bookDTO));
    }

    @Test
    public void addBookException() {

        Student student = Student.builder().email("email").firstName("Popescu").lastName("Andrei").adress("Bucuresti").build();

        StudentDTO studentDTO = StudentDTO.builder().email("email").firstName("Popescu").lastName("Andrei").adress("Bucuresti").build();

        BookDTO bookDTO = BookDTO.builder().name("Math").student(studentDTO).build();

        doReturn(Optional.of(Book.builder().bookName("Math").student(student).build())).when(bookRepo).getBookByName("Math");

        assertThrows(BookNotFoundException.class, () -> this.bookCommandService.addBook(bookDTO));
    }

    @Test
    public void updateBook() {
        Student student = Student.builder().email("email").firstName("Popescu").lastName("Andrei").adress("Bucuresti").build();

        StudentDTO studentDTO = StudentDTO.builder().email("email").firstName("Popescu").lastName("Andrei").adress("Bucuresti").build();
        Book book = Book.builder().bookName("Math").student(student).build();

        BookDTO bookDTO = BookDTO.builder().name("Math").student(studentDTO).build();

        doReturn(Optional.of(book)).when(bookRepo).getBookByName("Math");
        doReturn(Optional.of(student)).when(studentRepo).findStudentByEmail("email");

        this.bookCommandService.updateBook(bookDTO);

        verify(bookRepo).save(argumentCaptor.capture());

        assertEquals(book, argumentCaptor.getValue());
    }

    @Test
    public void updateBookStudentException() {

        StudentDTO studentDTO = StudentDTO.builder().email("email").firstName("Popescu").lastName("Andrei").adress("Bucuresti").build();

        BookDTO bookDTO = BookDTO.builder().name("Math").student(studentDTO).build();

        Book book = Book.builder().bookName("Math").build();

        doReturn(Optional.of(book)).when(bookRepo).getBookByName("Math");
        doReturn(Optional.empty()).when(studentRepo).findStudentByEmail("email");

        assertThrows(StudentNotFoundException.class, () -> this.bookCommandService.updateBook(bookDTO));
    }

    @Test
    public void updateBookException() {

        StudentDTO studentDTO = StudentDTO.builder().email("email").firstName("Popescu").lastName("Andrei").adress("Bucuresti").build();

        BookDTO bookDTO = BookDTO.builder().name("Math").student(studentDTO).build();

        doReturn(Optional.empty()).when(bookRepo).getBookByName("Math");

        assertThrows(BookNotFoundException.class, () -> this.bookCommandService.updateBook(bookDTO));
    }

    @Test
    public void deleteBook() {
        Book book = Book.builder().bookName("Math").build();

        doReturn(Optional.of(book)).when(bookRepo).getBookByName("Math");

        this.bookCommandService.deleteBook("Math");

        verify(bookRepo).delete(argumentCaptor.capture());

        assertEquals(book, argumentCaptor.getValue());
    }

    @Test
    public void deleteBookException() {

        doReturn(Optional.empty()).when(bookRepo).getBookByName("Math");

        assertThrows(BookNotFoundException.class, () -> this.bookCommandService.deleteBook("Math"));
    }

}