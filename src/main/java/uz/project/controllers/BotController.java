package uz.project.controllers;

import org.springframework.web.bind.annotation.RestController;
import uz.project.models.*;
import uz.project.services.ProductService;
import uz.project.services.SpecialCategoryService;
import uz.project.services.StudentService;
import uz.project.services.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
public class BotController {

    private final StudentService studentService;
    private final ProductService productService;

    private final SpecialCategoryService specialCategoryService;

    private final UserService userService;

    public BotController(StudentService studentService, ProductService productService, SpecialCategoryService specialCategoryService, UserService userService) {
        this.studentService = studentService;
        this.productService = productService;
        this.specialCategoryService = specialCategoryService;
        this.userService = userService;
    }

    public Student getStudentWithId(Long id) {
        var student = studentService.getStudent(id);

        return Objects.requireNonNullElseGet(student, Student::new);
    }

    public Product addProduct(Product product) {
        if (product == null)
            return null;
        return productService.saveProduct(product);
    }

    public Product getProductWithID(Long id) {
        return productService.getProductById(id);
    }

    public User getUserWithId(Long id) {
        return userService.getUserById(id);
    }

    public List<SpecialCategory> getAllSubCategories(String furniture) {
        var category = ProductCategory.valueOf(furniture);
        List<SpecialCategory> subCategories = specialCategoryService.getAllSpecialCategoriesByMainCategory(category);

        if (subCategories == null)
            return new ArrayList<>();

        return subCategories;
    }
}
