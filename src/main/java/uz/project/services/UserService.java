package uz.project.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.project.models.Student;
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

    public Student getUserById(Integer id) {
        var student = new Student();
        student.setAge(21);
        student.setName("Abbos");
        return student;
    }

    public User getUserByUsername(String username) {
        if (username == null || username.isEmpty())
            return null;

        if (userRepository.existsUserByUsername(username)) {
            return userRepository.findUserByUsername(username);
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

    public User update(User user, boolean isPasswordChange) {
        if (isPasswordChange)
            user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    public List<User> getAllUsersByName(String name) {
        if (name == null || name.equals(""))
            return getAllUsers();

        var users = userRepository.findAllByUsernameContainingIgnoreCase(name);
        return users;
    }

}
