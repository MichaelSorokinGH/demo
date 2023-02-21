package Java_Advanced.demo.service.impl;

import Java_Advanced.demo.exceptions.CustomException;
import Java_Advanced.demo.model.dto.BookDTO;
import Java_Advanced.demo.model.dto.UserDTO;
import Java_Advanced.demo.model.entity.Book;
import Java_Advanced.demo.model.entity.User;
import Java_Advanced.demo.model.enums.Status;
import Java_Advanced.demo.model.repository.UserRepository;
import Java_Advanced.demo.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
//Ломбок
@Service
//Создаёт Bean данного класса, скажет спрингу что данный класс является бином. поднятие контекста проинициализируется.
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final BookService bookService;
    private final UserRepository userRepository;
    private final ObjectMapper mapper;

    @Override
    public UserDTO create(UserDTO userDTO) {

        userRepository.findByEmail(userDTO.getEmail()).ifPresent(
                b -> {
                    throw new CustomException("Пользователь с таким email уже существует", HttpStatus.CONFLICT);
                }
        );

        User user = mapper.convertValue(userDTO, User.class);
        user.setStatus(Status.CREATED);
        User save = userRepository.save(user);

        return mapper.convertValue(save, UserDTO.class);
    }

    @Override
    public UserDTO update(UserDTO userDTO) {
//        User user = userRepository.findByEmail(userDTO.getEmail())
//                .orElseThrow(() -> new CustomException("Книга с таким названием не найдена", HttpStatus.NOT_FOUND));
////       Если запись не найдена - бросаем исключение
////        book.setBookTitle(bookDTO.getBookTitle() == null ? book.getBookTitle() : bookDTO.getBookTitle());
//
//        user.setEmail(userDTO.getEmail() == null ? user.getEmail() : userDTO.getEmail());
//        user.setFirstName(user.getFirstName() == null ? user.getFirstName() : userDTO.getFirstName());
//        user.setLastName(user.getLastName() == null ? user.getLastName() : userDTO.getLastName());
//        user.setGender(user.getGender() == null ? user.getGender() : userDTO.getGender());
//        user.setUpdatedAt(LocalDateTime.now());
//        user.setStatus(Status.UPDATED);
//
//        return mapper.convertValue( userRepository.save(user), userDTO.class);
        return null;

    }

    @Override
    public UserDTO get(String email) {
        return mapper.convertValue(getUser(email), UserDTO.class);
    }

    @Override
    public void delete(String email) {
        User user = getUser(email);
        user.setStatus(Status.DELETED);
        user.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user);
    }

    @Override
    public User getUser(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException("Пользователь с таким email не найден", HttpStatus.NOT_FOUND));
    }

    @Override
    public UserDTO addToBook(String email, String bookTitle) {
        Book book = bookService.getBook(bookTitle);
        User user = getUser(email);
        user.getBooks().add(book);
        User save = userRepository.save(user);
        UserDTO userDTO = mapper.convertValue(save, UserDTO.class);
        userDTO.setBookDTO(mapper.convertValue(book, BookDTO.class));
        return userDTO;

//    Для того что бы сократить часть кода, полей
//    @Override
//    public UserDTO createUser(UserDTO userDTO) {
//
////        User user = new User();
////        user.setAge(userDTO.getAge());
////        user.setFirstName(userDTO.getFirstName());
////        user.setLastName(userDTO.getLastName());
////        user.setGender(userDTO.getGender());
//
////        User user = mapper.convertValue(userDTO, User.class);
//////        Преобразование юзераДТО к юзеру, сконвертированный с трансферного объекта сущность юзер
//////        Маппер отрабатывает одинаковые поля и тип данных, если тип данных разный пишем вручную ниже
////
////        user.setCreatedAt(LocalDateTime.now());
//////        Прописывается что юзер создаётся сейчас
////
////        List<Book> books = userDTO.getBooks().stream()
////                .map(b -> {
////                    Book book = new Book();
////                    book.setBookTitle(b.getBookTitle());
////                    book.setBookCondition(b.getBookCondition());
////                    book.setAuthorsCount(b.getAuthorsCount());
////                    try {
////                        book.setPublicationDate(LocalDate.parse(b.getPublicationDate()));
////                    } catch (Exception e) {
////                       log.error(e.getMessage());
////                       throw new RuntimeException(e);
////                    }
////                    return book;
////                })
////                .collect(Collectors.toList());
//////        Хотим создать объект нашего пользователя,
//////        преобразовали через маппер наш трансферный объект(ЮзерДТО) к нашей сущности юзер,
//////        далее берем все книги, которые есть у нашего трансферного объекта, через стрим мы
//////        раскладываем и собираем список книг и в конце мы устанавливаем юзеру все те книги которые мы
//////        получили из трансферного объекта
////
////        user.setBooks(books);
////
////        User save = userRepository.save(user);
//////      Сохраняем. сэйв принимает энтити(сущность) в данном случае юзера
//////        стандартный метод, который даёт возможность сохранения в базу
////
////        UserDTO result = mapper.convertValue(user, UserDTO.class);
////        List<BookDTO> booksDTO = user.getBooks().stream()
////                .map(b -> {
////
////                    BookDTO bookDTO = new BookDTO();
////                    bookDTO.setBookTitle(b.getBookTitle());
////                    bookDTO.setBookCondition(b.getBookCondition());
////                    bookDTO.setAuthorsCount(b.getAuthorsCount());
////                    bookDTO.setPublicationDate(String.valueOf(b.getPublicationDate()));
////                    return bookDTO;
////                })
////                .collect(Collectors.toList());
////
////        result.setBooks((booksDTO));
//
//
//        return null;
////        при return null не отображается в боди постмана ответ
//
////        Создаём юзера, сеттером добавляем ему значения, геттером из ДТО забираем
//    }
    }
}

