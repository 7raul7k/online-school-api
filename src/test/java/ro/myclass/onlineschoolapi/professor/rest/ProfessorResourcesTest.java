package ro.myclass.onlineschoolapi.professor.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ro.myclass.onlineschoolapi.exceptions.ListEmptyException;
import ro.myclass.onlineschoolapi.professor.dto.ProfessorDTO;
import ro.myclass.onlineschoolapi.professor.model.Professor;
import ro.myclass.onlineschoolapi.professor.service.ProfessorCommandService;
import ro.myclass.onlineschoolapi.professor.service.ProfessorQuerryService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
class ProfessorResourcesTest {

    @Mock
    private ProfessorCommandService professorCommandService;

    @Mock
    private ProfessorQuerryService professorQuerryService;

    @InjectMocks
    private ProfessorResources professorResources;

    private MockMvc restMockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void initialConfig(){
        this.restMockMvc = MockMvcBuilders.standaloneSetup(professorResources).build();
    }

    @Test
    public void getAllProfessors() throws Exception {

        Faker faker = new Faker();

        List<Professor> professorList = new ArrayList<>();

        for(int i = 0 ; i <= 10 ;i++){
            Professor professor = Professor.builder().firstName(faker.name().firstName()).lastName(faker.name().lastName()).age(faker.number().numberBetween(20, 60)).email(faker.internet().emailAddress()).adress(faker.address().fullAddress()).build();
            professorList.add(professor);
        }

        doReturn(professorList).when(professorQuerryService).getAllProfessors();

        restMockMvc.perform(get("/api/v1/professor/allProfessors"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(professorList)));
    }

    @Test
    public void getAllProfessorsBadRequest() throws Exception{

        doThrow(ListEmptyException.class).when(professorQuerryService).getAllProfessors();

            restMockMvc.perform(get("/api/v1/professor/allProfessors"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getProfessorById() throws Exception {
        Faker faker = new Faker();

        Professor professor = Professor.builder().firstName(faker.name().firstName()).lastName(faker.name().lastName()).age(faker.number().numberBetween(20, 60)).email(faker.internet().emailAddress()).adress(faker.address().fullAddress()).build();

        doReturn(professor).when(professorQuerryService).getProfessorById(professor.getId());

        restMockMvc.perform(get("/api/v1/professor/professorById?id=" + professor.getId()))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(professor)));
    }

    @Test
    public void getProfessorByIdBadRequest() throws Exception {

        doThrow(ListEmptyException.class).when(professorQuerryService).getProfessorById((long) 1);

        restMockMvc.perform(get("/api/v1/professor/professorById?id=" + 1))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getProfessorByEmail() throws Exception {
        Faker faker = new Faker();

        Professor professor = Professor.builder().firstName(faker.name().firstName()).lastName(faker.name().lastName()).age(faker.number().numberBetween(20, 60)).email(faker.internet().emailAddress()).adress(faker.address().fullAddress()).build();

        doReturn(professor).when(professorQuerryService).getProfessorByEmail(professor.getEmail());

        restMockMvc.perform(get("/api/v1/professor/professorByEmail?email=" + professor.getEmail()))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(professor)));
    }

