package Java_Advanced.demo.model.repository;

import Java_Advanced.demo.model.entity.Book;
import Java_Advanced.demo.model.enums.BookCondition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
//    Типизировать указать с какой сущностью мы работаем и тип первичного ключа

    Optional<Book> findByBookTitle(String bookTitle);
//    Optional даёт возможность проверки на существование сущности

//    List<Book> findAllByBookCondition(BookCondition condition);
////    Поиск по определённым параметрам, в данном случае по состоянию книги
//
//    @Query("select book from Book book where book.bookCondition = :condition")
//    List<Book> getBooks(@Param("condition") BookCondition condition);
////    Явно указываем имя параметра
////    ВСЁ ЭТО HQL!
//
//    @Query(value = "select * from books where book.bookCondition = :condition", nativeQuery = true)
//    List<Book> getBooksNative(@Param("condition") BookCondition condition);
////    Нативный метод
}
