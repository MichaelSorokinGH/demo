package Java_Advanced.demo.controllers;

import Java_Advanced.demo.model.dto.AuthorDTO;
import Java_Advanced.demo.model.dto.BookDTO;
import Java_Advanced.demo.service.impl.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
@Tag(name = "Авторы")
public class AuthorController {

    private final AuthorService authorService;

    @PostMapping
    public ResponseEntity<AuthorDTO> createAuthor(@RequestBody AuthorDTO authorDTO) {

        return ResponseEntity.ok(authorService.create(authorDTO));
//        ResponseEntity обёртка передающая статус@PostMapping
    }

    @PutMapping
    public ResponseEntity<AuthorDTO> updateAuthor(@RequestBody AuthorDTO authorDTO) {

        return ResponseEntity.ok(authorService.update(authorDTO));
    }

    @GetMapping
    public ResponseEntity<AuthorDTO> getAuthor(@RequestParam Long id) {

        return ResponseEntity.ok(authorService.get(id));
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteAuthor(@RequestParam Long id) {
        authorService.delete(id);
        return ResponseEntity.ok().build();
    }

//    @PostMapping("/bookOwner")
//    public ResponseEntity<BookDTOResponse> addToAuthor(@RequestParam String bookTitle, @RequestParam String email) {
////        метод добавления книге автора
//
//        return ResponseEntity.ok(bookService.addToUser(bookTitle, email));

    @PostMapping("/addtobook")
    @Operation(summary = "Назначение автору библиотеки")
    public ResponseEntity<AuthorDTO> addToBook(@RequestParam Long id, @RequestParam String bookTitle) {
        return ResponseEntity.ok(authorService.addToBook(id, bookTitle));
    }
}
