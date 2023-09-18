package ro.myclass.onlineschoolapi.professor.service;

import org.springframework.stereotype.Service;
import ro.myclass.onlineschoolapi.professor.model.Professor;

import java.util.List;

@Service
public interface ProfessorQuerryService {

    List<Professor> getAllProfessors();

    Professor getProfessorById(long id);

    Professor getProfessorByFirstNameAndLastName(String firstName, String lastName);

    Professor getProfessorByFirstNameAndLastNameAndAge(String firstName, String lastName, int age);

    List<Professor> getProfessorsByFirstName(String firstName);

    List<Professor> getProfessorsByLastName(String lastName);

    List<Professor> getProfessorsByAge(int age);

    Professor getProfessorByEmail(String email);

    List<Professor> getProfessorsByAdress(String adress);

    List<Professor> getProfessorsBySubject(String subject);



}
