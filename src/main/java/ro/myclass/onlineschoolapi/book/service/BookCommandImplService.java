package ro.myclass.onlineschoolapi.book.service;


import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ro.myclass.onlineschoolapi.book.dto.BookDTO;
import ro.myclass.onlineschoolapi.book.model.Book;
import ro.myclass.onlineschoolapi.book.repo.BookRepo;
import ro.myclass.onlineschoolapi.exceptions.BookNotFoundException;
import ro.myclass.onlineschoolapi.exceptions.StudentNotFoundException;
import ro.myclass.onlineschoolapi.student.dto.StudentDTO;
import ro.myclass.onlineschoolapi.student.model.Student;
import ro.myclass.onlineschoolapi.student.repo.StudentRepo;

import java.util.Optional;

@Service
@Transactional
public class BookCommandImplService implements BookCommandService{

    private BookRepo bookRepo;

    private StudentRepo studentRepo;

    public BookCommandImplService(BookRepo bookRepo, StudentRepo studentRepo) {
        this.bookRepo = bookRepo;
        this.studentRepo = studentRepo;
    }

    public void addBook(BookDTO bookDTO) {
        Optional<Book> book = bookRepo.getBookByName(bookDTO.getName());

        if(book.isEmpty()){
            StudentDTO studentDTO = bookDTO.getStudent();

            Optional<Student> student = studentRepo.findStudentByEmail(studentDTO.getEmail());

            if(student.isEmpty()){
                throw new StudentNotFoundException();
            }

            Book book1 = Book.builder().bookName(bookDTO.getName()).student(student.get()).build();
            bookRepo.save(book1);
        }
    }

    public void updateBook(BookDTO bookDTO) {
        Optional<Book> book = bookRepo.getBookByName(bookDTO.getName());

        if(book.isPresent()){
            StudentDTO studentDTO = bookDTO.getStudent();

            Optional<Student> student = studentRepo.findStudentByEmail(studentDTO.getEmail());

            if(student.isEmpty()){
                throw new StudentNotFoundException();
            }

            Book book1 = Book.builder().bookName(bookDTO.getName()).student(student.get()).build();
            bookRepo.save(book1);
        }
    }

    public void deleteBook(String name) {
        Optional<Book> book = bookRepo.getBookByName(name);

        if(book.isEmpty()){
            throw new BookNotFoundException();
        }else{
            bookRepo.delete(book.get());
        }
    }
}
