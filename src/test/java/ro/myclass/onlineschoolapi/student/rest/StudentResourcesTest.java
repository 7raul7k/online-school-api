package ro.myclass.onlineschoolapi.student.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ro.myclass.onlineschoolapi.exceptions.ListEmptyException;
import ro.myclass.onlineschoolapi.exceptions.StudentNotFoundException;
import ro.myclass.onlineschoolapi.student.model.Student;
import ro.myclass.onlineschoolapi.student.service.StudentCommandService;
import ro.myclass.onlineschoolapi.student.service.StudentQuerryService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class StudentResourcesTest {

    @Mock
    private StudentCommandService studentCommandService;

    @Mock
    private StudentQuerryService studentQuerryService;

    @InjectMocks
    private StudentResources studentResources;

    public ObjectMapper objectMapper = new ObjectMapper();

    private MockMvc restMockMvc;

    @BeforeEach
    public void initialConfig(){
        this.restMockMvc = MockMvcBuilders.standaloneSetup(studentResources).build();
    }

    @Test
    public void getAllStudents() throws Exception {
        Faker faker = new Faker();

        List<Student> studentList = new ArrayList<>();

        for(int i = 0 ; i <= 10 ;i++){

            studentList.add(Student.builder().id((long) i)
                    .firstName(faker.name().firstName())
                    .lastName(faker.name().lastName())
                    .email(faker.internet().emailAddress())
                    .build());
        }

        doReturn(studentList).when(studentQuerryService).getAllStudents();

        restMockMvc.perform(get("/api/v1/student/allStudents"))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(studentList)));
    }

    @Test
    public void getAllStudentsBadRequest() throws Exception {
        doThrow(ListEmptyException.class).when(studentQuerryService).getAllStudents();

        restMockMvc.perform(get("/api/v1/student/allStudents"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getStudentById() throws Exception {
        Faker faker = new Faker();

        Student student = Student.builder().id((long) 1)
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .email(faker.internet().emailAddress())
                .build();

        doReturn(student).when(studentQuerryService).getStudentById((long) 1);

        restMockMvc.perform(get("/api/v1/student/studentById").param("id", String.valueOf(1)))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(student)));
    }

    @Test
    public void getStudentByIdBadRequest() throws Exception {
        doThrow(StudentNotFoundException.class).when(studentQuerryService).getStudentById((long) 1);

        restMockMvc.perform(get("/api/v1/student/studentById").param("id", String.valueOf(1)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getStudentByEmail() throws Exception {
        Faker faker = new Faker();

        Student student = Student.builder().id((long) 1)
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .email(faker.internet().emailAddress())
                .build();

        doReturn(student).when(studentQuerryService).getStudentByEmail(student.getEmail());

        restMockMvc.perform(get("/api/v1/student/studentByEmail").param("email", student.getEmail()))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(student)));
    }

    @Test
    public void getStudentByEmailBadRequest() throws Exception {
        doThrow(StudentNotFoundException.class).when(studentQuerryService).getStudentByEmail("email");

        restMockMvc.perform(get("/api/v1/student/studentByEmail").param("email", "email"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getStudentByFirstName() throws Exception {
        Faker faker = new Faker();

        List<Student> studentList = new ArrayList<>();

        for(int i = 0 ; i <= 10 ;i++){

            studentList.add(Student.builder().id((long) i)
                    .firstName(faker.name().firstName())
                    .lastName(faker.name().lastName())
                    .email(faker.internet().emailAddress())
                    .build());
        }

        doReturn(studentList).when(studentQuerryService).getStudentsByFirstName(studentList.get(0).getFirstName());

        restMockMvc.perform(get("/api/v1/student/getStudentsByFirstName").param("firstName", studentList.get(0).getFirstName()))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(studentList)));
    }

    @Test
    public void getStudentByFirstNameBadRequest() throws Exception {
        doThrow(ListEmptyException.class).when(studentQuerryService).getStudentsByFirstName("firstName");

        restMockMvc.perform(get("/api/v1/student/getStudentsByFirstName").param("firstName", "firstName"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void addStudent() throws Exception {
        Faker faker = new Faker();

        Student student = Student.builder().id((long) 1)
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .email(faker.internet().emailAddress())
                .build();

        doNothing().when(studentCommandService).addStudent(any());

        restMockMvc.perform(post("/api/v1/student/addStudent")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(student)))
                .andExpect(status().isOk());
    }

    @Test
    public void addStudentBadRequest() throws Exception {
        Faker faker = new Faker();

        Student student = Student.builder().id((long) 1)
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .email(faker.internet().emailAddress())
                .build();

        doThrow(StudentNotFoundException.class).when(studentCommandService).addStudent(any());

        restMockMvc.perform(post("/api/v1/student/addStudent")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(student)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getStudentByLastName() throws Exception {
        Faker faker = new Faker();

        List<Student> studentList = new ArrayList<>();

        for(int i = 0 ; i <= 10 ;i++){

            studentList.add(Student.builder().id((long) i)
                    .firstName(faker.name().firstName())
                    .lastName(faker.name().lastName())
                    .email(faker.internet().emailAddress())
                    .build());
        }

        doReturn(studentList).when(studentQuerryService).getStudentsByLastName(studentList.get(0).getLastName());

        restMockMvc.perform(get("/api/v1/student/getStudentsByLastName").param("lastName", studentList.get(0).getLastName()))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(studentList)));
    }

    @Test
    public void getStudentByLastNameBadRequest() throws Exception {
        doThrow(ListEmptyException.class).when(studentQuerryService).getStudentsByLastName("lastName");

        restMockMvc.perform(get("/api/v1/student/getStudentsByLastName").param("lastName", "lastName"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void updateStudent() throws Exception {
        Faker faker = new Faker();

        Student student = Student.builder().id((long) 1)
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .email(faker.internet().emailAddress())
                .build();

        doNothing().when(studentCommandService).updateStudent(any());

        restMockMvc.perform(put("/api/v1/student/updateStudent")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(student)))
                .andExpect(status().isOk());
    }




}