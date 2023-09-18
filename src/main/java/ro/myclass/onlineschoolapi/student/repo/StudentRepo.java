package ro.myclass.onlineschoolapi.student.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.myclass.onlineschoolapi.student.model.Student;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepo  extends JpaRepository<Student,Long> {

    @Query("select s from Student s where s.firstName = ?1")
    List<Student> findStudentsByFirstName(String firstName);

    @Query("select s from Student s where s.lastName = ?1")
    List<Student> findStudentsByLastName(String lastName);

    @Query("select s from Student s where s.age = ?1")
    List<Student> findStudentsByAge(int age);

    @Query("select s from Student s where s.email = ?1")
    Optional<Student> findStudentByEmail(String email);

    @Query("select s from Student s where s.adress = ?1")
    List<Student> findStudentsByAdress(String adress);

    @Query("select s from Student s where s.firstName = ?1 and s.lastName = ?2")
    Optional<Student> findStudentByFirstNameAndLastName(String firstName, String lastName);

    @Query("select s from Student s where s.firstName = ?1 and s.lastName = ?2 and s.age = ?3")
    Optional<Student> findStudentByFirstNameAndLastNameAndAge(String firstName, String lastName, int age);

    @Query("select s from Student s")
    List<Student> getAllStudent();

    @Query("select s from Student s where s.id = ?1")
    Optional<Student> getStudentById(long id);
}
