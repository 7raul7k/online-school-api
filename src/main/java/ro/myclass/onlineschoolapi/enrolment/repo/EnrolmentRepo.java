package ro.myclass.onlineschoolapi.enrolment.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.myclass.onlineschoolapi.enrolment.model.Enrolment;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnrolmentRepo extends JpaRepository<Enrolment,Long> {

    @Query("select e from Enrolment e where e.student.id = ?1")
    List<Enrolment> getEnrolmentsByStudentId(long studentId);

    @Query("select e from Enrolment e where e.course.id = ?1")
    List<Enrolment> getEnrolmentsByCourseId(long courseId);


    @Query("select e from Enrolment e")
    List<Enrolment> getAllEnrolments();

    @Query("select e from Enrolment e where e.id = ?1")
    Optional<Enrolment> getEnrolmentById(long id);
}
