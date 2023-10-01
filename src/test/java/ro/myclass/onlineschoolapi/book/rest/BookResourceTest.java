package ro.myclass.onlineschoolapi.book.rest;

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
import ro.myclass.onlineschoolapi.book.dto.BookDTO;
import ro.myclass.onlineschoolapi.book.model.Book;
import ro.myclass.onlineschoolapi.book.service.BookCommandService;
import ro.myclass.onlineschoolapi.book.service.BookQuerryService;
import ro.myclass.onlineschoolapi.exceptions.ListEmptyException;
import ro.myclass.onlineschoolapi.student.model.Student;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
class BookResourceTest {

    @Mock
    private BookCommandService bookCommandService;

    @Mock
    private BookQuerryService bookQuerryService;

    @InjectMocks
    private BookResource bookResource;

    private MockMvc restMockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void initialConfig(){
        this.restMockMvc = MockMvcBuilders.standaloneSetup(bookResource).build();
    }

    @Test
    public void getAllBooks() throws Exception{


        Faker faker = new Faker();

        List<Book> bookList = new ArrayList<>();


        //generate 1 student without faker
        Student student = Student.builder().firstName("Popescu Andrei").lastName("Popescu").age(12).email("").adress("Bucuresti").build();
        for(int i = 0 ; i < 10;i++){
            Book book = Book.builder().bookName(faker.book().title()).student(student).build();
            bookList.add(book);
        }

        doReturn(bookList).when(bookQuerryService).getAllBooks();

        restMockMvc.perform(get("/api/v1/book/allBooks"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(bookList)));
    }

    @Test
    public void getAllBooksBadRequest() throws Exception{
        doThrow(ListEmptyException.class).when(bookQuerryService).getAllBooks();

        restMockMvc.perform(get("/api/v1/book/allBooks"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getBookById() throws Exception{

        Faker faker = new Faker();

        Student student = Student.builder().firstName("Popescu Andrei").lastName("Popescu").age(12).email("").adress("Bucuresti").build();

        Book book = Book.builder().bookName(faker.book().title()).student(student).build();

        doReturn(book).when(bookQuerryService).getBookById(book.getId());

        restMockMvc.perform(get("/api/v1/book/bookById?id=" + book.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(book)));
    }

    @Test
    public void getBookByIdBadRequest() throws Exception{
        doThrow(ListEmptyException.class).when(bookQuerryService).getBookById(1);

        restMockMvc.perform(get("/api/v1/book/bookById?id=1"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getBookByName() throws Exception{

        Faker faker = new Faker();

        Student student = Student.builder().firstName("Popescu Andrei").lastName("Popescu").age(12).email("").adress("Bucuresti").build();

        Book book = Book.builder().bookName(faker.book().title()).student(student).build();

        doReturn(book).when(bookQuerryService).getBookByName(book.getBookName());

        restMockMvc.perform(get("/api/v1/book/bookByName?name=" + book.getBookName()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(book)));
    }

    @Test
    public void getBookByNameBadRequest() throws Exception{
        doThrow(ListEmptyException.class).when(bookQuerryService).getBookByName("Math");

        restMockMvc.perform(get("/api/v1/book/bookByName?name=Math"))
                .andExpect(status().isBadRequest());
    }





    @Test
    public void addBook() throws Exception{

        Faker faker = new Faker();

        BookDTO book = BookDTO.builder().name(faker.book().title()).build();

        restMockMvc.perform(post("/api/v1/book/addBook")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isOk());



    }

    @Test
    public void updateBook() throws Exception{

        Faker faker = new Faker();

        BookDTO book = BookDTO.builder().name(faker.book().title()).build();

        restMockMvc.perform(put("/api/v1/book/updateBook")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isOk());
    }

    @Test
    public void updateBookBadRequest() throws Exception{

        Faker faker = new Faker();

        BookDTO book = BookDTO.builder().name(faker.book().title()).build();

        doThrow(ListEmptyException.class).when(bookCommandService).updateBook(book);

        restMockMvc.perform(put("/api/v1/book/updateBook")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deleteBook() throws Exception{

        doNothing().when(bookCommandService).deleteBook("Math");

        restMockMvc.perform(delete("/api/v1/book/deleteBook?name=Math"))
                .andExpect(status().isOk());

        }

    @Test
    public void deleteBookBadRequest() throws Exception{

        doThrow(ListEmptyException.class).when(bookCommandService).deleteBook("Math");

        restMockMvc.perform(delete("/api/v1/book/deleteBook?name=Math"))
                .andExpect(status().isBadRequest());

        }

    }


