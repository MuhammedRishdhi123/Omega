package com.example.omega.Model;

import com.orm.SugarRecord;

public class product extends SugarRecord<product> {
    private String productName,productCategory,productDescription,imageUrl,fullDescription;

    private double productPrice;
    private int stock;

    public product() {
    }

    public product(String productName, String productCategory, String productDescription, String imageUrl, double productPrice, int stock,String fullDescription) {
        this.productName = productName;
        this.productCategory = productCategory;
        this.productDescription = productDescription;
        this.imageUrl = imageUrl;
        this.productPrice = productPrice;
        this.stock = stock;
        this.fullDescription=fullDescription;
    }

    public String getFullDescription() {
        return fullDescription;
    }

    public void setFullDescription(String fullDescription) {
        this.fullDescription = fullDescription;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }
}
