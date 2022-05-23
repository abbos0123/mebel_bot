package uz.project.services;

import org.springframework.stereotype.Service;
import uz.project.models.ProductCategory;
import uz.project.models.SpecialCategory;
import uz.project.repositories.SpecialCategoryRepository;

import java.util.List;

@Service
public class SpecialCategoryService {

    private final SpecialCategoryRepository specialCategoryRepository;

    public SpecialCategoryService(SpecialCategoryRepository specialCategoryRepository) {
        this.specialCategoryRepository = specialCategoryRepository;
    }


    public SpecialCategory saveOrUpdate(SpecialCategory specialCategory) {
        return specialCategoryRepository.save(specialCategory);
    }

    public List<SpecialCategory> getAllSpecialCategories() {
        return specialCategoryRepository.findAll();
    }

    public SpecialCategory getSpecialCategoryByID(Long id) {
        if (id == null || id < 0)
            return null;

        if (specialCategoryRepository.existsSpecialCategoryById(id)) {
            return specialCategoryRepository.findSpecialCategoryById(id);
        }

        return null;
    }


    public SpecialCategory getSpecialCategoryBYName(String username) {
        if (username == null || username.isEmpty())
            return null;

        if (specialCategoryRepository.existsSpecialCategoryByName(username)) {
            return specialCategoryRepository.findSpecialCategoryByName(username);
        }

        return null;
    }

    public String delete(Long id) {
        SpecialCategory specialCategory = specialCategoryRepository.findSpecialCategoryById(id);

        specialCategoryRepository.delete(specialCategory);

        return specialCategory.getName() + " is deleted!";
    }


    public boolean doesSpecialCategoryExist(String name) {
        return specialCategoryRepository.existsSpecialCategoryByName(name);
    }


    public boolean doesSpecialCategoryExist(Long id) {
        return specialCategoryRepository.existsSpecialCategoryById(id);
    }


    public SpecialCategory update(SpecialCategory specialCategory) {
        return specialCategoryRepository.save(specialCategory);
    }

    public List<SpecialCategory> getAllSpecialCategoriesByName(String name) {
        if (name == null || name.equals(""))
            return getAllSpecialCategories();

        return specialCategoryRepository.findAllByNameContainingIgnoreCase(name);
    }

    public List<SpecialCategory> getAllSpecialCategoriesByMainCategory(ProductCategory productCategory) {
        if (productCategory == null)
            return getAllSpecialCategories();

        return   specialCategoryRepository.findSpecialCategoriesByProductCategory(productCategory);
    }

}
