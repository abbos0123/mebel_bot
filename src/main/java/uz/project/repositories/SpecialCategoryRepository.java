package uz.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.project.models.ProductCategory;
import uz.project.models.SpecialCategory;
import uz.project.models.User;

import java.util.List;

@Repository
public interface SpecialCategoryRepository extends JpaRepository<SpecialCategory, Long> {

    SpecialCategory findSpecialCategoryById(Long id);

    SpecialCategory findSpecialCategoryByName(String name);

    boolean existsSpecialCategoryById(Long id);

    boolean existsSpecialCategoryByName(String name);

    List<SpecialCategory> findSpecialCategoriesByProductCategory(ProductCategory productCategory);

    List<SpecialCategory> findAllByNameContainingIgnoreCase(String username);
}
