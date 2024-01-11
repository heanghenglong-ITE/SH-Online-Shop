package com.example.shonlineshop.Domain;

public class ActivityDomain {

    private int id;
    private String name;
    private String price;
    private String discount;
    private String afterprice;
    private String imageUrl;
    private String quantity;
    private String delivery;
    private String size;
    private String description;
    private String category;

    public ActivityDomain(int id, String name, String price, String discount, String afterprice, String imageUrl, String quantity, String delivery, String size, String description, String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.afterprice = afterprice;
        this.imageUrl = imageUrl;
        this.quantity = quantity;
        this.delivery = delivery;
        this.size = size;
        this.description = description;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String dicount) {
        this.discount = dicount;
    }

    public String getAfterprice() {
        return afterprice;
    }

    public void setAfterprice(String afterprice) {
        this.afterprice = afterprice;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
