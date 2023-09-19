package ro.myclass.onlineschoolapi.enrolment.service;


import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ro.myclass.onlineschoolapi.course.dto.CourseDTO;
import ro.myclass.onlineschoolapi.course.model.Course;
import ro.myclass.onlineschoolapi.course.repo.CourseRepo;
import ro.myclass.onlineschoolapi.enrolment.dto.EnrolmentDTO;
import ro.myclass.onlineschoolapi.enrolment.model.Enrolment;
import ro.myclass.onlineschoolapi.enrolment.repo.EnrolmentRepo;
import ro.myclass.onlineschoolapi.exceptions.EnrolmentWasFoundException;
import ro.myclass.onlineschoolapi.exceptions.StudentNotFoundException;
import ro.myclass.onlineschoolapi.student.dto.StudentDTO;
import ro.myclass.onlineschoolapi.student.model.Student;
import ro.myclass.onlineschoolapi.student.repo.StudentRepo;

import java.util.Optional;

@Service
@Transactional
public class EnrolmentCommandImplService  implements EnrolmentCommandService{

    private EnrolmentRepo enrolmentRepo;

    private StudentRepo studentRepo;

    private CourseRepo courseRepo;

    public EnrolmentCommandImplService(EnrolmentRepo enrolmentRepo, StudentRepo studentRepo, CourseRepo courseRepo) {
        this.enrolmentRepo = enrolmentRepo;
        this.studentRepo = studentRepo;
        this.courseRepo = courseRepo;
    }

    public void addEnrolment(EnrolmentDTO enrolmentDTO) {

        Optional<Enrolment> enrolment = enrolmentRepo.getEnrolmentById(enrolmentDTO.getId());

        if(enrolment.isEmpty()){
            StudentDTO studentDTO = enrolmentDTO.getStudent();
            CourseDTO courseDTO = enrolmentDTO.getCourse();
            Optional<Student> student = studentRepo.findStudentByEmail(studentDTO.getEmail());

            if(student.isEmpty()){
                throw new StudentNotFoundException();
            }
            Optional<Course> course = courseRepo.getCourseByName(courseDTO.getName());

            if(course.isEmpty()){
                throw new StudentNotFoundException();
            }

            Enrolment enrolment1 = Enrolment.builder().student(student.get()).course(course.get()).build();

            enrolmentRepo.save(enrolment1);
        }else{
            throw new EnrolmentWasFoundException();
        }

    }

    public void deleteEnrolment(long id) {

        Optional<Enrolment> enrolment = enrolmentRepo.getEnrolmentById(id);

        if(enrolment.isEmpty()){
            throw new StudentNotFoundException();
        }else{
            enrolmentRepo.delete(enrolment.get());
        }

    }

    public void updateEnrolment(EnrolmentDTO enrolmentDTO){

        Optional<Enrolment> enrolment = enrolmentRepo.getEnrolmentById(enrolmentDTO.getId());

        if(enrolment.isEmpty()) {
            throw new StudentNotFoundException();
        }else{

            if(enrolmentDTO.getStudent() != null){
                StudentDTO studentDTO = enrolmentDTO.getStudent();
                Optional<Student> student = studentRepo.findStudentByEmail(studentDTO.getEmail());

                if(student.isEmpty()){
                    throw new StudentNotFoundException();
                }

                enrolment.get().setStudent(student.get());
            } if(enrolmentDTO.getCourse() !=null){
                CourseDTO courseDTO = enrolmentDTO.getCourse();
                Optional<Course> course = courseRepo.getCourseByName(courseDTO.getName());

                if(course.isEmpty()){
                    throw new StudentNotFoundException();
                }

                enrolment.get().setCourse(course.get());
            }

            enrolmentRepo.save(enrolment.get());
        }

    }
}