package Java_Advanced.demo.controllers;

import Java_Advanced.demo.model.dto.BookDTO;
import Java_Advanced.demo.service.impl.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
@Tag(name = "Книги")
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<BookDTO> create(@RequestBody BookDTO bookDTO) {

        return ResponseEntity.ok(bookService.create(bookDTO));
//        ResponseEntity обёртка передающая статус@PostMapping
    }

    @PutMapping
    public ResponseEntity<BookDTO> update(@RequestBody BookDTO bookDTO) {

        return ResponseEntity.ok(bookService.update(bookDTO));
    }

    @GetMapping
    public ResponseEntity<BookDTO> get(@RequestParam String bookTitle) {

        return ResponseEntity.ok(bookService.get(bookTitle));
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> delete(@RequestParam String bookTitle) {
        bookService.delete(bookTitle);
        return ResponseEntity.ok().build();
    }

//    @PostMapping("/addtoauthor")
//    @Operation(summary = "Назначение книги автору")
//    public ResponseEntity<BookDTO> addToAuthor(@RequestParam String bookTitle, @RequestParam Long id) {
//
//
//        return ResponseEntity.ok(bookService.addToAuthor(bookTitle, id));

//        @RequestParam(required = false) - необязательный парматр, по дефолту тру.
//        @RequestParam(required = false, defaultValue = "some name") - установка значения по дефолту


    @GetMapping("/all")
    public ModelMap getAllBooks(@RequestParam(required = false, defaultValue = "1") Integer page,
                                @RequestParam(required = false, defaultValue = "10") Integer perPage,
                                @RequestParam(required = false, defaultValue = "bookTitle") String sort,
                                @RequestParam(required = false, defaultValue = "ASC") Sort.Direction order) {
        return bookService.getAllBooks(page, perPage, sort, order);
    }
}





