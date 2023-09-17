package ro.myclass.onlineschoolapi.studentIdCard.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.myclass.onlineschoolapi.studentIdCard.model.StudentIdCard;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentIdCardRepo extends JpaRepository<StudentIdCard,Long> {

    @Query("select s from StudentIdCard s where s.id = ?1")
    List<StudentIdCard> getStudentIdCardById(long id);

    @Query("select s from StudentIdCard s where s.student.id = ?1")
    Optional<StudentIdCard> getStudentIdCardByStudentId(long studentId);

    @Query("select s from StudentIdCard s")
    Optional<StudentIdCard> getStudentIdCard();

    @Query("select s from StudentIdCard s where s.student.id = ?1")
    List<StudentIdCard> getStudentIdCardByStudentId(long studentId);
    

}
