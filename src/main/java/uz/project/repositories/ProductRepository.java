package uz.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.project.models.Product;
import uz.project.models.ProductCategory;
import uz.project.models.SpecialCategory;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findProductById(Long id);

    Product findProductByName(String name);

    boolean existsByName(String name);

    boolean existsById(Long id);

    List<Product> findAllByProductCategory(ProductCategory productCategory);

    List<Product> findAllByProductSpecialCategory(SpecialCategory specialCategory);

    List<Product> findAllByNameContainingIgnoreCase(String name);
}
