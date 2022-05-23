package uz.project.controllers;

import org.springframework.web.bind.annotation.RestController;
import uz.project.models.*;
import uz.project.services.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
public class BotController {

    private final StudentService studentService;
    private final ProductService productService;
    private final OrderService orderService;
    private final SpecialCategoryService specialCategoryService;
    private final UserService userService;

    private final LocationService locationService;


    public BotController(StudentService studentService, ProductService productService, OrderService orderService, SpecialCategoryService specialCategoryService, UserService userService, LocationService locationService) {
        this.studentService = studentService;
        this.productService = productService;
        this.orderService = orderService;
        this.specialCategoryService = specialCategoryService;
        this.userService = userService;
        this.locationService = locationService;
    }

    public List<Order> getAllOrdersOfUser(Long userId){
        if (!orderService.doesOrderExistWithUserId(userId))
            return null;

      return  orderService.getAllOrdersOfUser(userId);

    }
    public Order getOrderWithId(Long id){
        if (orderService.doesOrderExist(id)){
          return   orderService.getOrderById(id);
        }

        return null;
    }
    public Product getProductWithID(Long id) {
        if (productService.doesExistProduct(id))
            return productService.getProductById(id);
        else
            return null;
    }

    public User getUserWithId(Long id) {
        return userService.getUserById(id);
    }

    public SpecialCategory getSpecialCategoryWithID(Long id) {
        if (!specialCategoryService.doesSpecialCategoryExist(id))
            return null;

        return specialCategoryService.getSpecialCategoryByID(id);
    }

    public List<SpecialCategory> getAllSubCategories(String furniture) {
        var category = ProductCategory.valueOf(furniture);
        List<SpecialCategory> subCategories = specialCategoryService.getAllSpecialCategoriesByMainCategory(category);

        if (subCategories == null)
            return new ArrayList<>();

        return subCategories;
    }


    public List<Product> getAllProductsOfSubcategory(SpecialCategory specialCategory) {
        if (specialCategory == null)
            return new ArrayList<>();
        var list = productService.getAllProductsOfSpecialCategories(specialCategory);

        if (list == null)
            return new ArrayList<>();

        return list;
    }


    public User getUserByChatId(Long chatId) {
        var user = userService.getUserByChatId(chatId);

        if (user == null)
            return new User();

        return user;
    }

    public boolean doesUserExistByChatId(Long chatId) {
        try {
            return userService.doesUserExistByChatId(chatId);
        } catch (Exception e) {
            return false;
        }
    }

    public User saveUser(User user) {
        if (user == null)
            return new User();

        return userService.saveOrUpdate(user);
    }
      public Order saveOrder(Order order) {
        if (order == null)
          return null;

        return orderService.saveOrder(order);
    }

    public Location saveLocation(Location location){
        return locationService.saveLocation(location);
    }
}
