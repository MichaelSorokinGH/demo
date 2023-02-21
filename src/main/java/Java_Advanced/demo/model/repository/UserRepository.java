package Java_Advanced.demo.model.repository;

import Java_Advanced.demo.model.entity.Book;
import Java_Advanced.demo.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

}
