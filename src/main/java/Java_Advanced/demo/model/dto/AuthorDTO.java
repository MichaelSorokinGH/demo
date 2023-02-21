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
public class AuthorDTO {

    String firstName;
    String lastName;
    Gender gender;
    String dateOfBirth;

    BookDTO bookDTO;

}
