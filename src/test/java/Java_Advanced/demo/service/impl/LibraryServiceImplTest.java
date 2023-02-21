package Java_Advanced.demo.service.impl;

import Java_Advanced.demo.exceptions.CustomException;
import Java_Advanced.demo.model.dto.LibraryDTO;
import Java_Advanced.demo.model.entity.Library;
import Java_Advanced.demo.model.entity.User;
import Java_Advanced.demo.model.repository.LibraryRepository;
import Java_Advanced.demo.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;


import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LibraryServiceImplTest {

    @InjectMocks
    private LibraryServiceImpl libraryService;

//    @Mock
//    private UserService userService;

    @Mock
    private LibraryRepository libraryRepository;

    @Spy
    private ObjectMapper mapper;

    @Test
    public void createLibrary() {
        LibraryDTO libraryDTO = new LibraryDTO();
        libraryDTO.setLibraryName("Tower");

        when(libraryRepository.save(any(Library.class))).thenReturn(any(Library.class));

        LibraryDTO result = libraryService.create(libraryDTO);
        assertEquals(libraryDTO.getLibraryName() , result.getLibraryName());

    }

    @Test(expected = CustomException.class)
    public void create_exception() {
        LibraryDTO libraryDTO = new LibraryDTO();
        libraryDTO.setLibraryName("Tower");

        when(libraryRepository.findByLibraryName(anyString())).thenThrow(CustomException.class);
        libraryService.create(libraryDTO);
    }

    @Test
    public void update() {
    }

    @Test
    public void get() {
    }

    @Test
    public void delete() {
        Library library = new Library();
        library.setLibraryName("Tower");
        when(libraryRepository.findByLibraryName(anyString())).thenReturn(Optional.of(library));

        when(libraryRepository.save(any(Library.class))).thenReturn(any(Library.class));

//        verify(libraryRepository, times(1)).save(library);

        libraryService.delete("Tower");
    }

    @Test
    public void addToUser() {
        UserService userService = mock(UserService.class);

        User value = new User();
        value.setAge(25);

        when(userService.getUser(anyString())).thenReturn(value);
    }

    @Test
    public void getLibrary() {
    }
}