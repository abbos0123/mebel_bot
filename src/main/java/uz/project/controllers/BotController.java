package uz.project.controllers;

import org.springframework.web.bind.annotation.RestController;
import uz.project.models.Product;
import uz.project.models.Student;
import uz.project.services.ProductService;
import uz.project.services.StudentService;

import java.util.Objects;

@RestController
public class BotController {

    private final StudentService studentService;
    private final ProductService productService;

    public BotController(StudentService studentService, ProductService productService) {
        this.studentService = studentService;
        this.productService = productService;
    }

    public Student getStudentWithId(Long id) {
        var student = studentService.getStudent(id);

        return Objects.requireNonNullElseGet(student, Student::new);
    }

    public Product addProduct(Product product){
        if (product == null)
            return null;
        return productService.saveProduct(product);
    }

    public Product getProductWithID(Long id){
        return productService.getProductById(id);
    }
}
