package ro.myclass.onlineschoolapi.enrolment.rest;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.myclass.onlineschoolapi.enrolment.PdfGenerator.EnrolmentPDF;
import ro.myclass.onlineschoolapi.enrolment.dto.EnrolmentDTO;
import ro.myclass.onlineschoolapi.enrolment.dto.RemoveEnrolmentDTO;
import ro.myclass.onlineschoolapi.enrolment.model.Enrolment;
import ro.myclass.onlineschoolapi.enrolment.service.EnrolmentCommandService;
import ro.myclass.onlineschoolapi.enrolment.service.EnrolmentQuerryService;
import ro.myclass.onlineschoolapi.student.dto.CreateRestResponse;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/enrolment")
@Slf4j
@CrossOrigin
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
    public ResponseEntity<CreateRestResponse> deleteEnrolment(@RequestBody RemoveEnrolmentDTO removeEnrolmentDTO){
        enrolmentCommandService.deleteEnrolment(removeEnrolmentDTO);

        log.info("REST request to delete enrolment by removeEnrolmentDTO", removeEnrolmentDTO);

        return new ResponseEntity<>(new CreateRestResponse("Enrolment was deleted"), HttpStatus.OK);
    }

    @GetMapping("/exportPDF")
    public ResponseEntity<CreateRestResponse> exportPDF(HttpServletResponse response)throws Exception{

        response.setContentType("application/pdf");

        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
        String currentDate = dateFormat.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment;filename=enrolmentPdf_" +  currentDate +".pdf";

        response.setHeader(headerKey,headerValue);

        List<Enrolment> enrolmentList = this.enrolmentQuerryService.getAllEnrolments();

        EnrolmentPDF enrolmentPDF = new EnrolmentPDF(enrolmentList);

        return new ResponseEntity<>(new CreateRestResponse("PDF was succefully"),HttpStatus.OK);

    }
}
