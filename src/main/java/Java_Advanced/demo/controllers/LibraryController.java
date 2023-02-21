package Java_Advanced.demo.controllers;

import Java_Advanced.demo.model.dto.LibraryDTO;
import Java_Advanced.demo.service.impl.LibraryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/library")
@RequiredArgsConstructor
@Tag(name = "Библиотека")
public class LibraryController {

    private final LibraryService libraryService;

    @PostMapping
    public ResponseEntity<LibraryDTO> create(@RequestBody LibraryDTO libraryDTO) {

        return ResponseEntity.ok(libraryService.create(libraryDTO));
    }

    @PutMapping
    public ResponseEntity<LibraryDTO> update(@RequestBody LibraryDTO libraryDTO) {

        return ResponseEntity.ok(libraryService.update(libraryDTO));
    }

    @GetMapping
    public ResponseEntity<LibraryDTO> get(@RequestParam String libraryName) {

        return ResponseEntity.ok(libraryService.get(libraryName));
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> delete(@RequestParam String libraryName) {
        libraryService.delete(libraryName);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/addtoUser")
    @Operation(summary = "Назначение библиотеке пользователя")
    public ResponseEntity<LibraryDTO> addToUser(@RequestParam String name, @RequestParam String email) {
        return ResponseEntity.ok(libraryService.addToUser(name, email));
    }
}

