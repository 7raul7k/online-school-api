package ro.myclass.onlineschoolapi.professor.rest;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.myclass.onlineschoolapi.professor.dto.ProfessorDTO;
import ro.myclass.onlineschoolapi.professor.model.Professor;
import ro.myclass.onlineschoolapi.professor.service.ProfessorCommandService;
import ro.myclass.onlineschoolapi.professor.service.ProfessorQuerryService;
import ro.myclass.onlineschoolapi.student.CreateRestResponse;

import java.util.List;

@RestController
@RequestMapping("/api/v1/professor")
@Slf4j
public class ProfessorResources {

    private ProfessorCommandService professorCommandService;

    private ProfessorQuerryService professorQuerryService;

    public ProfessorResources(ProfessorCommandService professorCommandService, ProfessorQuerryService professorQuerryService) {
        this.professorCommandService = professorCommandService;
        this.professorQuerryService = professorQuerryService;
    }

    @GetMapping("/allProfessors")
    public ResponseEntity<List<Professor>> getAllProfessors(){
        List<Professor> professorList = professorQuerryService.getAllProfessors();

        log.info("REST request to get all professors", professorList);

        return new ResponseEntity<>(professorList, HttpStatus.OK);

    }

    @GetMapping("/professorById")
    public ResponseEntity<Professor> getProfessorById(@RequestParam long id){
        Professor professor = professorQuerryService.getProfessorById(id);

        log.info("REST request to get professor by id", professor);

        return new ResponseEntity<>(professor, HttpStatus.OK);
    }

    @GetMapping("/professorByEmail")
    public ResponseEntity<Professor> getProfessorByEmail(@RequestParam String email){
        Professor professor = professorQuerryService.getProfessorByEmail(email);

        log.info("REST request to get professor by email", professor);

        return new ResponseEntity<>(professor, HttpStatus.OK);
    }

    @GetMapping("/getProfessorsByFirstName")
    public ResponseEntity<List<Professor>> getProfessorsByFirstName(@RequestParam String firstName){
        List<Professor> professorList = professorQuerryService.getProfessorsByFirstName(firstName);

        log.info("REST request to get professors by first name", professorList);

        return new ResponseEntity<>(professorList, HttpStatus.OK);
    }

    @GetMapping("/getProfessorsByLastName")
    public ResponseEntity<List<Professor>> getProfessorsByLastName(@RequestParam String lastName){
        List<Professor> professorList = professorQuerryService.getProfessorsByLastName(lastName);

        log.info("REST request to get professors by last name", professorList);

        return new ResponseEntity<>(professorList, HttpStatus.OK);
    }

    @GetMapping("/getProfessorsByAge")
    public ResponseEntity<List<Professor>> getProfessorsByAge(@RequestParam int age){
        List<Professor> professorList = professorQuerryService.getProfessorsByAge(age);

        log.info("REST request to get professors by age", professorList);

        return new ResponseEntity<>(professorList, HttpStatus.OK);
    }

    @GetMapping("/getProfessorsByAdress")
    public ResponseEntity<List<Professor>> getProfessorsByAdress(@RequestParam String adress){
        List<Professor> professorList = professorQuerryService.getProfessorsByAdress(adress);

        log.info("REST request to get professors by adress", professorList);

        return new ResponseEntity<>(professorList, HttpStatus.OK);
    }

    @GetMapping("/getProfessorByFirstNameAndLastName")
    public ResponseEntity<Professor> getProfessorByFirstNameAndLastName(@RequestParam String firstName, @RequestParam String lastName){
        Professor professor = professorQuerryService.getProfessorByFirstNameAndLastName(firstName, lastName);

        log.info("REST request to get professor by first name and last name", professor);

        return new ResponseEntity<>(professor, HttpStatus.OK);
    }

    @GetMapping("/getProfessorByFirstNameAndLastNameAndAge")
    public ResponseEntity<Professor> getProfessorByFirstNameAndLastNameAndAge(@RequestParam String firstName, @RequestParam String lastName, @RequestParam int age){
        Professor professor = professorQuerryService.getProfessorByFirstNameAndLastNameAndAge(firstName, lastName, age);

        log.info("REST request to get professor by first name and last name and age", professor);

        return new ResponseEntity<>(professor, HttpStatus.OK);
    }

    @PostMapping("/addProfessor")
    public ResponseEntity<CreateRestResponse> addProfessor(@RequestBody ProfessorDTO professor){
        professorCommandService.addProfessor(professor);

        log.info("REST request to add professor", professor);

        return new ResponseEntity<>(new CreateRestResponse("Professor was added"), HttpStatus.OK);
    }

    @PutMapping("/updateProfessor")
    public ResponseEntity<CreateRestResponse> updateProfessor(@RequestBody ProfessorDTO professor){
        professorCommandService.updateProfessor(professor);

        log.info("REST request to update professor", professor);

        return new ResponseEntity<>(new CreateRestResponse("Professor was updated"), HttpStatus.OK);
    }

    @DeleteMapping("/deleteProfessor")
    public ResponseEntity<CreateRestResponse> deleteProfessor(@RequestParam String firstName, @RequestParam String lastName){
        professorCommandService.deleteProfessor(firstName, lastName);

        log.info("REST request to delete professor", firstName, lastName);

        return new ResponseEntity<>(new CreateRestResponse("Professor was deleted"), HttpStatus.OK);
    }
}
