package com.carshop;

import com.carshop.model.Car;
import com.carshop.model.Order;
import com.carshop.model.User;
import com.carshop.service.CarService;
import com.carshop.service.OrderService;
import com.carshop.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class OrderServiceTest {
    private OrderService orderService;
    private CarService carService;
    private UserService userService;

    @BeforeEach
    public void setUp() {
        orderService = new OrderService();
        carService = new CarService();
        userService = new UserService();
        userService.register("client", "pass", "client");
    }

    @Test
    public void testCreateOrder() {
        carService.addCar("Toyota", "Camry", 2020, 30000, "New");
        Car car = carService.listCars().get(0);
        User client = userService.login("client", "pass");
        assertTrue(orderService.createOrder(car, client, "Pending", LocalDate.now()));
        assertEquals(1, orderService.listOrders().size());
    }

    @Test
    public void testEditOrder() {
        carService.addCar("Toyota", "Camry", 2020, 30000, "New");
        Car car = carService.listCars().get(0);
        User client = userService.login("client", "pass");
        orderService.createOrder(car, client, "Pending", LocalDate.now());
        Order order = orderService.listOrders().get(0);
        assertTrue(orderService.editOrder(order.getId(), "Completed"));
        assertEquals("Completed", orderService.listOrders().get(0).getStatus());
    }

    @Test
    public void testCancelOrder() {
        carService.addCar("Toyota", "Camry", 2020, 30000, "New");
        Car car = carService.listCars().get(0);
        User client = userService.login("client", "pass");
        orderService.createOrder(car, client, "Pending", LocalDate.now());
        Order order = orderService.listOrders().get(0);
        assertTrue(orderService.cancelOrder(order.getId()));
        assertTrue(orderService.listOrders().isEmpty());
    }
}
