package uz.project.controllers;

import org.springframework.web.bind.annotation.RestController;
import uz.project.models.Student;
import uz.project.services.StudentService;

import java.util.Objects;

@RestController
public class BotController {

    private final StudentService studentService;

    public BotController(StudentService studentService) {
        this.studentService = studentService;
    }

    public Student getStudentWithId(Long id) {
        var student = studentService.getStudent(id);

        return Objects.requireNonNullElseGet(student, Student::new);
    }
}
