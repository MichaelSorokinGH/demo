package Java_Advanced.demo.model.dto;

import Java_Advanced.demo.model.enums.BookCondition;
import Java_Advanced.demo.model.enums.GenresOfBooks;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookDTO {
    @Enumerated(EnumType.STRING)
//    С этой аннотацией в БД будет прописываться не число, а строка
//    (Тут вопрос нужно ли тут использовать данную аннотацию?
    BookCondition bookCondition;

    String bookTitle;
//    Енум Отдельный тип данных, отдельное поле, которое используется у Книги
    LocalDate publicationDate;
//    Лучше использовать стринг
    GenresOfBooks genresOfBooks;

    AuthorDTO authorDTO;

    LibraryDTO libraryDTO;



//    List<AuthorDTO> authors;


}
