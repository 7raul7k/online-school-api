package ro.myclass.onlineschoolapi.professor.service;


import org.springframework.stereotype.Service;
import ro.myclass.onlineschoolapi.exceptions.ListEmptyException;
import ro.myclass.onlineschoolapi.exceptions.ProfessorNotFoundException;
import ro.myclass.onlineschoolapi.professor.model.Professor;
import ro.myclass.onlineschoolapi.professor.repo.ProfessorRepo;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorQuerryImplService  implements ProfessorQuerryService {

    private ProfessorRepo professorRepo;

    public ProfessorQuerryImplService(ProfessorRepo professorRepo) {
        this.professorRepo = professorRepo;
    }

    public List<Professor> getAllProfessors() {
        List<Professor> professorList = professorRepo.getAllProfessor();

        if (professorList.isEmpty()) {
            throw new ListEmptyException();
        }

        return professorList;
    }

    public Professor getProfessorById(long id) {
        Optional<Professor> professor = professorRepo.getProfessorById(id);

        if (professor.isEmpty()) {
            throw new ProfessorNotFoundException();
        }

        return professor.get();
    }

    public Professor getProfessorByFirstNameAndLastName(String firstName, String lastName) {
        Optional<Professor> professor = professorRepo.getProfessorByFirstNameAndLastName(firstName, lastName);

        if (professor.isEmpty()) {
            throw new ProfessorNotFoundException();
        }

        return professor.get();
    }

    public Professor getProfessorByFirstNameAndLastNameAndAge(String firstName, String lastName, int age) {
        Optional<Professor> professor = professorRepo.getProfessorByFirstNameAndLastNameAndAge(firstName, lastName, age);

        if (professor.isEmpty()) {
            throw new ProfessorNotFoundException();
        }

        return professor.get();
    }

    public List<Professor> getProfessorsByFirstName(String firstName) {
        List<Professor> professorList = professorRepo.getProfessorByFirstName(firstName);

        if (professorList.isEmpty()) {
            throw new ListEmptyException();
        }

        return professorList;
    }

    public List<Professor> getProfessorsByLastName(String lastName) {
        List<Professor> professorList = professorRepo.getProfessorByLastName(lastName);

        if (professorList.isEmpty()) {
            throw new ListEmptyException();
        }

        return professorList;
    }

    public List<Professor> getProfessorsByAge(int age) {
        List<Professor> professorList = professorRepo.getProfessorByAge(age);

        if (professorList.isEmpty()) {
            throw new ListEmptyException();
        }

        return professorList;
    }

    public Professor getProfessorByEmail(String email) {
        Optional<Professor> professor = professorRepo.getProfessorByEmail(email);

        if (professor.isEmpty()) {
            throw new ProfessorNotFoundException();
        }

        return professor.get();
    }

    public List<Professor> getProfessorsByAdress(String adress) {
        List<Professor> professorList = professorRepo.getProfessorByAdress(adress);

        if (professorList.isEmpty()) {
            throw new ListEmptyException();
        }

        return professorList;
    }

    public List<Professor> getProfessorsBySubject(String subject) {
        List<Professor> professorList = professorRepo.getProfessorBySubject(subject);

        if (professorList.isEmpty()) {
            throw new ListEmptyException();
        }

        return professorList;
    }


}
