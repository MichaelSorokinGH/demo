package Java_Advanced.demo.model.dto;

import Java_Advanced.demo.model.entity.User;
import Java_Advanced.demo.model.enums.BookCondition;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LibraryDTO {

    String libraryName;

    LocalDate dateOfIssue;

    UserDTO userDTO;
}
