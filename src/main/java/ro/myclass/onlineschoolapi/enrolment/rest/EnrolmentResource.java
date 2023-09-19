package ro.myclass.onlineschoolapi.enrolment.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.myclass.onlineschoolapi.enrolment.dto.EnrolmentDTO;
import ro.myclass.onlineschoolapi.enrolment.model.Enrolment;
import ro.myclass.onlineschoolapi.enrolment.service.EnrolmentCommandService;
import ro.myclass.onlineschoolapi.enrolment.service.EnrolmentQuerryService;
import ro.myclass.onlineschoolapi.student.CreateRestResponse;

import java.util.List;

@RestController
@RequestMapping("/api/v1/enrolment")
@Slf4j
public class EnrolmentResource {

    private EnrolmentCommandService enrolmentCommandService;

    private EnrolmentQuerryService enrolmentQuerryService;

    public EnrolmentResource(EnrolmentCommandService enrolmentCommandService, EnrolmentQuerryService enrolmentQuerryService) {
        this.enrolmentCommandService = enrolmentCommandService;
        this.enrolmentQuerryService = enrolmentQuerryService;
    }

    @GetMapping("/allEnrolments")
    public ResponseEntity<List<Enrolment>> getAllEnrolments(){
        List<Enrolment> enrolmentList = enrolmentQuerryService.getAllEnrolments();

        log.info("REST request to get all enrolments", enrolmentList);

        return new ResponseEntity<>(enrolmentList, HttpStatus.OK);

    }

    @GetMapping("/enrolmentById")
    public ResponseEntity<Enrolment> getEnrolmentById(@RequestParam long id){
        Enrolment enrolment = enrolmentQuerryService.getEnrolmentById(id);

        log.info("REST request to get enrolment by id", enrolment);

        return new ResponseEntity<>(enrolment, HttpStatus.OK);
    }

    @GetMapping("/enrolmentByStudentId")
    public ResponseEntity<List<Enrolment>> getEnrolmentByStudentId(@RequestParam long studentId){
        List<Enrolment> enrolmentList = enrolmentQuerryService.getEnrolmentByStudentId(studentId);

        log.info("REST request to get enrolment by student id", enrolmentList);

        return new ResponseEntity<>(enrolmentList, HttpStatus.OK);
    }

    @GetMapping("/enrolmentByCourseId")
    public ResponseEntity<List<Enrolment>> getEnrolmentByCourseId(@RequestParam long courseId){
        List<Enrolment> enrolmentList = enrolmentQuerryService.getEnrolmentByCourseId(courseId);

        log.info("REST request to get enrolment by course id", enrolmentList);

        return new ResponseEntity<>(enrolmentList, HttpStatus.OK);
    }

  @PostMapping("/addEnrolment")
    public ResponseEntity<CreateRestResponse> addEnrolment(@RequestBody EnrolmentDTO enrolment){
        enrolmentCommandService.addEnrolment(enrolment);

        log.info("REST request to add enrolment", enrolment);

        return new ResponseEntity<>(new CreateRestResponse("Enrolment was added"), HttpStatus.OK);
    }

    @PutMapping("/updateEnrolment")
    public ResponseEntity<CreateRestResponse> updateEnrolment(@RequestBody EnrolmentDTO enrolment){
        enrolmentCommandService.updateEnrolment(enrolment);

        log.info("REST request to update enrolment", enrolment);

        return new ResponseEntity<>(new CreateRestResponse("Enrolment was updated"), HttpStatus.OK);
    }

    @DeleteMapping("/deleteEnrolment")
    public ResponseEntity<CreateRestResponse> deleteEnrolment(@RequestParam long id){
        enrolmentCommandService.deleteEnrolment(id);

        log.info("REST request to delete enrolment", id);

        return new ResponseEntity<>(new CreateRestResponse("Enrolment was deleted"), HttpStatus.OK);
    }
}
