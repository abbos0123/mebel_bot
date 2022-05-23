package uz.project.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.project.models.*;
import uz.project.services.SpecialCategoryService;

import javax.ws.rs.NotFoundException;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/category")
public class SpecialCategoryController {
    private final SpecialCategoryService specialCategoryService;

    public SpecialCategoryController(SpecialCategoryService specialCategoryService) {
        this.specialCategoryService = specialCategoryService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> saveCategory(@RequestBody SpecialCategory specialCategory) throws Exception {
        try {
            var specialCategorySaved = specialCategoryService.saveOrUpdate(specialCategory);
            return ResponseEntity.ok(specialCategorySaved);
        } catch (Exception e) {
            throw new Exception("Something wrong with your insertion !");
        }
    }


    @PutMapping("/update")
    public ResponseEntity<?> updateSpecialCategory(@RequestBody SpecialCategory specialCategory) throws Exception {
        if (specialCategory == null || specialCategory.getProductCategory() == null || specialCategory.getName() == null || specialCategory.getName().isEmpty())
            throw new Exception("Please fill all fields !");

        return ResponseEntity.ok(specialCategoryService.update(specialCategory));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSpecialCategory(@PathVariable("id") Long id) throws NotFoundException {
        if (!specialCategoryService.doesSpecialCategoryExist(id))
            throw new NotFoundException("This category  does not exist!");

        return ResponseEntity.ok(new CustomResponse(HttpStatus.OK.value(), "Category is deleted successfully!"));
    }


    @GetMapping("/{id}")
    public ResponseEntity<SpecialCategory> getSpecialById(@PathVariable("id") Long id) throws NotFoundException {
        if (!specialCategoryService.doesSpecialCategoryExist(id))
            throw new NotFoundException("This user does not exist!");

        return new ResponseEntity<>(specialCategoryService.getSpecialCategoryByID(id), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<?> getAllSpecialCategoriesByName(@RequestParam("name") String name) {
        try {
            var list = specialCategoryService.getAllSpecialCategoriesByName(name);
            return ResponseEntity.ok(list);

        } catch (Exception e) {
            return ResponseEntity.ok(new ArrayList<>());
        }
    }


    @GetMapping("/all")
    public ResponseEntity<?> getAllSpecialCategories() {
        try {
            var list = specialCategoryService.getAllSpecialCategories();
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            return ResponseEntity.ok(new ArrayList<>());
        }
    }

    @GetMapping("/all/{category}")
    public ResponseEntity<?> getAllSpecialCategories(@PathVariable ProductCategory category) {
        try {
            var list = specialCategoryService.getAllSpecialCategoriesByMainCategory(category);
            return ResponseEntity.ok(list);

        } catch (Exception e) {
            return ResponseEntity.ok(new ArrayList<>());
        }
    }
}