    @Test
    public void getProfessorByEmailBadRequest() throws Exception {

        doThrow(ListEmptyException.class).when(professorQuerryService).getProfessorByEmail("email");

        restMockMvc.perform(get("/api/v1/professor/professorByEmail?email=" + "email"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getProfessorsByFirstName() throws Exception {
        Faker faker = new Faker();

        List<Professor> professorList = new ArrayList<>();

        for(int i = 0 ; i <= 10 ;i++){
            Professor professor = Professor.builder().firstName(faker.name().firstName()).lastName(faker.name().lastName()).age(faker.number().numberBetween(20, 60)).email(faker.internet().emailAddress()).adress(faker.address().fullAddress()).build();
            professorList.add(professor);
        }

        doReturn(professorList).when(professorQuerryService).getProfessorsByFirstName(professorList.get(0).getFirstName());

        restMockMvc.perform(get("/api/v1/professor/getProfessorsByFirstName?firstName=" + professorList.get(0).getFirstName()))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(professorList)));
    }

    @Test
    public void getProfessorsByFirstNameBadRequest() throws Exception {

        doThrow(ListEmptyException.class).when(professorQuerryService).getProfessorsByFirstName("firstName");

        restMockMvc.perform(get("/api/v1/professor/getProfessorsByFirstName?firstName=" + "firstName"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getProfessorsByLastName() throws Exception {
        Faker faker = new Faker();

        List<Professor> professorList = new ArrayList<>();

        for(int i = 0 ; i <= 10 ;i++){
            Professor professor = Professor.builder().firstName(faker.name().firstName()).lastName(faker.name().lastName()).age(faker.number().numberBetween(20, 60)).email(faker.internet().emailAddress()).adress(faker.address().fullAddress()).build();
            professorList.add(professor);
        }

        doReturn(professorList).when(professorQuerryService).getProfessorsByLastName(professorList.get(0).getLastName());

        restMockMvc.perform(get("/api/v1/professor/getProfessorsByLastName?lastName=" + professorList.get(0).getLastName()))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(professorList)));
    }

    @Test
    public void getProfessorsByLastNameBadRequest() throws Exception {

        doThrow(ListEmptyException.class).when(professorQuerryService).getProfessorsByLastName("lastName");

        restMockMvc.perform(get("/api/v1/professor/getProfessorsByLastName?lastName=" + "lastName"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getProfessorsByAge() throws Exception {
        Faker faker = new Faker();

        List<Professor> professorList = new ArrayList<>();

        for(int i = 0 ; i <= 10 ;i++){
            Professor professor = Professor.builder().firstName(faker.name().firstName()).lastName(faker.name().lastName()).age(faker.number().numberBetween(20, 60)).email(faker.internet().emailAddress()).adress(faker.address().fullAddress()).build();
            professorList.add(professor);
        }

        doReturn(professorList).when(professorQuerryService).getProfessorsByAge(professorList.get(0).getAge());

        restMockMvc.perform(get("/api/v1/professor/getProfessorsByAge?age=" + professorList.get(0).getAge()))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(professorList)));
    }

    @Test
    public void getProfessorsByAgeBadRequest() throws Exception {

        doThrow(ListEmptyException.class).when(professorQuerryService).getProfessorsByAge(20);

        restMockMvc.perform(get("/api/v1/professor/getProfessorsByAge?age=" + 20))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getProfessorsByAdress() throws Exception {
        Faker faker = new Faker();

        List<Professor> professorList = new ArrayList<>();

        for(int i = 0 ; i <= 10 ;i++){
            Professor professor = Professor.builder().firstName(faker.name().firstName()).lastName(faker.name().lastName()).age(faker.number().numberBetween(20, 60)).email(faker.internet().emailAddress()).adress(faker.address().fullAddress()).build();
            professorList.add(professor);
        }

        doReturn(professorList).when(professorQuerryService).getProfessorsByAdress(professorList.get(0).getAdress());

        restMockMvc.perform(get("/api/v1/professor/getProfessorsByAdress?adress=" + professorList.get(0).getAdress()))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(professorList)));
    }

    @Test
    public void getProfessorsByAdressBadRequest() throws Exception {

        doThrow(ListEmptyException.class).when(professorQuerryService).getProfessorsByAdress("adress");

        restMockMvc.perform(get("/api/v1/professor/getProfessorsByAdress?adress=" + "adress"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getProfessorByFirstNameAndLastName() throws Exception {
        Faker faker = new Faker();

        Professor professor = Professor.builder().firstName(faker.name().firstName()).lastName(faker.name().lastName()).age(faker.number().numberBetween(20, 60)).email(faker.internet().emailAddress()).adress(faker.address().fullAddress()).build();

        doReturn(professor).when(professorQuerryService).getProfessorByFirstNameAndLastName(professor.getFirstName(), professor.getLastName());

        restMockMvc.perform(get("/api/v1/professor/getProfessorByFirstNameAndLastName?firstName=" + professor.getFirstName() + "&lastName=" + professor.getLastName()))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(professor)));
    }

    @Test
    public void getProfessorByFirstNameAndLastNameBadRequest() throws Exception {

        doThrow(ListEmptyException.class).when(professorQuerryService).getProfessorByFirstNameAndLastName("firstName", "lastName");

        restMockMvc.perform(get("/api/v1/professor/getProfessorByFirstNameAndLastName?firstName=" + "firstName" + "&lastName=" + "lastName"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getProfessorByFirstNameAndLastNameAndAge() throws Exception {
        Faker faker = new Faker();

        Professor professor = Professor.builder().firstName(faker.name().firstName()).lastName(faker.name().lastName()).age(faker.number().numberBetween(20, 60)).email(faker.internet().emailAddress()).adress(faker.address().fullAddress()).build();

        doReturn(professor).when(professorQuerryService).getProfessorByFirstNameAndLastNameAndAge(professor.getFirstName(), professor.getLastName(), professor.getAge());

        restMockMvc.perform(get("/api/v1/professor/getProfessorByFirstNameAndLastNameAndAge?firstName=" + professor.getFirstName() + "&lastName=" + professor.getLastName() + "&age=" + professor.getAge()))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(professor)));
    }

    @Test
    public void getProfessorByFirstNameAndLastNameAndAgeBadRequest() throws Exception {

        doThrow(ListEmptyException.class).when(professorQuerryService).getProfessorByFirstNameAndLastNameAndAge("firstName", "lastName", 20);

        restMockMvc.perform(get("/api/v1/professor/getProfessorByFirstNameAndLastNameAndAge").param("firstName", "firstName").param("lastName", "lastName").param("age", "20"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void updateProfessor() throws Exception {
        Faker faker = new Faker();

        ProfessorDTO professor = ProfessorDTO.builder().firstName(faker.name().firstName()).lastName(faker.name().lastName()).age(faker.number().numberBetween(20, 60)).email(faker.internet().emailAddress()).adress(faker.address().fullAddress()).build();


        doNothing().when(professorCommandService).updateProfessor(professor);

        restMockMvc.perform(put("/api/v1/professor/updateProfessor")
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(professor)))
                .andExpect(status().isOk());
    }

    @Test
    public void updateProfessorBadRequest() throws Exception {
        Faker faker = new Faker();

        ProfessorDTO professor = ProfessorDTO.builder().firstName(faker.name().firstName()).lastName(faker.name().lastName()).age(faker.number().numberBetween(20, 60)).email(faker.internet().emailAddress()).adress(faker.address().fullAddress()).build();

        doThrow(ListEmptyException.class).when(professorCommandService).updateProfessor(professor);

        restMockMvc.perform(put("/api/v1/professor/updateProfessor")
                        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(professor)))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void deleteProfessor() throws Exception {

        doNothing().when(professorCommandService).deleteProfessor("firstName", "lastName");


        restMockMvc.perform(delete("/api/v1/professor/deleteProfessor").param("firstName", "firstName").param("lastName", "lastName"))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteProfessorBadRequest() throws Exception {

        doThrow(ListEmptyException.class).when(professorCommandService).deleteProfessor("firstName", "lastName");

        restMockMvc.perform(delete("/api/v1/professor/deleteProfessor").param("firstName", "firstName").param("lastName", "lastName"))
                .andExpect(status().isBadRequest());
    }

}