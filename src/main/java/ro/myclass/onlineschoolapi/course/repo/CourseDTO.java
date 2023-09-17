package ro.myclass.onlineschoolapi.course.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.myclass.onlineschoolapi.course.model.Course;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseDTO extends JpaRepository<Course,Long> {

    @Query("select c from Course c where c.name = ?1")
    Optional<Course> getCourseByName(String name);

    @Query("select c from Course c where c.department = ?1")
    List<Course> getCoursesByDepartment(String department);

    @Query("select c from Course c where c.name = ?1 and c.department = ?2")
    Optional<Course> getCourseByNameAndDepartment(String name, String department);

    @Query("select c from Course c where c.professor.id = ?1")
    List<Course> getCoursesByProfessorId(long professorId);

}
