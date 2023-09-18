package ro.myclass.onlineschoolapi.professor.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.myclass.onlineschoolapi.professor.model.Professor;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfessorRepo  extends JpaRepository<Professor,Long> {

    @Query("select p from Professor p")
    List<Professor> getAllProfessor();
    @Query("select p from Professor p where p.firstName = ?1")
    List<Professor> getProfessorByFirstName(String firstName);

    @Query("select p from Professor p where p.lastName = ?1")
    List<Professor> getProfessorByLastName(String lastName);

    @Query("select p from Professor p where p.age = ?1")
    List<Professor> getProfessorByAge(int age);

    @Query("select p from Professor p where p.email = ?1")
    Optional<Professor> getProfessorByEmail(String email);

    @Query("select p from Professor p where p.adress = ?1")
    List<Professor> getProfessorByAdress(String adress);

    @Query("select p from Professor p where p.subject = ?1")
    List<Professor> getProfessorBySubject(String subject);

    @Query("select p from Professor p where p.firstName = ?1 and p.lastName = ?2")
    Optional<Professor> getProfessorByFirstNameAndLastName(String firstName, String lastName);

    @Query("select p from Professor p where p.firstName = ?1 and p.lastName = ?2 and p.age = ?3")
    Optional<Professor> getProfessorByFirstNameAndLastNameAndAge(String firstName, String lastName, int age);

    @Query("select p from Professor p where p.id = ?1")
    Optional<Professor> getProfessorById(long id);


}
