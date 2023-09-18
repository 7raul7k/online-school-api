package ro.myclass.onlineschoolapi.professor.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ro.myclass.onlineschoolapi.professor.dto.ProfessorDTO;

@Service
@Transactional
public interface ProfessorCommandService {

    void addProfessor(ProfessorDTO professorDTO);
    void deleteProfessor(String firstName, String lastName);
    void updateProfessor(ProfessorDTO professorDTO);

}
