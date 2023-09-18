package ro.myclass.onlineschoolapi.course.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ro.myclass.onlineschoolapi.course.dto.CourseDTO;

@Service
@Transactional
public interface CourseCommandService {

    void addCourse(CourseDTO courseDTO);
    void deleteCourse(String name);
    void updateCourse(CourseDTO courseDTO);
}
