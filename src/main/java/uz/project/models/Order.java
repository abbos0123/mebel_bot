package uz.project.models;

public class Order {

    private static Long lastId = 0L;
    private Long id;
    private String name;
    private String desc;

    public Order() {
        id = lastId;
        lastId++;
    }

    public Order(String name, String desc) {
        this.name = name;
        this.desc = desc;
        id = lastId;
        lastId++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
