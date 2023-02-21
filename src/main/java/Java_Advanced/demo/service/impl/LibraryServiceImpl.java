package Java_Advanced.demo.service.impl;

import Java_Advanced.demo.exceptions.CustomException;
import Java_Advanced.demo.model.dto.LibraryDTO;
import Java_Advanced.demo.model.dto.UserDTO;
import Java_Advanced.demo.model.entity.Library;
import Java_Advanced.demo.model.entity.User;
import Java_Advanced.demo.model.enums.Status;
import Java_Advanced.demo.model.repository.LibraryRepository;
import Java_Advanced.demo.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class LibraryServiceImpl implements LibraryService {
    private final UserService userService;
    private final LibraryRepository libraryRepository;
    private final ObjectMapper mapper;

    @Override
    public LibraryDTO create(LibraryDTO libraryDTO) {

        libraryRepository.findByLibraryName(libraryDTO.getLibraryName()).ifPresent(
                b -> {
                    throw new CustomException("Библиотека с таким названием уже существует", HttpStatus.CONFLICT);
                }
        );

        Library library = mapper.convertValue(libraryDTO, Library.class);
        library.setStatus(Status.CREATED);
        libraryRepository.save(library);


//        BookShop bookShop = mapper.convertValue(bookShopDTO, BookShop.class);
//
//        List<Book> books = bookShopDTO.getBooks().stream()
//                .map(b -> {
//                    Book book = new Book();
//                    book.setBookTitle(b.getBookTitle());
//                    book.setBookCondition(b.getBookCondition());
//                    book.setAuthorsCount(b.getAuthorsCount());
//                    try {
//                        book.setPublicationDate(LocalDate.parse(b.getPublicationDate()));
//                    } catch (Exception e) {
//                        log.error(e.getMessage());
//                        throw new RuntimeException(e);
//                    }
//                    return book;
//                })
//                .collect(Collectors.toList());
//
//        bookShop.setBooks(books);
//
//        BookShop save = bookShopRepository.save(bookShop);
//
//        BookShopDTO result = mapper.convertValue(bookShopDTO, BookShopDTO.class);
//        List<BookDTO> booksDTO = bookShop.getBooks().stream()
//                .map(b -> {
//
//                    BookDTO bookDTO = new BookDTO();
//                    bookDTO.setBookTitle(b.getBookTitle());
//                    bookDTO.setBookCondition(b.getBookCondition());
//                    bookDTO.setAuthorsCount(b.getAuthorsCount());
//                    bookDTO.setPublicationDate(String.valueOf(b.getPublicationDate()));
//                    return bookDTO;
//                })
//                .collect(Collectors.toList());
//
//        result.setBooks((booksDTO));
//
//
        return mapper.convertValue(library, LibraryDTO.class);
    }

    @Override
    public LibraryDTO update(LibraryDTO libraryDTO) {
        Library library = getLibrary(libraryDTO.getLibraryName());
        library.setDateOfIssue(libraryDTO.getDateOfIssue() == null ? library.getDateOfIssue() : (libraryDTO.getDateOfIssue()));
        library.setUpdatedAt(LocalDateTime.now());
        library.setStatus(Status.UPDATED);

        return mapper.convertValue(libraryRepository.save(library), LibraryDTO.class);
    }



    @Override
    public LibraryDTO get(String libraryName) {
        return mapper.convertValue(getLibrary(libraryName), LibraryDTO.class);
    }

    @Override
    public void delete(String libraryName) {
        Library library = getLibrary(libraryName);
        library.setStatus(Status.DELETED);
        library.setUpdatedAt(LocalDateTime.now());
        libraryRepository.save(library);
    }

    @Override
    public LibraryDTO addToUser(String libraryName, String email) {
        User user = userService.getUser(email);
        Library library = getLibrary(libraryName);
        library.setUser(user);
        Library save = libraryRepository.save(library);
        LibraryDTO libraryDTO = mapper.convertValue(save, LibraryDTO.class);
        libraryDTO.setUserDTO(mapper.convertValue(user, UserDTO.class));
        return libraryDTO;
    }

    public Library getLibrary(String libraryName) {
        return libraryRepository.findByLibraryName(libraryName)
                .orElseThrow(() -> new CustomException("Библиотека с таким названием не найдена", HttpStatus.NOT_FOUND));
    }
}







