package ro.myclass.onlineschoolapi.student.rest;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.myclass.onlineschoolapi.student.CreateRestResponse;
import ro.myclass.onlineschoolapi.student.PdfGenerator.StudentPDF;
import ro.myclass.onlineschoolapi.student.dto.StudentDTO;
import ro.myclass.onlineschoolapi.student.model.Student;
import ro.myclass.onlineschoolapi.student.service.StudentCommandService;
import ro.myclass.onlineschoolapi.student.service.StudentQuerryService;

import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/student")
@Slf4j
@CrossOrigin
public class StudentResources {

    private StudentCommandService studentCommandService;


    private StudentQuerryService studentQueryService;

    public StudentResources(StudentCommandService studentCommandService, @Qualifier("studentQuerryImplService") StudentQuerryService studentQueryService) {
        this.studentCommandService = studentCommandService;
        this.studentQueryService = studentQueryService;
    }

    @GetMapping("/allStudents")
    public ResponseEntity<List<Student>> getAllStudents(){
        List<Student> studentList = studentQueryService.getAllStudents();

        log.info("REST request to get all students", studentList);

        return new ResponseEntity<>(studentList, HttpStatus.OK);

    }

    @GetMapping("/studentById")
    public ResponseEntity<Student> getStudentById(@RequestParam int id){
        Student student = studentQueryService.getStudentById(id);

        log.info("REST request to get student by id", student);

        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @GetMapping("/studentByEmail")
    public ResponseEntity<Student> getStudentByEmail(@RequestParam String email){
        Student student = studentQueryService.getStudentByEmail(email);

        log.info("REST request to get student by email", student);

        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @GetMapping("/getStudentsByFirstName")
    public ResponseEntity<List<Student>> getStudentByFirstName(@RequestParam String firstName){
        List<Student> studentList = studentQueryService.getStudentsByFirstName(firstName);

          log.info("REST request to get student by first name", studentList);

        return new ResponseEntity<>(studentList, HttpStatus.OK);
    }

    @PostMapping("/addStudent")
    public ResponseEntity<CreateRestResponse> addStudent(@RequestBody StudentDTO student){
        studentCommandService.addStudent(student);

        log.info("REST request to add student", student);

        return new ResponseEntity<>(new CreateRestResponse("Student was added"), HttpStatus.OK);
    }

    @PutMapping("/updateStudent")
    public ResponseEntity<CreateRestResponse> updateStudent(@RequestBody StudentDTO student){
        studentCommandService.updateStudent(student);

        log.info("REST request to update student", student);

        return new ResponseEntity<>(new CreateRestResponse("Student was updated"), HttpStatus.OK);
    }

    @DeleteMapping("/deleteStudent")
    public ResponseEntity<CreateRestResponse> deleteStudent(@RequestParam String firstName, @RequestParam String lastName){
        studentCommandService.deleteStudent(firstName, lastName);

        log.info("REST request to delete student", firstName, lastName);

        return new ResponseEntity<>(new CreateRestResponse("Student was deleted"), HttpStatus.OK);
    }

    @GetMapping("/getStudentsByLastName")
    public ResponseEntity<List<Student>> getStudentByLastName(@RequestParam String lastName){
        List<Student> studentList = studentQueryService.getStudentsByLastName(lastName);

        log.info("REST request to get student by last name", studentList);

        return new ResponseEntity<>(studentList, HttpStatus.OK);
    }

    @GetMapping("/getStudentsByAge")
    public ResponseEntity<List<Student>> getStudentByAge(@RequestParam int age){
        List<Student> studentList = studentQueryService.getStudentsByAge(age);

        log.info("REST request to get student by age", studentList);

        return new ResponseEntity<>(studentList, HttpStatus.OK);
    }

    @GetMapping("/getStudentsByAdress")
    public ResponseEntity<List<Student>> getStudentByAdress(@RequestParam String adress){
        List<Student> studentList = studentQueryService.getStudentsByAdress(adress);

        log.info("REST request to get student by adress", studentList);

        return new ResponseEntity<>(studentList, HttpStatus.OK);
    }

    @GetMapping("/getStudentByFirstNameAndLastName")
    public ResponseEntity<Student> getStudentByFirstNameAndLastName(@RequestParam String firstName, @RequestParam String lastName){
        Student student = studentQueryService.getStudentByFirstNameAndLastName(firstName, lastName);

        log.info("REST request to get student by first name and last name", student);

        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @GetMapping("/getStudentByFirstNameAndLastNameAndAge")
    public ResponseEntity<Student> getStudentByFirstNameAndLastNameAndAge(@RequestParam String firstName, @RequestParam String lastName, @RequestParam int age){
        Student student = studentQueryService.getStudentByFirstNameAndLastNameAndAge(firstName, lastName, age);

        log.info("REST request to get student by first name and last name and age", student);

        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @GetMapping("/exportPDF")
    public ResponseEntity<CreateRestResponse> exportPdf(HttpServletResponse response)throws Exception {

        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
        String currentDate = dateFormat.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment;filename=studentsPdf_" +  currentDate +".pdf";

        List<Student> studentList = this.studentQueryService.getAllStudents();

        StudentPDF studentPDF = new StudentPDF(studentList);

        studentPDF.generate(response);

        return new ResponseEntity<>(new CreateRestResponse("PDF was created successfully"),HttpStatus.OK);




    }
    

}
