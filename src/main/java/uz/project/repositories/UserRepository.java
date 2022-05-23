package uz.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.project.models.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserById(Long id);

    User findUserByUsername(String username);

    User findUserByPhoneNumber(String phoneNumber);

    User findUserByChatId(Long chatId);
    
    boolean existsUserById(Long id);

    boolean existsUserByUsername(String username);

    boolean existsUserByChatId(Long chatId);

    boolean existsUserByPhoneNumber(String  phoneNumber);

    List<User> findAllByUsernameContainingIgnoreCase(String username);

}
