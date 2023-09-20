package ro.myclass.onlineschoolapi.studentIdCard.rest;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.myclass.onlineschoolapi.student.CreateRestResponse;
import ro.myclass.onlineschoolapi.studentIdCard.dto.StudentIdCardDTO;
import ro.myclass.onlineschoolapi.studentIdCard.model.StudentIdCard;
import ro.myclass.onlineschoolapi.studentIdCard.service.StudentIdCardCommandService;
import ro.myclass.onlineschoolapi.studentIdCard.service.StudentIdCardQuerryService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/studentIdCard")
@Slf4j
public class StudentIdCardResource {
    private StudentIdCardCommandService studentIdCardCommandService;

    private StudentIdCardQuerryService studentIdCardQuerryService;

    public StudentIdCardResource(StudentIdCardCommandService studentIdCardCommandService, StudentIdCardQuerryService studentIdCardQuerryService) {
        this.studentIdCardCommandService = studentIdCardCommandService;
        this.studentIdCardQuerryService = studentIdCardQuerryService;
    }

    @GetMapping("/allStudentIdCards")
    public ResponseEntity<List<StudentIdCard>> getAllStudentIdCards(){
        List<StudentIdCard> studentIdCardList = studentIdCardQuerryService.getAllStudentIdCards();

        log.info("REST request to get all studentIdCards", studentIdCardList);

        return new ResponseEntity<>(studentIdCardList, HttpStatus.OK);

    }

    @GetMapping("/studentIdCardById")
    public ResponseEntity<StudentIdCard> getStudentIdCardById(@RequestParam long id){
        StudentIdCard studentIdCard = studentIdCardQuerryService.getStudentIdCardById(id);

        log.info("REST request to get studentIdCard by id", studentIdCard);

        return new ResponseEntity<>(studentIdCard, HttpStatus.OK);
    }

    @PostMapping("/addStudentIdCard")
    public ResponseEntity<CreateRestResponse> addStudentIdCard(@RequestBody StudentIdCardDTO studentIdCard){
        studentIdCardCommandService.addStudentIdCard(studentIdCard);

        log.info("REST request to add studentIdCard", studentIdCard);

        return new ResponseEntity<>(new CreateRestResponse("StudentIdCard was added"), HttpStatus.OK);
    }

    @PutMapping("/updateStudentIdCard")
    public ResponseEntity<CreateRestResponse> updateStudentIdCard(@RequestBody StudentIdCardDTO studentIdCard){
        studentIdCardCommandService.updateStudentIdCard(studentIdCard);

        log.info("REST request to update studentIdCard", studentIdCard);

        return new ResponseEntity<>(new CreateRestResponse("StudentIdCard was updated"), HttpStatus.OK);
    }

}
