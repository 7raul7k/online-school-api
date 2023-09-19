package ro.myclass.onlineschoolapi.professor.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ro.myclass.onlineschoolapi.exceptions.ProfessorNotFoundException;
import ro.myclass.onlineschoolapi.exceptions.ProfessorWasFoundException;
import ro.myclass.onlineschoolapi.professor.dto.ProfessorDTO;
import ro.myclass.onlineschoolapi.professor.model.Professor;
import ro.myclass.onlineschoolapi.professor.repo.ProfessorRepo;

import java.util.Optional;

@Service
@Transactional
public class ProfessorCommandImplService implements ProfessorCommandService {

    private ProfessorRepo professorRepo;

    public ProfessorCommandImplService(ProfessorRepo professorRepo) {
        this.professorRepo = professorRepo;
    }

    public void addProfessor(ProfessorDTO professorDTO) {

        Optional<Professor> professor = professorRepo.getProfessorByFirstNameAndLastName(professorDTO.getFirstName(), professorDTO.getLastName());
        if(professor.isEmpty()){
            Professor professor1 = Professor.builder().firstName(professorDTO.getFirstName()).lastName(professorDTO.getLastName()).age(professorDTO.getAge()).email(professorDTO.getEmail()).adress(professorDTO.getAdress()).subject(professorDTO.getSubject()).build();

            professorRepo.save(professor1);
        }else{
            throw new ProfessorWasFoundException();
        }

    }

    public void deleteProfessor(String firstName, String lastName) {

        Optional<Professor> professor = professorRepo.getProfessorByFirstNameAndLastName(firstName, lastName);

        if(professor.isEmpty()){
            throw new ProfessorNotFoundException();
        }else{
            professorRepo.delete(professor.get());
        }

    }

    public void updateProfessor(ProfessorDTO professorDTO){

        Optional<Professor> professor = professorRepo.getProfessorByEmail(professorDTO.getEmail());

        if(professor.isEmpty()) {
            throw new ProfessorNotFoundException();
        }else{

            if(professorDTO.getFirstName() != null){
                professor.get().setFirstName(professorDTO.getFirstName());
            } if(professorDTO.getLastName() !=null){
                professor.get().setLastName(professorDTO.getLastName());
            } if(professorDTO.getAge() != 0){
                professor.get().setAge(professorDTO.getAge());
            } if(professorDTO.getAdress() != null){
                professor.get().setAdress(professorDTO.getAdress());
            } if(professorDTO.getSubject() != null){
                professor.get().setSubject(professorDTO.getSubject());
            }

            this.professorRepo.saveAndFlush(professor.get());
        }
    }
}
