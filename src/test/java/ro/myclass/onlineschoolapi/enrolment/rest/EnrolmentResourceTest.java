package ro.myclass.onlineschoolapi.enrolment.rest;

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
import ro.myclass.onlineschoolapi.course.dto.CourseDTO;
import ro.myclass.onlineschoolapi.course.model.Course;
import ro.myclass.onlineschoolapi.enrolment.dto.EnrolmentDTO;
import ro.myclass.onlineschoolapi.enrolment.dto.RemoveEnrolmentDTO;
import ro.myclass.onlineschoolapi.enrolment.model.Enrolment;
import ro.myclass.onlineschoolapi.enrolment.service.EnrolmentCommandService;
import ro.myclass.onlineschoolapi.enrolment.service.EnrolmentQuerryService;
import ro.myclass.onlineschoolapi.exceptions.ListEmptyException;
import ro.myclass.onlineschoolapi.student.dto.StudentDTO;
import ro.myclass.onlineschoolapi.student.model.Student;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
class EnrolmentResourceTest {

    @Mock
    private EnrolmentCommandService enrolmentCommandService;

    @Mock
    private EnrolmentQuerryService enrolmentQuerryService;

    @InjectMocks
    private EnrolmentResource enrolmentResource;

    private MockMvc restMockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void initialConfig(){
        this.restMockMvc = MockMvcBuilders.standaloneSetup(enrolmentResource).build();
    }

    @Test
    public void getAllEnrolments() throws Exception {

        Faker faker = new Faker();

        List<Enrolment> enrolmentList = new ArrayList<>();


        Student student = Student.builder().id(1L).firstName("Popescu").lastName("Andrei").age(12).email("").adress("Bucuresti").build();

        for(int i = 0 ; i <= 10 ;i++){

            Course course = Course.builder().id((long) i).name(faker.educator().course()).build();

            Enrolment enrolment = Enrolment.builder().id((long) i).student(student).course(course).build();

            enrolmentList.add(enrolment);

        }

        doReturn(enrolmentList).when(enrolmentQuerryService).getAllEnrolments();

        restMockMvc.perform(get("/api/v1/enrolment/allEnrolments"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(enrolmentList)));
    }

