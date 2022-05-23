package uz.project.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.project.models.User;
import uz.project.repositories.UserRepository;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public User saveOrUpdate(User user) {

        return userRepository.save(user);
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    public User getUserById(Long id) {
        if (id == null || id < 0)
            return null;

        if (userRepository.existsUserById(id)) {
            return userRepository.findUserById(id);
        }

        return null;
    }


    public User getUserByChatId(Long chatId) {
        if (chatId == null || chatId < 0)
            return null;

        if (userRepository.existsUserByChatId(chatId)) {
            return userRepository.findUserByChatId(chatId);
        }

        return null;
    }


    public User getUserByUsername(String username) {
        if (username == null || username.isEmpty())
            return null;

        if (userRepository.existsUserByUsername(username)) {
            return userRepository.findUserByUsername(username);
        }

        return null;
    }


    public User getUserByPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isEmpty())
            return null;

        if (userRepository.existsUserByPhoneNumber(phoneNumber)) {
            return userRepository.findUserByPhoneNumber(phoneNumber);
        }

        return null;
    }


    public String delete(Long id) {
        User user = userRepository.findUserById(id);
        userRepository.delete(user);

        return user.getUsername() + " is deleted!";
    }


    public boolean doesUserExist(String username) {
        return userRepository.existsUserByUsername(username);
    }


    public boolean doesUserExist(Long id) {
        return userRepository.existsUserById(id);
    }


    public boolean doesUserExistByChatId(Long id) {
        return userRepository.existsUserByChatId(id);
    }


    public boolean doesUserExistByPhoneNumber(String phoneNumber) {
        return userRepository.existsUserByPhoneNumber(phoneNumber);
    }


    public User update(User user, boolean isPasswordChange) {
        if (isPasswordChange)
            user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    public List<User> getAllUsersByName(String name) {
        if (name == null || name.equals(""))
            return getAllUsers();

        return userRepository.findAllByUsernameContainingIgnoreCase(name);
    }
}
