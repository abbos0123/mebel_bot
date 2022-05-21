package uz.project.models;

import javax.persistence.*;

@Entity
@Table(name = "special_categories")
public class SpecialCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "product_category")
    @Enumerated(EnumType.STRING)
    private ProductCategory productCategory;
    @Column(name = "product_name")
    private String name;
    @Column(name = "product_description")
    private String description;

    public SpecialCategory() {
    }

    public SpecialCategory(ProductCategory productCategory, String name, String description) {
        this.productCategory = productCategory;
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
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

    @Override
    public String toString() {
        return "SpecialCategory{" +
                "id=" + id +
                ", productCategory=" + productCategory +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