    @Test
    public void getAllEnrolmentsBadRequest() throws Exception {

        doThrow(ListEmptyException.class).when(enrolmentQuerryService).getAllEnrolments();

        restMockMvc.perform(get("/api/v1/enrolment/allEnrolments"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getEnrolmentById() throws Exception {

        Faker faker = new Faker();

        Student student = Student.builder().id(1L).firstName("Popescu").lastName("Andrei").age(12).email("").adress("Bucuresti").build();

        Course course = Course.builder().id(1L).name(faker.educator().course()).build();

        Enrolment enrolment = Enrolment.builder().id(1L).student(student).course(course).build();

        doReturn(enrolment).when(enrolmentQuerryService).getEnrolmentById(1L);

        restMockMvc.perform(get("/api/v1/enrolment/enrolmentById?id=1"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(enrolment)));
    }

    @Test
    public void getEnrolmentByIdBadRequest() throws Exception {

        doThrow(ListEmptyException.class).when(enrolmentQuerryService).getEnrolmentById(1L);

        restMockMvc.perform(get("/api/v1/enrolment/enrolmentById?id=1"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getEnrolmentByStudentId() throws Exception {

        Faker faker = new Faker();

        List<Enrolment> enrolmentList = new ArrayList<>();

        Student student = Student.builder().id(1L).firstName("Popescu").lastName("Andrei").age(12).email("").adress("Bucuresti").build();

        for(int i = 0 ; i <= 10 ;i++){

            Course course = Course.builder().id((long) i).name(faker.educator().course()).build();

            Enrolment enrolment = Enrolment.builder().id((long) i).student(student).course(course).build();

            enrolmentList.add(enrolment);

        }

        doReturn(enrolmentList).when(enrolmentQuerryService).getEnrolmentByStudentId(1L);

        restMockMvc.perform(get("/api/v1/enrolment/enrolmentByStudentId?studentId=1"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(enrolmentList)));
    }

    @Test
    public void getEnrolmentByStudentIdBadRequest() throws Exception {

        doThrow(ListEmptyException.class).when(enrolmentQuerryService).getEnrolmentByStudentId(1L);

        restMockMvc.perform(get("/api/v1/enrolment/enrolmentByStudentId?studentId=1"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getEnrolmentByCourseId() throws Exception {

        Faker faker = new Faker();

        List<Enrolment> enrolmentList = new ArrayList<>();

        Course course = Course.builder().id(1L).name(faker.educator().course()).build();

        for(int i = 0 ; i <= 10 ;i++){

            Student student = Student.builder().id((long) i).firstName("Popescu").lastName("Andrei").age(12).email("").adress("Bucuresti").build();

            Enrolment enrolment = Enrolment.builder().id((long) i).student(student).course(course).build();

            enrolmentList.add(enrolment);

        }

        doReturn(enrolmentList).when(enrolmentQuerryService).getEnrolmentByCourseId(1L);

        restMockMvc.perform(get("/api/v1/enrolment/enrolmentByCourseId?courseId=1"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(enrolmentList)));
    }

    @Test
    public void getEnrolmentByCourseIdBadRequest() throws Exception {

        doThrow(ListEmptyException.class).when(enrolmentQuerryService).getEnrolmentByCourseId(1L);

        restMockMvc.perform(get("/api/v1/enrolment/enrolmentByCourseId?courseId=1"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void addEnrolment() throws Exception {

        Faker faker = new Faker();

        StudentDTO student = StudentDTO.builder().firstName("Popescu").lastName("Andrei").age(12).email("").adress("Bucuresti").build();

        CourseDTO course = CourseDTO.builder().name(faker.educator().course()).build();

        EnrolmentDTO enrolment = EnrolmentDTO.builder().studentdto(student).courseDTO(course).build();

        doNothing().when(enrolmentCommandService).addEnrolment(enrolment);
        restMockMvc.perform(post("/api/v1/enrolment/addEnrolment").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(enrolment)))
                 .andExpect(status().isOk());

    }

    @Test
    public void addEnrolmentBadRequest() throws Exception {

        Faker faker = new Faker();

        StudentDTO student = StudentDTO.builder().firstName("Popescu").lastName("Andrei").age(12).email("").adress("Bucuresti").build();

        CourseDTO course = CourseDTO.builder().name(faker.educator().course()).build();

        EnrolmentDTO enrolment = EnrolmentDTO.builder().studentdto(student).courseDTO(course).build();

        doThrow(ListEmptyException.class).when(enrolmentCommandService).addEnrolment(enrolment);
        restMockMvc.perform(post("/api/v1/enrolment/addEnrolment").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(enrolment)))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void updateEnrolment() throws Exception {

        Faker faker = new Faker();

        StudentDTO student = StudentDTO.builder().firstName("Popescu").lastName("Andrei").age(12).email("").adress("Bucuresti").build();

        CourseDTO course = CourseDTO.builder().name(faker.educator().course()).build();

        EnrolmentDTO enrolment = EnrolmentDTO.builder().studentdto(student).courseDTO(course).build();

        doNothing().when(enrolmentCommandService).updateEnrolment(enrolment);
        restMockMvc.perform(put("/api/v1/enrolment/updateEnrolment").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(enrolment)))
                .andExpect(status().isOk());

    }

    @Test
    public void updateEnrolmentBadRequest() throws Exception {

        Faker faker = new Faker();

        StudentDTO student = StudentDTO.builder().firstName("Popescu").lastName("Andrei").age(12).email("").adress("Bucuresti").build();

        CourseDTO course = CourseDTO.builder().name(faker.educator().course()).build();

        EnrolmentDTO enrolment = EnrolmentDTO.builder().studentdto(student).courseDTO(course).build();

        doThrow(ListEmptyException.class).when(enrolmentCommandService).updateEnrolment(enrolment);
        restMockMvc.perform(put("/api/v1/enrolment/updateEnrolment").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(enrolment)))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void deleteEnrolment() throws Exception{

        RemoveEnrolmentDTO removeEnrolmentDTO = RemoveEnrolmentDTO.builder().firstName("Popescu").lastName("Andrei").courseName("Matematica").build();

        doNothing().when(enrolmentCommandService).deleteEnrolment(removeEnrolmentDTO);

      restMockMvc.perform(delete("/api/v1/enrolment/deleteEnrolment").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(removeEnrolmentDTO))).andExpect(status().isOk());

    }

    @Test
    public void deleteEnrolmentBadRequest() throws Exception{

        RemoveEnrolmentDTO removeEnrolmentDTO = RemoveEnrolmentDTO.builder().firstName("Popescu").lastName("Andrei").courseName("Matematica").build();

        doThrow(ListEmptyException.class).when(enrolmentCommandService).deleteEnrolment(removeEnrolmentDTO);

        restMockMvc.perform(delete("/api/v1/enrolment/deleteEnrolment").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(removeEnrolmentDTO)))
                .andExpect(status().isBadRequest());

    }


}