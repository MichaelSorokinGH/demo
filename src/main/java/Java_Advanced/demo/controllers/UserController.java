package Java_Advanced.demo.controllers;

import Java_Advanced.demo.model.dto.LibraryDTO;
import Java_Advanced.demo.model.dto.UserDTO;
import Java_Advanced.demo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "Пользователи")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO userDTO) {
//        Контроллер вернёт ЮзерДТО когда мы будем сохранять пользователя,
//        реквест боди для указания Юзер ДТО передаётся в боди
        return ResponseEntity.ok(userService.create(userDTO));
//        Передаём Юзер Дто как метод
    }

    @PutMapping
    public ResponseEntity<UserDTO> update(@RequestBody UserDTO userDTO) {

        return ResponseEntity.ok(userService.update(userDTO));
    }

    @GetMapping
    public ResponseEntity<UserDTO> get(@RequestParam String email) {

        return ResponseEntity.ok(userService.get(email));
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> delete(@RequestParam String email) {
        userService.delete(email);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/addtoBook")
    @Operation(summary = "Назначение пользователю книг")
    public ResponseEntity<UserDTO> addToBook(@RequestParam String email, @RequestParam String bookTitle) {
        return ResponseEntity.ok(userService.addToBook(email, bookTitle));
    }
}
