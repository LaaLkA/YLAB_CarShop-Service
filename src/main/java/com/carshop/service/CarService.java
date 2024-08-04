package com.carshop.service;

import com.carshop.model.Car;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CarService {
    private Map<Integer, Car> cars = new HashMap<>();
    private int nextId = 1;

    public List<Car> listCars() {
        return new ArrayList<>(cars.values());
    }

    public boolean addCar(String brand, String model, int year, double price, String condition) {
        Car car = new Car(nextId++, brand, model, year, price, condition);
        cars.put(car.getId(), car);
        return true;
    }

    public boolean editCar(int id, String brand, String model, int year, double price, String condition) {
        Car car = cars.get(id);
        if (car != null) {
            car.setBrand(brand);
            car.setModel(model);
            car.setYear(year);
            car.setPrice(price);
            car.setCondition(condition);
            return true;
        }
        return false;
    }

    public boolean deleteCar(int id) {
        return cars.remove(id) != null;
    }

    public Car getCar(int id) {
        return cars.get(id);
    }
}
