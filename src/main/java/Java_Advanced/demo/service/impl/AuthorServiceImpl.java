package Java_Advanced.demo.service.impl;

import Java_Advanced.demo.exceptions.CustomException;
import Java_Advanced.demo.model.dto.AuthorDTO;
import Java_Advanced.demo.model.dto.BookDTO;
import Java_Advanced.demo.model.entity.Author;
import Java_Advanced.demo.model.entity.Book;
import Java_Advanced.demo.model.enums.Status;
import Java_Advanced.demo.model.repository.AuthorRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final BookService bookService;
    private final AuthorRepository authorRepository;
    private final ObjectMapper mapper;


    @Override
    public AuthorDTO create(AuthorDTO authorDTO) {

        Author author = mapper.convertValue(authorDTO, Author.class);
        author.setStatus(Status.CREATED);
        Author save = authorRepository.save(author);
        return mapper.convertValue(save, AuthorDTO.class);
    }

    @Override
    public AuthorDTO update(AuthorDTO authorDTO) {
//        Author author = getAuthor(authorDTO.getId());
//
//        author.setFirstName(authorDTO.getFirstName() == null ? author.getFirstName() : authorDTO.getFirstName());
//        author.setLastName(authorDTO.getLastName() == null ? author.getLastName() : authorDTO.getLastName());
//        author.setDateOfBirth(authorDTO.getDateOfBirth() == null ? author.getDateOfBirth() : authorDTO.getDateOfBirth());
//        author.setGender(authorDTO.getGender() == null ? author.getGender() : authorDTO.getGender());
//        author.setUpdatedAt(LocalDateTime.now());
//        author.setStatus(Status.UPDATED);
//
//        return mapper.convertValue(authorRepository.save(author), AuthorDTO.class);
        return null;

    }

    @Override
    public AuthorDTO get(Long id) {
        return mapper.convertValue(getAuthor(id), AuthorDTO.class);
    }

    @Override
    public void delete(Long id) {
        Author author = getAuthor(id);
        author.setStatus(Status.DELETED);
        author.setUpdatedAt(LocalDateTime.now());
        authorRepository.save(author);

    }
    @Override
    public Author getAuthor(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new CustomException("Пользователь с таким id не найден", HttpStatus.NOT_FOUND));
    }

    public AuthorDTO addToBook(Long id, String bookTitle) {
        Book book = bookService.getBook(bookTitle);
        Author author = getAuthor(id);
        author.getBooks().add(book);
        Author save = authorRepository.save(author);
        AuthorDTO authorDTO = mapper.convertValue(save, AuthorDTO.class);
        authorDTO.setBookDTO(mapper.convertValue(book, BookDTO.class));
        return authorDTO;

    }
}
