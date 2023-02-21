package Java_Advanced.demo.service.impl;

import Java_Advanced.demo.model.dto.LibraryDTO;
import Java_Advanced.demo.model.entity.Library;

public interface LibraryService {

    LibraryDTO create(LibraryDTO libraryDTO);

    LibraryDTO update(LibraryDTO libraryDTO);

    LibraryDTO get(String libraryName);

    void delete(String libraryName);

    Library getLibrary(String libraryName);

    LibraryDTO addToUser(String libraryName, String email);

}
