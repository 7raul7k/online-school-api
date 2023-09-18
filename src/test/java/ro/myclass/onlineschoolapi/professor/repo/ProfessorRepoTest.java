package ro.myclass.onlineschoolapi.professor.repo;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.internal.util.KotlinInlineClassUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ro.myclass.onlineschoolapi.OnlineSchoolApiApplication;
import ro.myclass.onlineschoolapi.professor.model.Professor;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = OnlineSchoolApiApplication.class)
@Transactional
class ProfessorRepoTest {

    @Autowired
    public ProfessorRepo professorRepo;

    @BeforeEach
    public void clean() {
        professorRepo.deleteAll();
    }

    @Test
    public void getAllProfessor() {

        Professor professor = Professor.builder().firstName("Popescu Andrei").lastName("Popescu").age(12).email("popescuandrei@gmail.com").adress("Bucuresti").subject("Matematica").build();
        Professor professor1 = Professor.builder().firstName("Ionescu Razvan").lastName("Ionescu").age(13).email("ionescurazvan@gmail.com").adress("Bucuresti").subject("Romana").build();
        Professor professor2 = Professor.builder().firstName("Popa Cristian").lastName("Popa").age(14).email("popacristian@gmail.com").adress("Bucuresti").subject("Istorie").build();
        Professor professor3 = Professor.builder().firstName("Stanciu Ionut").lastName("Stanciu").age(15).email("stanciuionut@gmail.com").adress("Bucuresti").subject("Geografie").build();

        professorRepo.save(professor);
        professorRepo.save(professor1);
        professorRepo.save(professor2);
        professorRepo.save(professor3);

        List<Professor> professorList = new ArrayList<>();
        professorList.add(professor);
        professorList.add(professor1);
        professorList.add(professor2);
        professorList.add(professor3);

        assertEquals(professorList, professorRepo.getAllProfessor());
    }

    @Test
    public void getProfessorByEmail() {
        Professor professor = Professor.builder().firstName("Popescu Andrei").lastName("Popescu").age(12).email("popescuandrei@gmail.com").adress("Bucuresti").subject("Matematica").build();

        professorRepo.save(professor);

        assertEquals(professor, professorRepo.getProfessorByEmail("popescuandrei@gmail.com").get());
    }

    @Test
    public void getProfessorByFirstName() {
        Professor professor = Professor.builder().firstName("Popescu").lastName("Popescu").age(12).email("popescuandrei@gmail.com").adress("Bucuresti").subject("Matematica").build();
        Professor professor1 = Professor.builder().firstName("Popescu").lastName("George").age(12).email("popescugeorge@gmail.com").adress("Bucuresti").subject("Matematica").build();
        Professor professor2 = Professor.builder().firstName("Popescu").lastName("Alex").age(12).email("popescualex@gmail.com").adress("Bucuresti").subject("Matematica").build();
        Professor professor3 = Professor.builder().firstName("Popescu").lastName("Ion").age(12).email("popescuion@gmail.com").adress("Bucuresti").subject("Matematica").build();

        professorRepo.save(professor);
        professorRepo.save(professor1);
        professorRepo.save(professor2);
        professorRepo.save(professor3);

        List<Professor> professorList = new ArrayList<>();
        professorList.add(professor);
        professorList.add(professor1);
        professorList.add(professor2);
        professorList.add(professor3);

        assertEquals(professorList, professorRepo.getProfessorByFirstName("Popescu"));

    }

    @Test
    public void getProfessorByLastName() {

        Professor professor = Professor.builder().firstName("Popescu").lastName("Andrei").age(12).email("popescuandrei@gmail.com").adress("Bucuresti").subject("Matematica").build();
        Professor professor1 = Professor.builder().firstName("Ionescu").lastName("Andrei").age(12).email("ionescuandrei@gmail.com").adress("Bucuresti").subject("Matematica").build();
        Professor professor2 = Professor.builder().firstName("Popa").lastName("Andrei").age(12).email("popaandrei@gmail.com").adress("Bucuresti").subject("Matematica").build();
        Professor professor3 = Professor.builder().firstName("Stanciu").lastName("Andrei").age(12).email("stanciuandrei@gmail.com").adress("Bucuresti").subject("Matematica").build();

        professorRepo.save(professor);
        professorRepo.save(professor1);
        professorRepo.save(professor2);
        professorRepo.save(professor3);

        List<Professor> professorList = new ArrayList<>();
        professorList.add(professor);
        professorList.add(professor1);
        professorList.add(professor2);
        professorList.add(professor3);

        assertEquals(professorList, professorRepo.getProfessorByLastName("Andrei"));
    }

