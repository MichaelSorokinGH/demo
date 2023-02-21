package Java_Advanced.demo.model.entity;

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
@Getter
@Setter
@Entity
//Аннотация говорит о том что этот класс является сущностью для бд
@Table(name = "Librarys")
//название таблицы, принято указывать во множественном числе
@FieldDefaults(level = AccessLevel.PRIVATE)
//Аннотация задающая для всех полей модификатор Private
public class Library {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
////Задаёт автоинкремент, счётчик увеличивается
//(каждый раз когда создаётся пользователь - счётчик будет крутиться, создавать новый ID
    Long id;

    @Column(name = "library_name", unique = true)
    String libraryName;

    @Column(name = "date_of_issue")
    LocalDate dateOfIssue;

    @CreationTimestamp
    @Column(name = "created_at")
    LocalDateTime createdAt;

    @Column(name = "updated_at")
    LocalDateTime updatedAt;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonBackReference(value = "library_user")
    User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonBackReference(value = "library_author")
    Author author;

    @Enumerated(EnumType.STRING)
    Status status = Status.CREATED;

}
