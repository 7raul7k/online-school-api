package ro.myclass.onlineschoolapi.course.rest;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.myclass.onlineschoolapi.course.PdfGenerator.CoursePDF;
import ro.myclass.onlineschoolapi.course.dto.CourseDTO;
import ro.myclass.onlineschoolapi.course.model.Course;
import ro.myclass.onlineschoolapi.course.service.CourseCommandService;
import ro.myclass.onlineschoolapi.course.service.CourseQuerryService;
import ro.myclass.onlineschoolapi.student.dto.CreateRestResponse;

import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/course")
@Slf4j
@CrossOrigin
public class CourseResource {

    private CourseCommandService courseCommandService;

    private CourseQuerryService courseQuerryService;

    public CourseResource(CourseCommandService courseCommandService, CourseQuerryService courseQuerryService) {
        this.courseCommandService = courseCommandService;
        this.courseQuerryService = courseQuerryService;
    }

    @GetMapping("/allCourses")
    public ResponseEntity<List<Course>> getAllCourses() throws InterruptedException {
        List<Course> courseList = courseQuerryService.getAllCourses();

        log.info("REST request to get all courses", courseList);

        Thread.sleep(5000);

        return new ResponseEntity<>(courseList, HttpStatus.OK);

    }

    @GetMapping("/courseById")
    public ResponseEntity<Course> getCourseById(@RequestParam int id){
        Course course = courseQuerryService.getCourseById(id);

        log.info("REST request to get course by id", course);

        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @GetMapping("/courseByName")
    public ResponseEntity<CreateRestResponse> getCourseByName(@RequestParam String name){
        Course course = courseQuerryService.getCourseByName(name);

        log.info("REST request to get course by name", course);

        return new ResponseEntity<>(new CreateRestResponse("Course with name " + name + " was found"), HttpStatus.OK);
    }

   @PostMapping("/addCourse")
    public ResponseEntity<CreateRestResponse> addCourse(@RequestBody CourseDTO course){
        courseCommandService.addCourse(course);

        log.info("REST request to add course", course);

        return new ResponseEntity<>(new CreateRestResponse("Course was added"), HttpStatus.OK);
    }

    @PutMapping("/updateCourse")
    public ResponseEntity<CreateRestResponse> updateCourse(@RequestBody CourseDTO course){
        courseCommandService.updateCourse(course);

        log.info("REST request to update course", course);

        return new ResponseEntity<>(new CreateRestResponse("Course was updated"), HttpStatus.OK);
    }

    @DeleteMapping("/deleteCourse")
    public ResponseEntity<CreateRestResponse> deleteCourse(@RequestParam String name){
        courseCommandService.deleteCourse(name);

        log.info("REST request to delete course", name);

        return new ResponseEntity<>(new CreateRestResponse("Course was deleted"), HttpStatus.OK);
    }

    @GetMapping("/exportPDF")
    public ResponseEntity<CreateRestResponse> exportCoursesPDF(HttpServletResponse response) throws Exception{

        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");

        String currentDate = dateFormat.format(new Date());

        String headerKey="Content-Disposition";
        String headerValue ="attachment;filename=coursesPdf_" + currentDate + ".pdf";

        List<Course> courseList = this.courseQuerryService.getAllCourses();

        CoursePDF coursePDF = new CoursePDF(courseList);

        response.setHeader(headerKey,headerValue);
        coursePDF.generate(response);

        return new ResponseEntity<>(new CreateRestResponse("PDF was generated"),HttpStatus.OK);
    }


}
