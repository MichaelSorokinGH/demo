package Java_Advanced.demo.model.entity;

import Java_Advanced.demo.model.enums.Gender;
import Java_Advanced.demo.model.enums.Status;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
//Аннотация говорит о том что этот класс является сущностью для бд
@Table(name = "authors")
//название таблицы, принято указывать во множественном числе
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
////Задаёт автоинкремент, счётчик увеличивается
    @JsonIgnore
    Long id;

    @Column(name = "first_name", unique = true)
//    Использовать при написании снэк кейс
    String firstName;

    @Column(name = "last_name")
    String lastName;

    @Enumerated(EnumType.STRING)
//    С этой аннотацией в БД будет прописываться не число, а строка
    Gender gender;
//    Енум Отдельный тип данных, отдельное поле, которое используется у Автора

    @Column(name = "date_of_birth")
    String dateOfBirth;

    @CreationTimestamp
    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable = false)
    LocalDateTime createdAt;

    @Column(name = "updated_at")
    LocalDateTime updatedAt;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonBackReference(value = "library_author")
    Library library;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonBackReference(value = "book_authors")
    List<Book> books;


    @Enumerated(EnumType.STRING)
    Status status;

}








