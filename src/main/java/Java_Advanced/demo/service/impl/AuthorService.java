package Java_Advanced.demo.service.impl;

import Java_Advanced.demo.model.dto.AuthorDTO;
import Java_Advanced.demo.model.entity.Author;

public interface AuthorService {

    AuthorDTO create(AuthorDTO authorDTO);

    AuthorDTO update(AuthorDTO authorDTO);

    AuthorDTO get(Long id);

    void delete(Long id);

    Author getAuthor(Long id);

    AuthorDTO addToBook(Long id, String bookTitle);

//    BookDTO addToAuthor(String bookTitle, Long id);
}


