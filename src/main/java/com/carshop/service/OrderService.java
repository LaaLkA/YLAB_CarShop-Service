package com.carshop.service;

import com.carshop.model.Car;
import com.carshop.model.Order;
import com.carshop.model.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderService {
    private Map<Integer, Order> orders = new HashMap<>();
    private int nextId = 1;

    public List<Order> listOrders() {
        return new ArrayList<>(orders.values());
    }

    public boolean createOrder(Car car, User customer, String status, LocalDate date) {
        Order order = new Order(nextId++, car, customer, status, date);
        orders.put(order.getId(), order);
        return true;
    }

    public boolean editOrder(int id, String status) {
        Order order = orders.get(id);
        if (order != null) {
            order.setStatus(status);
            return true;
        }
        return false;
    }

    public boolean cancelOrder(int id) {
        return orders.remove(id) != null;
    }
}
