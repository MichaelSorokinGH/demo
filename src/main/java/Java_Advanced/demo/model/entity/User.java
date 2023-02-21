package Java_Advanced.demo.model.entity;

import Java_Advanced.demo.model.enums.Gender;
import Java_Advanced.demo.model.enums.Status;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name = "users")
//название таблицы, принято указывать во множественном числе
@FieldDefaults(level = AccessLevel.PRIVATE)
//Аннотация задающая для всех полей модификатор Private
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
////Задаёт автоинкремент, счётчик увеличивается
//(каждый раз когда создаётся пользователь - счётчик будет крутиться, создавать новый ID
    Long id;

    Integer age;

    @Column(name = "first_name")
//    Использовать при написании снэк кейс
    String firstName;

    @Column(name = "last_name")
    String lastName;

    @Column(unique = true)
    String email;

    @Enumerated(EnumType.STRING)
//    С этой аннотацией в БД будет прописываться не число, а строка
    Gender gender;
//    Енум Отдельный тип данных, отдельное поле, которое используется у Юзера
    @CreationTimestamp
    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable = false)
    LocalDateTime createdAt;
//    Когда юзер был создан в баззе данных
//    columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP" говорит о том, что
//    при создании должно прописываться

    @Column(name = "updated_at")
    LocalDateTime updatedAt;
//    Обновляем данные пользователя

    @OneToOne(cascade = CascadeType.ALL)
    @JsonManagedReference(value = "library_user")
    Library library;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonBackReference(value = "user_books")
    List<Book> books;
//    Коллекция для связи между пользователем и книгой
    @Enumerated(EnumType.STRING)
    Status status;

}
