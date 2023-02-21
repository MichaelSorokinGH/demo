package Java_Advanced.demo.service.impl;

import Java_Advanced.demo.exceptions.CustomException;
import Java_Advanced.demo.model.dto.BookDTO;
import Java_Advanced.demo.model.entity.Book;
import Java_Advanced.demo.model.enums.Status;
import Java_Advanced.demo.model.repository.BookRepository;
import Java_Advanced.demo.utils.PaginationUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final ObjectMapper mapper;
    @Override
    public BookDTO create(BookDTO bookDTO) {

        bookRepository.findByBookTitle(bookDTO.getBookTitle()).ifPresent(
                b -> {throw new CustomException("Книга с таким названием уже существует", HttpStatus.CONFLICT);}
        );
        Book book = mapper.convertValue(bookDTO, Book.class);
        book.setStatus(Status.CREATED);
        bookRepository.save(book);
//        List<Author> authors = bookDTO.getAuthors().stream()
//                .map(a -> {
//                    Author author = new Author();
//                    author.setFirstName(a.getFirstName());
//                    author.setLastName(a.getLastName());
//                    author.setGender(a.getGender());
//                    author.setDateOfBirth(a.getDateOfBirth());
//                    return author;
//                })
//                .collect(Collectors.toList());
//
//        book.setAuthors(authors);
//
//        Book save = bookRepository.save(book);
//
//        BookDTO result = mapper.convertValue(book, BookDTO.class);
//        List<AuthorDTO> authorsDTO = book.getAuthors().stream()
//                .map(a -> {
//
//                    AuthorDTO authorDTO = new AuthorDTO();
//                    authorDTO.setFirstName(a.getFirstName());
//                    authorDTO.setLastName(a.getLastName());
//                    authorDTO.setGender(a.getGender());
//                    authorDTO.setDateOfBirth(a.getDateOfBirth());
//                    return authorDTO;
//                })
//                .collect(Collectors.toList());
//
//        result.setAuthors(authorsDTO);
        return mapper.convertValue(book, BookDTO.class);
    }

    @Override
    public BookDTO update(BookDTO bookDTO) {
        Book book = getBook(bookDTO.getBookTitle());
//       Если запись не найдена - бросаем исключение
//        book.setBookTitle(bookDTO.getBookTitle() == null ? book.getBookTitle() : bookDTO.getBookTitle());
//        BookTitle будет уникальным полем
        book.setGenresOfBooks(book.getGenresOfBooks() == null ? book.getGenresOfBooks() : bookDTO.getGenresOfBooks());
        book.setPublicationDate(bookDTO.getPublicationDate() == null ? book.getPublicationDate() : bookDTO.getPublicationDate());
        book.setBookCondition(book.getBookCondition() == null ? book.getBookCondition() : bookDTO.getBookCondition());
        book.setUpdatedAt(LocalDateTime.now());
        book.setStatus(Status.UPDATED);

        return mapper.convertValue( bookRepository.save(book), BookDTO.class);
    }

    @Override
    public BookDTO get(String bookTitle) {
        return mapper.convertValue(getBook(bookTitle), BookDTO.class);
    }


    @Override
    public List<BookDTO> getBooks() {
        return bookRepository.findAll().stream().map(b -> get(b.getBookTitle()))
                .collect(Collectors.toList());

    }

    @Override
    public void delete(String bookTitle) {
        Book book = getBook(bookTitle);
        book.setStatus(Status.DELETED);
        book.setUpdatedAt(LocalDateTime.now());
        bookRepository.save(book);
    }

    public Book getBook(String bookDTO) {
        return bookRepository.findByBookTitle(bookDTO)
                .orElseThrow(() -> new CustomException("Книга с таким названием не найдена", HttpStatus.NOT_FOUND));
    }



//    @Override
//    public BookDTO addToAuthor(String bookTitle, Long id) {
//        Author author = authorService.getAuthor(id);
//        Book book = getBook(bookTitle);
//        book.setAuthor(author);
//        Book save = bookRepository.save(book);
//        BookDTO bookDTO = mapper.convertValue(save, BookDTO.class);
//        bookDTO.setAuthorDTO(mapper.convertValue(author, AuthorDTO.class));
//        return bookDTO;
//
////      МЕТОД ДЛЯ СВЯЗЫВАНИЯ AUTHOR И КНИГИ!
//    }

    @Override
    public ModelMap getAllBooks(Integer page, Integer perPage, String sort, Sort.Direction order) {
        Pageable pageRequest = PaginationUtil.getPageRequest(page, perPage, sort, order);
        Page<Book> pageResult = bookRepository.findAll(pageRequest);

        List<BookDTO> content = pageResult.getContent().stream()
                .map(b -> mapper.convertValue(b, BookDTO.class))
                .collect(Collectors.toList());

        PagedListHolder<BookDTO> result = new PagedListHolder<>();

        result.setPage(page);
        result.setPageSize(perPage);

        ModelMap map = new ModelMap();
        map.addAttribute("content", result.getPageList());
        map.addAttribute("pageNumber", page);
        map.addAttribute("pageSize", result.getPageSize());
        map.addAttribute("totalPages", result.getPageCount());

        return map;
    }

}
