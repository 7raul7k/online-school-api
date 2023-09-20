package ro.myclass.onlineschoolapi.course.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
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
import ro.myclass.onlineschoolapi.course.service.CourseCommandService;
import ro.myclass.onlineschoolapi.course.service.CourseQuerryService;
import ro.myclass.onlineschoolapi.exceptions.CourseNotFoundException;
import ro.myclass.onlineschoolapi.exceptions.ListEmptyException;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
class CourseResourceTest {

    @Mock
    private CourseCommandService courseCommandService;

    @Mock
    private CourseQuerryService courseQuerryService;

    @InjectMocks
    private CourseResource courseResource;

    private MockMvc restMockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void initialConfig(){
        this.restMockMvc = MockMvcBuilders.standaloneSetup(courseResource).build();
    }

    @Test
    public void getAllCourses() throws Exception{

        Faker faker = new Faker();

        List<Course> courseList = new ArrayList<>();

        for(int i = 0 ; i <= 10 ;i++){
            Course course = Course.builder().name(faker.educator().course()).build();
            courseList.add(course);
        }

        doReturn(courseList).when(courseQuerryService).getAllCourses();

        restMockMvc.perform(get("/api/v1/course/allCourses"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(courseList)));

    }

    @Test
    public void getAllCoursesBadRequest() throws Exception{

        doThrow(ListEmptyException.class).when(courseQuerryService).getAllCourses();

        restMockMvc.perform(get("/api/v1/course/allCourses"))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void getCourseById() throws Exception{

        Faker faker = new Faker();

        Course course = Course.builder().name(faker.educator().course()).build();

        doReturn(course).when(courseQuerryService).getCourseById(1);

        restMockMvc.perform(get("/api/v1/course/courseById?id=1"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(course)));

    }

    @Test
    public void getCourseByIdBadRequest() throws Exception{

        doThrow(ListEmptyException.class).when(courseQuerryService).getCourseById(1);

        restMockMvc.perform(get("/api/v1/course/courseById?id=1"))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void getCourseByName() throws Exception{


        Course course = Course.builder().name("Math").department("Math").id(1L).build();

        doReturn(course).when(courseQuerryService).getCourseByName(course.getName());

        restMockMvc.perform(get("/api/v1/course/courseByName").param("name", course.getName()))
                .andExpect(status().isOk());

    }

    @Test
    public void getCourseByNameBadRequest() throws Exception{

        doThrow(ListEmptyException.class).when(courseQuerryService).getCourseByName("Math");

        restMockMvc.perform(get("/api/v1/course/courseByName").param("name", "Math"))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void addCourse() throws Exception{

        Faker faker = new Faker();

        CourseDTO course = CourseDTO.builder().name(faker.educator().course()).build();

        restMockMvc.perform(post("/api/v1/course/addCourse")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(course)))
                .andExpect(status().isOk());

        verify(courseCommandService, times(1)).addCourse(course);

    }

    @Test
    public void updateCourse() throws Exception{

        Faker faker = new Faker();

        CourseDTO course = CourseDTO.builder().name(faker.educator().course()).build();

        restMockMvc.perform(post("/api/v1/course/updateCourse")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(course)))
                .andExpect(status().isOk());

        verify(courseCommandService, times(1)).updateCourse(course);

    }

    @Test
    public void updateCourseBadRequest() throws Exception{

        Faker faker = new Faker();

        CourseDTO course = CourseDTO.builder().name(faker.educator().course()).build();

        doThrow(CourseNotFoundException.class).when(courseCommandService).updateCourse(course);

        restMockMvc.perform(put("/api/v1/course/updateCourse")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(course)))
                .andExpect(status().isBadRequest());

    }

}