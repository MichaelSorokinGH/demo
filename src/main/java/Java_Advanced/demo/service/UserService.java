package Java_Advanced.demo.service;

import Java_Advanced.demo.model.dto.UserDTO;
import Java_Advanced.demo.model.entity.User;

public interface UserService {

    UserDTO create(UserDTO userDTO);
    UserDTO update(UserDTO userDTO);

    UserDTO get(String email);
    void delete(String email);

    User getUser(String email);

    UserDTO addToBook(String email, String bookTitle);



}
