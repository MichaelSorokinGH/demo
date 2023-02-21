package Java_Advanced.demo.model.entity;

import Java_Advanced.demo.model.enums.BookCondition;
import Java_Advanced.demo.model.enums.GenresOfBooks;
import Java_Advanced.demo.model.enums.Status;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
//Аннотация говорит о том что этот класс является сущностью для бд
@Table(name = "books")
//название таблицы, принято указывать во множественном числе
@FieldDefaults(level = AccessLevel.PRIVATE)
//Аннотация задающая для всех полей модификатор Private
public class Book {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
////Задаёт автоинкремент, счётчик увеличивается
//(каждый раз когда создаётся пользователь - счётчик будет крутиться, создавать новый ID
    Long id;

    @JsonIgnore
    @Column(name = "book_title", unique = true)
    String bookTitle;

    @JsonIgnore
    @Enumerated(EnumType.STRING)
//    С этой аннотацией в БД будет прописываться не число, а строка
    BookCondition bookCondition;
//    Енум Отдельный тип данных, отдельное поле, которое используется у Книги
    @JsonIgnore
    @Enumerated(EnumType.STRING)
    GenresOfBooks genresOfBooks;

    @JsonIgnore
    @Column(name = "publication_date")
    LocalDate publicationDate;
//    Вместо докалдэйт стринг?
    @JsonIgnore
    @CreationTimestamp
//    когда создаём объект можно использовать данную аннотацию.
//    Даёт возможность создавать дату для объекта при создании
    @Column(name = "created_at")
    LocalDateTime createdAt;
//    Когда книга была создана в баззе данных
    @JsonIgnore
    @Column(name = "updated_at")
    LocalDateTime updatedAt;
//    Обновляем данные книги
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference(value = "author_books")
    Author author;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference(value = "library_books")
    Library library;

    @JsonIgnore
    @Enumerated(EnumType.STRING)
    Status status = Status.CREATED;

}
