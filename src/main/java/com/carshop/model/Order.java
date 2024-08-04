package com.carshop.model;

import java.time.LocalDate;

public class Order {
    private int id;
    private Car car;
    private User customer;
    private String status;
    private LocalDate date;

    public Order(int id, Car car, User customer, String status, LocalDate date) {
        this.id = id;
        this.car = car;
        this.customer = customer;
        this.status = status;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public Car getCar() {
        return car;
    }

    public User getCustomer() {
        return customer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", car=" + car +
                ", customer=" + customer.getUsername() +
                ", status='" + status + '\'' +
                ", date=" + date +
                '}';
    }
}
