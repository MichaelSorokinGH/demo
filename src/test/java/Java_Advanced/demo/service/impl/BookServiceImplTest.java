package Java_Advanced.demo.service.impl;

import Java_Advanced.demo.exceptions.CustomException;
import Java_Advanced.demo.model.dto.BookDTO;
import Java_Advanced.demo.model.dto.LibraryDTO;
import Java_Advanced.demo.model.entity.Book;
import Java_Advanced.demo.model.entity.Library;
import Java_Advanced.demo.model.repository.BookRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceImplTest {

    @InjectMocks
    private BookServiceImpl bookService;

    @Mock
    private BookRepository bookRepository;

    @Spy
    private ObjectMapper mapper;

    @Test
    public void create() {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setBookTitle("Warrior");

        when(bookRepository.save(any(Book.class))).thenReturn(any(Book.class));

        BookDTO result = bookService.create(bookDTO);
        assertEquals(bookDTO.getBookTitle() , result.getBookTitle());

    }

    @Test(expected = CustomException.class)
    public void create_exception() {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setBookTitle("Warrior");

        when(bookRepository.findByBookTitle(anyString())).thenThrow(CustomException.class);
        bookService.create(bookDTO);
    }


//    @Test
//    public void update() {
//        BookDTO bookDTO = new BookDTO();
//        bookDTO.setBookTitle("Spice");
//
//        when(bookRepository.save(any(Book.class))).thenReturn(any(Book.class));
//
//        BookDTO result = bookService.update(bookDTO);
//        assertEquals(bookDTO.getBookTitle(), result.getBookTitle());
//    }

    @Test
    public void get() {
    }

    @Test
    public void getBooks() {
    }

    @Test
    public void delete() {
        Book book = new Book();
        book.setBookTitle("Warrior");
        when(bookRepository.findByBookTitle(anyString())).thenReturn(Optional.of(book));

        when(bookRepository.save(any(Book.class))).thenReturn(any(Book.class));

//        verify(libraryRepository, times(1)).save(library);

        bookService.delete("Warrior");
    }

    @Test
    public void getBook() {
    }

    @Test
    public void getAllBooks() {
    }
}