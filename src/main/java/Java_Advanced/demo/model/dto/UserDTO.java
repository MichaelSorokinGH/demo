package Java_Advanced.demo.model.dto;

import Java_Advanced.demo.model.enums.Gender;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;


import java.util.List;
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDTO {

    Integer age;
    String firstName;
    String lastName;
    Gender gender;
    String email;

    BookDTO bookDTO;

//    Енум Отдельный тип данных, отдельное поле, которое используется у Юзера
//    List<BookDTO> books;
//    Коллекция для связи между пользователем и книгой

//    ВСЕ ЭТИ ТРАНСФЕРНЫЕ КЛАССЫ ИДУТ ОТ КЛИЕНТА!
}
