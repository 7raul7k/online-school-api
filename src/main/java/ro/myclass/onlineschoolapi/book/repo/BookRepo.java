package ro.myclass.onlineschoolapi.book.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.myclass.onlineschoolapi.book.model.Book;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepo extends JpaRepository<Book,Long> {

        @Query("select b from Book b where b.id = ?1")
        Optional<Book> getBookById(long id);

        @Query("select b from Book b where b.bookName = ?1")
        Optional<Book> getBookByName(String name);

        @Query("select b from Book b where b.student = ?1")
        List<Book> getBookByStudent(long studentId);
}
