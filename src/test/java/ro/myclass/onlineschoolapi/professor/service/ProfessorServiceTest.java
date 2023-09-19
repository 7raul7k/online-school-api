package ro.myclass.onlineschoolapi.professor.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ro.myclass.onlineschoolapi.exceptions.ListEmptyException;
import ro.myclass.onlineschoolapi.exceptions.ProfessorNotFoundException;
import ro.myclass.onlineschoolapi.exceptions.ProfessorWasFoundException;
import ro.myclass.onlineschoolapi.professor.dto.ProfessorDTO;
import ro.myclass.onlineschoolapi.professor.model.Professor;
import ro.myclass.onlineschoolapi.professor.repo.ProfessorRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProfessorServiceTest {

    @Mock
    private ProfessorRepo professorRepo;

    @InjectMocks
    private ProfessorQuerryService professorQuerryService = new ProfessorQuerryImplService(professorRepo);

    @InjectMocks
    private ProfessorCommandService professorCommandService = new ProfessorCommandImplService(professorRepo);

    @Captor
    private ArgumentCaptor<Professor> argumentCaptor;

    @Test
    public void getAllProfessors() {

        //generate 4 professors with all the fields and different values for each field and add them to a list
        Professor professor = Professor.builder().firstName("Andrei").lastName("Popescu").age(30).email("").adress("Bucuresti").subject("Matematica").build();
        Professor professor1 = Professor.builder().firstName("Mihai").lastName("Ionescu").age(30).email("").adress("Bucuresti").subject("Romana").build();
        Professor professor2 = Professor.builder().firstName("Andrei").lastName("Georgescu").age(30).email("").adress("Bucuresti").subject("Engleza").build();
        Professor professor3 = Professor.builder().firstName("Razvan").lastName("Andrei").age(30).email("").adress("Bucuresti").subject("Istorie").build();

        List<Professor> professorList = new ArrayList<>();
        professorList.add(professor);
        professorList.add(professor1);
        professorList.add(professor2);
        professorList.add(professor3);

        doReturn(professorList).when(professorRepo).getAllProfessor();

        assertEquals(professorList, professorQuerryService.getAllProfessors());

    }

    @Test
    public void getAllProfessorsException() {
        doReturn(new ArrayList<>()).when(professorRepo).getAllProfessor();

        assertThrows(ListEmptyException.class, () -> professorQuerryService.getAllProfessors());
    }

    @Test
    public void getProfessorById() {
        Professor professor = Professor.builder().firstName("Andrei").lastName("Popescu").age(30).email("").adress("Bucuresti").subject("Matematica").build();

        doReturn(java.util.Optional.of(professor)).when(professorRepo).getProfessorById(1);

        assertEquals(professor, professorQuerryService.getProfessorById(1));
    }

    @Test
    public void getProfessorByIdException() {
        doReturn(java.util.Optional.empty()).when(professorRepo).getProfessorById(1);

        assertThrows(ProfessorNotFoundException.class, () -> professorQuerryService.getProfessorById(1));
    }

    @Test
    public void getProfessorByFirstNameAndLastName() {
        Professor professor = Professor.builder().firstName("Andrei").lastName("Popescu").age(30).email("").adress("Bucuresti").subject("Matematica").build();

        doReturn(java.util.Optional.of(professor)).when(professorRepo).getProfessorByFirstNameAndLastName("Andrei", "Popescu");

        assertEquals(professor, professorQuerryService.getProfessorByFirstNameAndLastName("Andrei", "Popescu"));
    }

    @Test
    public void getProfessorByFirstNameAndLastNameException() {
        doReturn(java.util.Optional.empty()).when(professorRepo).getProfessorByFirstNameAndLastName("Andrei", "Popescu");

        assertThrows(ProfessorNotFoundException.class, () -> professorQuerryService.getProfessorByFirstNameAndLastName("Andrei", "Popescu"));
    }

    @Test
    public void getProfessorByFirstName() {
        Professor professor = Professor.builder().firstName("Andrei").lastName("Popescu").age(30).email("").adress("Bucuresti").subject("Matematica").build();
        Professor professor1 = Professor.builder().firstName("Andrei").lastName("Georgescu").age(30).email("").adress("Bucuresti").subject("Engleza").build();

        List<Professor> professorList = new ArrayList<>();
        professorList.add(professor);
        professorList.add(professor1);

        doReturn(professorList).when(professorRepo).getProfessorByFirstName("Andrei");

        assertEquals(professorList, professorQuerryService.getProfessorsByFirstName("Andrei"));
    }

    @Test
    public void getProfessorByFirstNameException() {
        doReturn(new ArrayList<>()).when(professorRepo).getProfessorByFirstName("Andrei");

        assertThrows(ListEmptyException.class, () -> professorQuerryService.getProfessorsByFirstName("Andrei"));
    }

    @Test
    public void getProfessorByLastName() {
        Professor professor = Professor.builder().firstName("Andrei").lastName("Popescu").age(30).email("").adress("Bucuresti").subject("Matematica").build();
        Professor professor1 = Professor.builder().firstName("Mihai").lastName("Popescu").age(30).email("").adress("Bucuresti").subject("Engleza").build();

        List<Professor> professorList = new ArrayList<>();
        professorList.add(professor);
        professorList.add(professor1);

        doReturn(professorList).when(professorRepo).getProfessorByLastName("Popescu");

        assertEquals(professorList, professorQuerryService.getProfessorsByLastName("Popescu"));
    }

    @Test
    public void getProfessorByLastNameException() {
        doReturn(new ArrayList<>()).when(professorRepo).getProfessorByLastName("Popescu");

        assertThrows(ListEmptyException.class, () -> professorQuerryService.getProfessorsByLastName("Popescu"));
    }

    @Test
    public void getProfessorByAge() {
        Professor professor = Professor.builder().firstName("Andrei").lastName("Popescu").age(30).email("").adress("Bucuresti").subject("Matematica").build();
        Professor professor1 = Professor.builder().firstName("Mihai").lastName("Popescu").age(30).email("").adress("Bucuresti").subject("Engleza").build();

        List<Professor> professorList = new ArrayList<>();
        professorList.add(professor);
        professorList.add(professor1);

        doReturn(professorList).when(professorRepo).getProfessorByAge(30);

        assertEquals(professorList, professorQuerryService.getProfessorsByAge(30));
    }

    @Test
    public void getProfessorByAgeException() {
        doReturn(new ArrayList<>()).when(professorRepo).getProfessorByAge(30);

        assertThrows(ListEmptyException.class, () -> professorQuerryService.getProfessorsByAge(30));
    }

    @Test
    public void getProfessorByAdress() {
        Professor professor = Professor.builder().firstName("Andrei").lastName("Popescu").age(30).email("").adress("Bucuresti").subject("Matematica").build();
        Professor professor1 = Professor.builder().firstName("Mihai").lastName("Popescu").age(30).email("").adress("Bucuresti").subject("Engleza").build();

        List<Professor> professorList = new ArrayList<>();
        professorList.add(professor);
        professorList.add(professor1);

        doReturn(professorList).when(professorRepo).getProfessorByAdress("Bucuresti");

        assertEquals(professorList, professorQuerryService.getProfessorsByAdress("Bucuresti"));
    }

    @Test
    public void getProfessorByAdressException() {
        doReturn(new ArrayList<>()).when(professorRepo).getProfessorByAdress("Bucuresti");

        assertThrows(ListEmptyException.class, () -> professorQuerryService.getProfessorsByAdress("Bucuresti"));
    }

    @Test
    public void getProfessorByEmail() {
        Professor professor = Professor.builder().firstName("Andrei").lastName("Popescu").age(30).email("popescuandrei@gmail.com").adress("Bucuresti").subject("Matematica").build();

        doReturn(java.util.Optional.of(professor)).when(professorRepo).getProfessorByEmail("popescuandrei@gmail.com");

        assertEquals(professor, professorQuerryService.getProfessorByEmail("popescuandrei@gmail.com"));

    }

    @Test
    public void getProfessorByEmailException() {
        doReturn(Optional.empty()).when(professorRepo).getProfessorByEmail("");

        assertThrows(ProfessorNotFoundException.class, () -> professorQuerryService.getProfessorByEmail(""));
    }

    @Test
    public void addProfessor() {
        Professor professor = Professor.builder().firstName("Andrei").lastName("Popescu").age(30).email("").adress("Bucuresti").subject("Matematica").build();

        ProfessorDTO professorDTO = ProfessorDTO.builder().firstName("Andrei").lastName("Popescu").age(30).email("").adress("Bucuresti").subject("Matematica").build();
        doReturn(Optional.empty()).when(professorRepo).getProfessorByFirstNameAndLastName(professorDTO.getFirstName(), professorDTO.getLastName());

        professorCommandService.addProfessor(professorDTO);

        verify(professorRepo, times(1)).save(argumentCaptor.capture());

        assertEquals(professor, argumentCaptor.getValue());

    }

    @Test
    public void addProfessorException() {
        ProfessorDTO professorDTO = ProfessorDTO.builder().firstName("Andrei").lastName("Popescu").age(30).email("").adress("Bucuresti").subject("Matematica").build();
        Professor professor = Professor.builder().firstName("Andrei").lastName("Popescu").age(30).email("").adress("Bucuresti").subject("Matematica").build();

        doReturn(Optional.of(professor)).when(professorRepo).getProfessorByFirstNameAndLastName(professorDTO.getFirstName(), professorDTO.getLastName());

        assertThrows(ProfessorWasFoundException.class, () -> professorCommandService.addProfessor(professorDTO));
    }

    @Test
    public void deleteProfessor() {
        Professor professor = Professor.builder().firstName("Andrei").lastName("Popescu").age(30).email("").adress("Bucuresti").subject("Matematica").build();

        doReturn(Optional.of(professor)).when(professorRepo).getProfessorByFirstNameAndLastName(professor.getFirstName(), professor.getLastName());

        professorCommandService.deleteProfessor(professor.getFirstName(), professor.getLastName());

        verify(professorRepo, times(1)).delete(argumentCaptor.capture());

        assertEquals(professor, argumentCaptor.getValue());

    }

    @Test
    public void deleteProfessorException() {
        Professor professor = Professor.builder().firstName("Andrei").lastName("Popescu").age(30).email("").adress("Bucuresti").subject("Matematica").build();

        doReturn(Optional.empty()).when(professorRepo).getProfessorByFirstNameAndLastName(professor.getFirstName(), professor.getLastName());

        assertThrows(ProfessorNotFoundException.class, () -> professorCommandService.deleteProfessor(professor.getFirstName(), professor.getLastName()));
    }

    @Test
    public void updateProfessor() {
        Professor professor = Professor.builder().firstName("Andrei").lastName("Popescu").age(30).email("").adress("Bucuresti").subject("").build();

        ProfessorDTO professorDTO = ProfessorDTO.builder().firstName("Andrei").lastName("Popescu").age(30).email("").adress("Bucuresti").subject("Matematica").build();

        doReturn(Optional.of(professor)).when(professorRepo).getProfessorByEmail(professor.getEmail());

        professorCommandService.updateProfessor(professorDTO);

        verify(professorRepo, times(1)).saveAndFlush(argumentCaptor.capture());

        assertEquals(professor, argumentCaptor.getValue());
    }

    @Test
    public void updateProfessorException() {
        Professor professor = Professor.builder().firstName("Andrei").lastName("Popescu").age(30).email("").adress("Bucuresti").subject("").build();

        ProfessorDTO professorDTO = ProfessorDTO.builder().firstName("Andrei").lastName("Popescu").age(30).email("").adress("Bucuresti").subject("Matematica").build();

        doReturn(Optional.empty()).when(professorRepo).getProfessorByEmail(professor.getEmail());

        assertThrows(ProfessorNotFoundException.class, () -> professorCommandService.updateProfessor(professorDTO));
    }

    @Test
    public void getProfessorsBySubject(){
        Professor professor = Professor.builder().firstName("Andrei").lastName("Popescu").age(30).email("").adress("Bucuresti").subject("Matematica").build();
        Professor professor1 = Professor.builder().firstName("Mihai").lastName("Popescu").age(30).email("").adress("Bucuresti").subject("Matematica").build();

        List<Professor> professorList = new ArrayList<>();
        professorList.add(professor);
        professorList.add(professor1);

        doReturn(professorList).when(professorRepo).getProfessorBySubject("Matematica");

        assertEquals(professorList, professorQuerryService.getProfessorsBySubject("Matematica"));
    }

    @Test
    public void getProfessorsBySubjectException(){
        doReturn(new ArrayList<>()).when(professorRepo).getProfessorBySubject("Matematica");

        assertThrows(ListEmptyException.class, () -> professorQuerryService.getProfessorsBySubject("Matematica"));
    }
}