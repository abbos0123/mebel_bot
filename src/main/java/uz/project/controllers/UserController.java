package uz.project.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import uz.project.models.CustomResponse;
import uz.project.models.Role;
import uz.project.models.User;
import uz.project.services.UserService;
import uz.project.utilds.RegistrationException;

import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.Objects;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/client/register")
    public ResponseEntity<?> saveUser(@RequestBody User userInput) throws RegistrationException {
        var role = new Role();
        role.setName("ROLE_USER");

        var user = saveUser(userInput, role);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/admin/register")
    public ResponseEntity<?> saveAdmin(@RequestBody User inputUser) throws RegistrationException {
        var role = new Role();
        role.setName("ROLE_ADMIN");

        var user = saveUser(inputUser, role);
        return ResponseEntity.ok(user);
    }


    public User saveUser(User userInput, Role role) throws RegistrationException {
        checkValidation(userInput);
        userInput.setRole(role);
        userInput.setPassword(passwordEncoder.encode(userInput.getPassword()));

        return userService.saveOrUpdate(userInput);
    }


    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        checkValidation(user);
        var bool = !Objects.equals(user.getPassword(), userService.getUserById(user.getId()).getPassword());

        return ResponseEntity.ok(userService.update(user, bool));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) throws NotFoundException {
        if (!userService.doesUserExist(id))
            throw new NotFoundException("This user does not exist!");

        return ResponseEntity.ok(new CustomResponse(HttpStatus.OK.value(), "Item is deleted successfully!"));
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") Long id) throws NotFoundException {
        if (!userService.doesUserExist(id))
            throw new NotFoundException("This user does not exist!");

        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<?> getAllUserByName(@RequestParam("name") String name) {
        try {
            var list = userService.getAllUsersByName(name);
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            return ResponseEntity.ok(new ArrayList<>());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllUsers() {
        try {
            var list = userService.getAllUsers();
            return ResponseEntity.ok(list);

        } catch (Exception e) {
            return ResponseEntity.ok(new ArrayList<>());
        }
    }

    @GetMapping("/phone_number")
    public ResponseEntity<User> getUserByPhoneNumber(@RequestParam(name = "phoneNumber") String phoneNumber) throws NotFoundException {
        if (userService.doesUserExistByPhoneNumber(phoneNumber))
            return ResponseEntity.ok(userService.getUserByPhoneNumber(phoneNumber));
        else
            throw new NotFoundException("This user does not exist!");
    }


    @GetMapping("/chat_id")
    public ResponseEntity<User> getUserByChatId(@RequestParam(name = "chat_id") Long chatId) throws NotFoundException {
        if (userService.doesUserExistByChatId(chatId))
            return ResponseEntity.ok(userService.getUserByChatId(chatId));
        else
            throw new NotFoundException("This user does not exist!");
    }

    @GetMapping("/username")
    public ResponseEntity<User> getUserByUsername(@RequestParam(name = "username") String username) throws NotFoundException {
        if (userService.doesUserExist(username))
            return ResponseEntity.ok(userService.getUserByUsername(username));
        else
            throw new NotFoundException("This user does not exist!");
    }


    @GetMapping("/checkWithChatId")
    public ResponseEntity<Boolean> checkUserByChatId(@RequestParam(name = "chatId") Long chatId) throws Exception {
        try {
            return ResponseEntity.ok(userService.doesUserExistByChatId(chatId));
        } catch (Exception e) {
            throw new Exception("Something wrong with you !");
        }
    }


    @GetMapping("/checkWithId")
    public ResponseEntity<Boolean> checkUserById(@RequestParam(name = "id") Long id) throws Exception {
        try {
            return ResponseEntity.ok(userService.doesUserExist(id));
        } catch (Exception e) {
            throw new Exception("Something wrong with you !");
        }
    }


    @GetMapping("/checkWithPhoneNumber")
    public ResponseEntity<Boolean> checkUserByPhoneNumber(@RequestParam(name = "phoneNumber") String phoneNumber) throws Exception {
        try {
            return ResponseEntity.ok(userService.doesUserExistByPhoneNumber(phoneNumber));
        } catch (Exception e) {
            throw new Exception("Something wrong with you !");
        }
    }

    public void checkValidation(User userInput) throws RegistrationException {
        if (userInput.getUsername() == null || userInput.getUsername().isEmpty() || userInput.getPassword() == null || userInput.getPassword().isEmpty())
            throw new RegistrationException("Please fill all parameters!");

        if (checkWhereSingleWord(userInput.getUsername())) {
            throw new RegistrationException("Username can be only single word!");
        }

        if (checkWhereSingleWord(userInput.getPassword())) {
            throw new RegistrationException("Password can be only single word!");
        }

        if (!checkPasswordLength(userInput.getPassword())) {
            throw new RegistrationException("Your password should contain at least 4 characters!");
        }

        if (userService.doesUserExist(userInput.getUsername()) && userInput.getId() == null) {
            throw new RegistrationException("This username has already been taken. Please select another one!");
        }

    }

    private Boolean checkPasswordLength(String password) {
        return password.length() >= 4;
    }

    private boolean checkWhereSingleWord(String s) {
        if (s == null || s.isEmpty())
            return false;

        return s.trim().contains(" ");
    }

}
