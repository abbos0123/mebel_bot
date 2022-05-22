package uz.project.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany
    @JoinColumn(name = "products")
    private List<Product> products;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "date")
    private LocalDate date;

    @OneToOne
    @JoinColumn(name = "location")
    private Location location;

    @OneToOne
    @JoinColumn(name = "location_image")
    private FileStorage locationImage;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_type")
    private OrderType orderType;

    @Column(name = "total_price")
    private Double totalPrice;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "deadline")
    private LocalDate deadline;

    public Order() {
    }

    public Order(String name, List<Product> productsId, Long userId, LocalDate date, Location location, FileStorage locationImage, OrderType orderType, LocalDate deadline) {
        this.name = name;
        this.products = productsId;
        this.userId = userId;
        this.date = date;
        this.location = location;
        this.locationImage = locationImage;
        this.orderType = orderType;
        this.deadline = deadline;
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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> productsId) {
        this.products = productsId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public FileStorage getLocationImage() {
        return locationImage;
    }

    public void setLocationImage(FileStorage locationImage) {
        this.locationImage = locationImage;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", productsId=" + products +
                ", userId=" + userId +
                ", date=" + date +
                ", location=" + location +
                ", locationImage=" + locationImage +
                ", orderType=" + orderType +
                ", totalPrice=" + totalPrice +
                ", deadline=" + deadline +
                '}';
    }
}
