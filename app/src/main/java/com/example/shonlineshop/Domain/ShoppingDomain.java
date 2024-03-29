package com.example.shonlineshop.Domain;

import com.google.gson.annotations.SerializedName;

public class ShoppingDomain implements java.io.Serializable{

    private String productName;
    private String productPrice;

    @SerializedName("imageUrl")
    private String imageUrl;

    public ShoppingDomain(String productName, String productPrice, String imageUrl) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.imageUrl = imageUrl;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
