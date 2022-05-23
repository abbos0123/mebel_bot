package uz.project.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "age")
    private int age;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "second_phone_number")
    private String secondPhoneNumber;

    @Column(name = "description")
    private String description;

    @Column(name = "chat_id")
    private Long chatId;

    @Column(name = "workplace")
    private String workPlace;

    @Column(name = "position")
    private String position;

    @Column(name = "salary")
    private Double salary;

    @Enumerated(EnumType.STRING)
    @Column(name = "language")
    private Language language;

    @OneToOne
    @JoinColumn(name = "passport_image")
    private FileStorage passwordScannerImage;

    @OneToOne
    @JoinColumn(name = "passport_back_image")
    private FileStorage passwordScannerImageBack;

    @OneToOne
    @JoinColumn(name = "passport_owner_image")
    private FileStorage passwordAndOwnerImage;


    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_orders",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "order_id"))
    private List<Order> orders;


    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_basket_products",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<Product> basketProducts;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Role role;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "card")
    private Card card;
    public User() {
        this.id = -1L;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSecondPhoneNumber() {
        return secondPhoneNumber;
    }

    public void setSecondPhoneNumber(String secondPhoneNumber) {
        this.secondPhoneNumber = secondPhoneNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public String getWorkPlace() {
        return workPlace;
    }

    public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public FileStorage getPasswordScannerImage() {
        return passwordScannerImage;
    }

    public void setPasswordScannerImage(FileStorage passwordScannerImage) {
        this.passwordScannerImage = passwordScannerImage;
    }

    public FileStorage getPasswordScannerImageBack() {
        return passwordScannerImageBack;
    }

    public void setPasswordScannerImageBack(FileStorage passwordScannerImageBack) {
        this.passwordScannerImageBack = passwordScannerImageBack;
    }

    public FileStorage getPasswordAndOwnerImage() {
        return passwordAndOwnerImage;
    }

    public void setPasswordAndOwnerImage(FileStorage passwordAndOwnerImage) {
        this.passwordAndOwnerImage = passwordAndOwnerImage;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public Set<Product> getBasketProducts() {
        return basketProducts;
    }

    public void setBasketProducts(Set<Product> basketProducts) {
        this.basketProducts = basketProducts;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public boolean addOrder(Order order){
        if (order == null )
            return false;

        if (orders == null || orders.isEmpty())
            orders = new ArrayList<>();

        return orders.add(order);
    }

    public boolean addProductToBasket(Product product){
        if (product == null )
            return false;

        if (basketProducts == null || basketProducts.isEmpty())
            basketProducts = new HashSet<>();

        return basketProducts.add(product);
    }

    public boolean removeProductFromBasket(Product product){
        if (product == null )
            return false;

        if (basketProducts == null || basketProducts.isEmpty())
            basketProducts = new HashSet<>();

        return basketProducts.remove(product);
    }
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", secondPhoneNumber='" + secondPhoneNumber + '\'' +
                ", description='" + description + '\'' +
                ", chatId=" + chatId +
                ", workPlace='" + workPlace + '\'' +
                ", position='" + position + '\'' +
                ", salary=" + salary +
                ", language=" + language +
                ", passwordScannerImage=" + passwordScannerImage +
                ", passwordScannerImageBack=" + passwordScannerImageBack +
                ", passwordAndOwnerImage=" + passwordAndOwnerImage +
                ", orders=" + orders +
                ", basketProducts=" + basketProducts +
                ", role=" + role +
                ", card=" + card +
                '}';
    }
}
