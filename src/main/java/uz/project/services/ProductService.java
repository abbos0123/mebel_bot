package uz.project.services;

import org.springframework.stereotype.Service;
import uz.project.models.Product;
import uz.project.models.ProductCategory;
import uz.project.models.SpecialCategory;
import uz.project.repositories.ProductRepository;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public Product saveProduct(Product product) {
        if (product == null) {
            return null;
        }

        return productRepository.save(product);
    }


    public Product getProductById(Long id) {
        if (!productRepository.existsById(id))
            return null;

        return productRepository.findProductById(id);
    }


    public boolean deleteProduct(Long id) {
        if (!productRepository.existsById(id))
            return false;

        try {
            var product = getProductById(id);
            productRepository.delete(product);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public Product updateProduct(Product product) {
        if (product == null) {
            return null;
        }

        return productRepository.save(product);
    }


    public boolean doesExistProduct(Long id){
        return productRepository.existsById(id);
    }


    public List<Product> findAllProductWithName(String name){
        return productRepository.findAllByNameContainingIgnoreCase(name);
    }


    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }


    public List<Product> getAllProductsOfSpecialCategories(SpecialCategory specialCategory){
        return productRepository.findAllByProductSpecialCategory(specialCategory);
    }


    public List<Product> getAllProductsOfMainCategories(ProductCategory productCategory){
        return productRepository.findAllByProductCategory(productCategory);
    }
}
