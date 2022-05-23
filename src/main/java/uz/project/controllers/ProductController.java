package uz.project.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.project.models.CustomResponse;
import uz.project.models.Product;
import uz.project.models.ProductCategory;
import uz.project.services.FileService;
import uz.project.services.ProductService;
import uz.project.services.SpecialCategoryService;

import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService productService;
    private final SpecialCategoryService specialCategoryService;

    public ProductController(ProductService productService, SpecialCategoryService specialCategoryService) {
        this.productService = productService;
        this.specialCategoryService = specialCategoryService;
    }


    @PostMapping("/add_new_product")
    public ResponseEntity<Product> addingNewProduct(@RequestBody Product product) throws Exception {

        checkValidation(product);

        return ResponseEntity.ok(productService.saveProduct(product));
    }

    private void checkValidation(Product product) throws Exception {
        if (product.getName() == null || product.getPrice() == 0D || product.getProductCategory() == null)
            throw new Exception("Please fill all fields!");

    }


    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) throws NotFoundException {
        var product = productService.getProductById(id);

        if (product == null)
            throw new NotFoundException("This product does not exist !");

        return ResponseEntity.ok(product);
    }


    @GetMapping("/all/{name}")
    public ResponseEntity<List<Product>> findAllProductWithName(@PathVariable String name) {
        try {
            var list = productService.findAllProductWithName(name);

            if (list == null || list.isEmpty())
                ResponseEntity.ok(new ArrayList<>());
            return ResponseEntity.ok(list);

        } catch (Exception e) {
            return ResponseEntity.ok(new ArrayList<>());
        }
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CustomResponse> deleteProduct(@PathVariable Long id) throws Exception {
        if (id == null)
            throw new Exception("Please dont skip id!");

        if (productService.doesExistProduct(id) && productService.deleteProduct(id)) {
            return ResponseEntity.ok(new CustomResponse(HttpStatus.OK.value(), "Product is deleted successfully!"));
        } else {
            throw new NotFoundException("This product does not exist !");
        }
    }


    @PutMapping("/update")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) throws Exception {
        checkValidation(product);

        if (product.getId() == null || product.getId() == 0)
            return ResponseEntity.ok(productService.saveProduct(product));

        return ResponseEntity.ok(productService.updateProduct(product));
    }


    @GetMapping("/checking/{id}")
    public ResponseEntity<Boolean> doesProductExistWithId(@PathVariable Long id) throws Exception {
        if (id == null || id == 0)
            throw new Exception("Please do bot skip id!");

        return ResponseEntity.ok(productService.doesExistProduct(id));
    }


    @GetMapping("/all")
    public ResponseEntity<List<Product>> findAllProducts() {
        try {
            var list = productService.getAllProducts();

            if (list == null || list.isEmpty())
                ResponseEntity.ok(new ArrayList<>());

            return ResponseEntity.ok(list);
        } catch (Exception e) {
            return ResponseEntity.ok(new ArrayList<>());
        }
    }

    @GetMapping("/all/category")
    public ResponseEntity<List<Product>> findAllProductsOfCategory(@RequestParam(name = "category") String productCategoryName) throws Exception {
        var productCategory = ProductCategory.valueOf(productCategoryName);

        try {
            var list = productService.getAllProductsOfMainCategories(productCategory);

            if (list == null || list.isEmpty())
                ResponseEntity.ok(new ArrayList<>());
            return ResponseEntity.ok(list);

        } catch (Exception e) {
            return ResponseEntity.ok(new ArrayList<>());
        }
    }


    @GetMapping("/all/category/{special_category_id}")
    public ResponseEntity<List<Product>> findAllProductsOfCategory(@PathVariable Long special_category_id) {
        if (!specialCategoryService.doesSpecialCategoryExist(special_category_id)) {
            throw new NotFoundException("This category does not exist !");
        }
        try {
            var list = productService.getAllProductsOfSpecialCategories(specialCategoryService.getSpecialCategoryByID(special_category_id));
            if (list == null || list.isEmpty())
                ResponseEntity.ok(new ArrayList<>());
            return ResponseEntity.ok(list);

        } catch (Exception e) {
            return ResponseEntity.ok(new ArrayList<>());
        }
    }
}
