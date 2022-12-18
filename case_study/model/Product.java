package case_study.model;

import java.time.Instant;


public class Product {//Single Responsibility Principle (SOLID)
    private Long id;
    private String name;
    private Double price;
    private Long table;
    private Integer quantity;
    private String description;
    private Instant createdAt;
    private Instant updatedAt;


    public Product(long id, String name, double price, int quantity, long table, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.table = table;
        this.description = description;
    }

    public Product(long id, String name, double price, int quantity, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
    }

    public Product(Long id, String name, Double price, Integer quantity, String description, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Product() {

    }

    public static Product parse(String record) {
        String[] fields = record.split(",");
        long id = Long.parseLong(fields[0]);
        String title = fields[1];
        double price = Double.parseDouble(fields[2]);
        int quantity = Integer.parseInt(fields[3]);
        String description = fields[4];
        Instant createdAt = Instant.parse(fields[5]);
        String temp = fields[6];
        Instant updatedAt = null;
        if (temp != null && !temp.equals("null")) updatedAt = Instant.parse(temp);
        return new Product(id, title, price, quantity, description, createdAt, updatedAt);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTable() {
        return table;
    }

    public void setTable(Long table) {
        this.table = table;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }


    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s,%s,%s,",
                id,
                name,
                price,
                quantity,
                description,
                createdAt,
                updatedAt);
    }

}
