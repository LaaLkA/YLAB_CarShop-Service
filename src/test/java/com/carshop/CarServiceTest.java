package com.carshop;

import com.carshop.model.Car;
import com.carshop.service.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CarServiceTest {
    private CarService carService;

    @BeforeEach
    public void setUp() {
        carService = new CarService();
    }

    @Test
    public void testAddCar() {
        assertTrue(carService.addCar("Toyota", "Camry", 2020, 30000, "New"));
        assertEquals(1, carService.listCars().size());
    }

    @Test
    public void testEditCar() {
        carService.addCar("Toyota", "Camry", 2020, 30000, "New");
        Car car = carService.listCars().get(0);
        assertTrue(carService.editCar(car.getId(), "Honda", "Civic", 2019, 25000, "Used"));
        assertEquals("Honda", carService.listCars().get(0).getBrand());
    }

    @Test
    public void testDeleteCar() {
        carService.addCar("Toyota", "Camry", 2020, 30000, "New");
        Car car = carService.listCars().get(0);
        assertTrue(carService.deleteCar(car.getId()));
        assertTrue(carService.listCars().isEmpty());
    }
}
