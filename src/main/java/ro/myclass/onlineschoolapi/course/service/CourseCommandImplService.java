package ro.myclass.onlineschoolapi.course.service;


import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ro.myclass.onlineschoolapi.course.dto.CourseDTO;
import ro.myclass.onlineschoolapi.course.model.Course;
import ro.myclass.onlineschoolapi.course.repo.CourseRepo;
import ro.myclass.onlineschoolapi.exceptions.CourseNotFoundException;
import ro.myclass.onlineschoolapi.exceptions.CourseWasFoundException;

import java.util.Optional;

@Service
@Transactional
public class CourseCommandImplService implements CourseCommandService {

    private CourseRepo courseRepo;

    public CourseCommandImplService(CourseRepo courseRepo) {
        this.courseRepo = courseRepo;
    }

    public void addCourse(CourseDTO courseDTO) {

        Optional<Course> course = courseRepo.getCourseByName(courseDTO.getName());

        if(course.isEmpty()){
            Course course1 = new Course();
            course1.setName(courseDTO.getName());
            course1.setDepartment(courseDTO.getDepartment());
            courseRepo.save(course1);
        }else{
            throw new CourseWasFoundException();
        }

    }

    public void deleteCourse(String name) {

        Optional<Course> course = courseRepo.getCourseByName(name);

        if(course.isEmpty()){
            throw new CourseNotFoundException();
        }else{
            courseRepo.delete(course.get());
        }

    }

    public void updateCourse(CourseDTO courseDTO){

        Optional<Course> course = courseRepo.getCourseByName(courseDTO.getName());

        if(course.isEmpty()) {
            throw new CourseNotFoundException();
        }else{

            if(courseDTO.getName() != null){
                course.get().setName(courseDTO.getName());
            } if(courseDTO.getDepartment() !=null){
                course.get().setDepartment(courseDTO.getDepartment());
            }

            courseRepo.save(course.get());
        }

    }
}