    @Test
    public void getProfessorByAge() {

        Professor professor = Professor.builder().firstName("Popescu").lastName("Andrei").age(12).email("").adress("Bucuresti").subject("Matematica").build();
        Professor professor1 = Professor.builder().firstName("Ionescu").lastName("Andrei").age(12).email("").adress("Bucuresti").subject("Matematica").build();
        Professor professor2 = Professor.builder().firstName("Popa").lastName("Andrei").age(12).email("").adress("Bucuresti").subject("Matematica").build();
        Professor professor3 = Professor.builder().firstName("Stanciu").lastName("Andrei").age(12).email("").adress("Bucuresti").subject("Matematica").build();

        professorRepo.save(professor);
        professorRepo.save(professor1);
        professorRepo.save(professor2);
        professorRepo.save(professor3);

        List<Professor> professorList = new ArrayList<>();
        professorList.add(professor);
        professorList.add(professor1);
        professorList.add(professor2);
        professorList.add(professor3);

        assertEquals(professorList, professorRepo.getProfessorByAge(12));
    }

    @Test
    public void getProfessorByAdress() {

        Professor professor = Professor.builder().firstName("Popescu").lastName("Andrei").age(12).email("").adress("Bucuresti").subject("Matematica").build();
        Professor professor1 = Professor.builder().firstName("Ionescu").lastName("Andrei").age(12).email("").adress("Bucuresti").subject("Matematica").build();
        Professor professor2 = Professor.builder().firstName("Popa").lastName("Andrei").age(12).email("").adress("Bucuresti").subject("Matematica").build();
        Professor professor3 = Professor.builder().firstName("Stanciu").lastName("Andrei").age(12).email("").adress("Bucuresti").subject("Matematica").build();

        professorRepo.save(professor);
        professorRepo.save(professor1);
        professorRepo.save(professor2);
        professorRepo.save(professor3);

        List<Professor> professorList = new ArrayList<>();
        professorList.add(professor);
        professorList.add(professor1);
        professorList.add(professor2);
        professorList.add(professor3);

        assertEquals(professorList, professorRepo.getProfessorByAdress("Bucuresti"));
    }

    @Test
    public void getProfessorBySubject() {

        Professor professor = Professor.builder().firstName("Popescu").lastName("Andrei").age(12).email("").adress("Bucuresti").subject("Matematica").build();
        Professor professor1 = Professor.builder().firstName("Ionescu").lastName("Andrei").age(12).email("").adress("Bucuresti").subject("Matematica").build();
        Professor professor2 = Professor.builder().firstName("Popa").lastName("Andrei").age(12).email("").adress("Bucuresti").subject("Matematica").build();
        Professor professor3 = Professor.builder().firstName("Stanciu").lastName("Andrei").age(12).email("").adress("Bucuresti").subject("Matematica").build();

        professorRepo.save(professor);
        professorRepo.save(professor1);
        professorRepo.save(professor2);
        professorRepo.save(professor3);

        List<Professor> professorList = new ArrayList<>();
        professorList.add(professor);
        professorList.add(professor1);
        professorList.add(professor2);
        professorList.add(professor3);

        assertEquals(professorList, professorRepo.getProfessorBySubject("Matematica"));
    }

    @Test
    public void getProfessorByFirstNameAndLastName() {

        Professor professor = Professor.builder().firstName("Popescu").lastName("Andrei").age(12).email("").adress("Bucuresti").subject("Matematica").build();

        professorRepo.save(professor);


        assertEquals(professor, professorRepo.getProfessorByFirstNameAndLastName("Popescu", "Andrei").get());
    }

    @Test
    public void getProfessorByFirstNameAndLastNameAndAge() {

        Professor professor = Professor.builder().firstName("Popescu").lastName("Andrei").age(12).email("").adress("Bucuresti").subject("Matematica").build();

        professorRepo.save(professor);


        assertEquals(professor, professorRepo.getProfessorByFirstNameAndLastNameAndAge("Popescu", "Andrei", 12).get());
    }

    @Test
    public void getProfessorById() {

        Professor professor = Professor.builder().firstName("Popescu").lastName("Andrei").age(12).email("").adress("Bucuresti").subject("Matematica").build();

        professorRepo.save(professor);

        assertEquals(professor, professorRepo.getProfessorById(1L).get());
    }

}

