package com.example.shonlineshop.Domain;

public class ActivityDomain {

    private int id;
    private String name;
    private String price;
    private String discount;
    private String afterprice;
    private String imageUrl;
    private String size;
    private String description;
    private String category;

    public ActivityDomain(int id, String name, String price, String discount, String afterprice, String imageUrl, String size, String description, String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.afterprice = afterprice;
        this.imageUrl = imageUrl;
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
