package uz.project.services;

import org.springframework.stereotype.Service;
import uz.project.models.Product;
import uz.project.repositories.ProductRepository;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    //Adding product to database
    public Product saveProduct(Product product) {
        if (product == null) {
            return null;
        }
        return productRepository.save(product);
    }

    //getting product by id
    public Product getProductById(Long id) {
        if (!productRepository.existsById(id))
            return null;
        return productRepository.findProductById(id);
    }

    //deleting  product
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

    // updating product
    public Product updateProduct(Product product) {
        if (product == null) {
            return null;
        }
        return productRepository.save(product);
    }

    // checking  existence with id
    public boolean doesExistProduct(Long id){
        return productRepository.existsById(id);
    }

    // check existence with name
    public List<Product> findAllProductWithName(String name){
        return productRepository.findAllByNameContainingIgnoreCase(name);
    }

    //getting all products
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
}
