package ro.myclass.onlineschoolapi.studentIdCard.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ro.myclass.onlineschoolapi.exceptions.ListEmptyException;
import ro.myclass.onlineschoolapi.student.dto.StudentDTO;
import ro.myclass.onlineschoolapi.student.model.Student;
import ro.myclass.onlineschoolapi.studentIdCard.dto.StudentIdCardDTO;
import ro.myclass.onlineschoolapi.studentIdCard.model.StudentIdCard;
import ro.myclass.onlineschoolapi.studentIdCard.service.StudentIdCardCommandService;
import ro.myclass.onlineschoolapi.studentIdCard.service.StudentIdCardQuerryService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class StudentIdCardResourceTest {

    @Mock
    private StudentIdCardCommandService studentIdCardCommandService;

    @Mock
    private StudentIdCardQuerryService studentIdCardQuerryService;

    @InjectMocks
    private StudentIdCardResource studentIdCardResource;

    private MockMvc restMockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void initialConfig(){
        this.restMockMvc = MockMvcBuilders.standaloneSetup(studentIdCardResource).build();
    }

    @Test
    public void getAllStudentIdCards() throws Exception{

        Faker faker = new Faker();

        List<StudentIdCard> studentIdCardList = new ArrayList<>();

        for(int i = 0 ; i < 10 ;i++){

            Student student = Student.builder()
                    .id(faker.number().randomNumber())
                    .firstName(faker.name().firstName())
                    .lastName(faker.name().lastName())
                    .age(faker.number().numberBetween(7, 18))
                    .email(faker.internet().emailAddress())
                    .adress(faker.address().fullAddress())
                    .build();

            studentIdCardList.add(StudentIdCard.builder()
                    .id(faker.number().randomNumber())
                    .student(student)
                    .build());
        }

        doReturn(studentIdCardList).when(studentIdCardQuerryService).getAllStudentIdCards();

        restMockMvc.perform(get("/api/v1/studentIdCard/allStudentIdCards"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(studentIdCardList)));
    }

    @Test
    public void getAllStudentIdCardsBadRequest() throws Exception{
        doThrow(ListEmptyException.class).when(studentIdCardQuerryService).getAllStudentIdCards();

        restMockMvc.perform(get("/api/v1/studentIdCard/allStudentIdCards"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getStudentIdCardById() throws Exception{

        Faker faker = new Faker();

        Student student = Student.builder()
                .id(faker.number().randomNumber())
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .age(faker.number().numberBetween(7, 18))
                .email(faker.internet().emailAddress())
                .adress(faker.address().fullAddress())
                .build();

        StudentIdCard studentIdCard = StudentIdCard.builder()
                .id(faker.number().randomNumber())
                .student(student)
                .build();

        doReturn(studentIdCard).when(studentIdCardQuerryService).getStudentIdCardById(studentIdCard.getId());

        restMockMvc.perform(get("/api/v1/studentIdCard/studentIdCardById?id=" + studentIdCard.getId()))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(studentIdCard)));
    }

    @Test
    public void getStudentIdCardByIdBadRequest() throws Exception{
        doThrow(ListEmptyException.class).when(studentIdCardQuerryService).getStudentIdCardById(1);

        restMockMvc.perform(get("/api/v1/studentIdCard/studentIdCardById?id=1"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void addStudentIdCard() throws Exception{

        Faker faker = new Faker();

        StudentDTO student = StudentDTO.builder()

                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .age(faker.number().numberBetween(7, 18))
                .email(faker.internet().emailAddress())
                .adress(faker.address().fullAddress())
                .build();

        StudentIdCardDTO studentIdCard = StudentIdCardDTO.builder()
                .student(student)
                .build();

        doNothing().when(studentIdCardCommandService).addStudentIdCard(studentIdCard);

        restMockMvc.perform(post("/api/v1/studentIdCard/addStudentIdCard")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(studentIdCard)))
                .andExpect(status().isOk());

    }

    @Test
    public void updateStudentIdCard() throws Exception{

        Faker faker = new Faker();

        StudentDTO student = StudentDTO.builder()

                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .age(faker.number().numberBetween(7, 18))
                .email(faker.internet().emailAddress())
                .adress(faker.address().fullAddress())
                .build();

        StudentIdCardDTO studentIdCard = StudentIdCardDTO.builder()
                .student(student)
                .build();

        doNothing().when(studentIdCardCommandService).updateStudentIdCard(studentIdCard);

        restMockMvc.perform(put("/api/v1/studentIdCard/updateStudentIdCard")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(studentIdCard)))
                .andExpect(status().isOk());

    }

    @Test
    public void updateStudentIdCardBadRequest() throws Exception{

        Faker faker = new Faker();

        StudentDTO student = StudentDTO.builder()

                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .age(faker.number().numberBetween(7, 18))
                .email(faker.internet().emailAddress())
                .adress(faker.address().fullAddress())
                .build();

        StudentIdCardDTO studentIdCard = StudentIdCardDTO.builder()
                .student(student)
                .build();

        doThrow(ListEmptyException.class).when(studentIdCardCommandService).updateStudentIdCard(studentIdCard);

        restMockMvc.perform(put("/api/v1/studentIdCard/updateStudentIdCard")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(studentIdCard)))
                .andExpect(status().isBadRequest());

    }

}