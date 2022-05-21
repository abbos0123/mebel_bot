package uz.project.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Double price;

    @Enumerated(EnumType.STRING)
    @Column(name = "product_category")
    private ProductCategory productCategory;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "product_special_category",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "sp_category_id")
    )
    private SpecialCategory productSpecialCategory;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "product_images_table",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "file_id"))
    private List<FileStorage> imageList;

    public Product() {
    }

    public Product(String name, long id) {
        this.name = name;
        this.id = id;
    }

    public Product(String name, String description, Double price, ProductCategory productCategory, List<FileStorage> imageList) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.productCategory = productCategory;
        this.imageList = imageList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public List<FileStorage> getImageList() {
        return imageList;
    }

    public void setImageList(List<FileStorage> imageList) {
        this.imageList = imageList;
    }

    public List<FileStorage> addImage(FileStorage imageFile) {
        if (imageList == null || imageList.isEmpty()) {
            imageList = new ArrayList<>();
        }

        imageList.add(imageFile);

        return imageList;
    }

    public boolean removeImage(int position) {
        if (imageList == null || imageList.isEmpty() || position >= imageList.size()) {
            return false;
        }

        imageList.remove(position);
        return true;
    }

    public boolean removeImage(FileStorage imageFile) {
        if (imageList == null || imageList.isEmpty()) {
            return false;
        }

        return imageList.remove(imageFile);
    }

    public SpecialCategory getProductSpecialCategory() {
        return productSpecialCategory;
    }

    public void setProductSpecialCategory(SpecialCategory productSpecialCategory) {
        this.productSpecialCategory = productSpecialCategory;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", productCategory=" + productCategory +
                ", productSpecialCategory=" + productSpecialCategory +
                ", imageList=" + imageList +
                '}';
    }
}

