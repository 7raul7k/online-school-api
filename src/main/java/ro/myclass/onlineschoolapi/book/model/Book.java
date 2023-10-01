package ro.myclass.onlineschoolapi.book.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ro.myclass.onlineschoolapi.student.model.Student;

@Table(name = "book_db")
@Entity(name = "Book")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Book {
    @Id
    @SequenceGenerator(name = "book_sequence",
            sequenceName = "book_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "book_sequence")
    @Column(name = "id")
    private long id;

    @Column(name = "book_name",
            nullable = false,
            columnDefinition = "TEXT")
    private String bookName;

    @OneToOne(mappedBy = "book",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Student student;


    @Override
    public String toString(){
        return id+","+bookName;
    }

    @Override
    public boolean equals(Object obj){
        Book b = (Book) obj;

        if(this.bookName.equals(b.bookName)){
            return true;
        }

        return false;
    }


}
