package uz.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.project.models.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserById(Long id);
    User findUserByUsername(String username);

    boolean existsUserById(Long id);

    boolean existsUserByUsername(String username);


    List<User> findAllByUsernameContainingIgnoreCase(String username);
}
