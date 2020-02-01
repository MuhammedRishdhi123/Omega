package com.example.omega.Model;

import com.orm.SugarRecord;

import java.util.ArrayList;

public class cart extends SugarRecord<cart> {
    private product product;
    private Long customerId;
    private int quantity;
    private customer customer;
    private String status;
    private double total;

    public cart() {
    }

    public cart(com.example.omega.Model.product product, Long customerId, int quantity, com.example.omega.Model.customer customer, String status, double total) {
        this.product = product;
        this.customerId = customerId;
        this.quantity = quantity;
        this.customer = customer;
        this.status = status;
        this.total = total;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public product getProducts() {
        return product;
    }

    public void setProducts(product product) {
        this.product = product;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public customer getCustomer() {
        return customer;
    }

    public void setCustomer(customer customer) {
        this.customer = customer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
