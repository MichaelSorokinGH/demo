package Java_Advanced.demo.service.impl;

import Java_Advanced.demo.model.dto.BookDTO;
import Java_Advanced.demo.model.entity.Book;
import org.springframework.data.domain.Sort;
import org.springframework.ui.ModelMap;

import java.util.List;

public interface BookService {

    BookDTO create(BookDTO bookDTO);

    BookDTO update(BookDTO bookDTO);

    BookDTO get(String bookTitle);

    void delete(String bookTitle);

//    BookDTOResponse addToUser(String bookTitle, String email);

    ModelMap getAllBooks(Integer page, Integer perPage, String sort, Sort.Direction order);

//    BookDTO addToAuthor(String bookTitle, Long id);


    Book getBook(String bookTitle);

    List<BookDTO> getBooks();
}


