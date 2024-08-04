package com.carshop;

import com.carshop.model.Car;
import com.carshop.model.User;
import com.carshop.service.CarService;
import com.carshop.service.LogService;
import com.carshop.service.OrderService;
import com.carshop.service.UserService;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    private static UserService userService = new UserService();
    private static CarService carService = new CarService();
    private static OrderService orderService = new OrderService();
    private static LogService logService = new LogService();
    private static User currentUser;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (currentUser == null) {
                System.out.println("1. Register");
                System.out.println("2. Login");
                int choice = scanner.nextInt();
                scanner.nextLine();
                if (choice == 1) {
                    register(scanner);
                } else if (choice == 2) {
                    login(scanner);
                }
            } else {
                showMenu(scanner);
            }
        }
    }

    private static void register(Scanner scanner) {
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        System.out.print("Role (admin/manager/client): ");
        String role = scanner.nextLine();
        if (userService.register(username, password, role)) {
            System.out.println("Registration successful.");
        } else {
            System.out.println("Username already taken.");
        }
    }

    private static void login(Scanner scanner) {
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        currentUser = userService.login(username, password);
        if (currentUser != null) {
            logService.logAction(currentUser, "Login");
            System.out.println("Login successful.");
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    private static void showMenu(Scanner scanner) {
        System.out.println("1. Manage Cars");
        System.out.println("2. Manage Orders");
        System.out.println("3. View Users");
        System.out.println("4. View Logs");
        System.out.println("5. Logout");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1:
                manageCars(scanner);
                break;
            case 2:
                manageOrders(scanner);
                break;
            case 3:
                viewUsers(scanner);
                break;
            case 4:
                viewLogs(scanner);
                break;
            case 5:
                logService.logAction(currentUser, "Logout");
                currentUser = null;
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private static void manageCars(Scanner scanner) {
        System.out.println("1. List Cars");
        System.out.println("2. Add Car");
        System.out.println("3. Edit Car");
        System.out.println("4. Delete Car");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1:
                carService.listCars().forEach(System.out::println);
                break;
            case 2:
                System.out.print("Brand: ");
                String brand = scanner.nextLine();
                System.out.print("Model: ");
                String model = scanner.nextLine();
                System.out.print("Year: ");
                int year = scanner.nextInt();
                System.out.print("Price: ");
                double price = scanner.nextDouble();
                scanner.nextLine(); // consume newline
                System.out.print("Condition: ");
                String condition = scanner.nextLine();
                carService.addCar(brand, model, year, price, condition);
                break;
            case 3:
                System.out.print("Car ID: ");
                int id = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Brand: ");
                brand = scanner.nextLine();
                System.out.print("Model: ");
                model = scanner.nextLine();
                System.out.print("Year: ");
                year = scanner.nextInt();
                System.out.print("Price: ");
                price = scanner.nextDouble();
                scanner.nextLine();
                System.out.print("Condition: ");
                condition = scanner.nextLine();
                carService.editCar(id, brand, model, year, price, condition);
                break;
            case 4:
                System.out.print("Car ID: ");
                id = scanner.nextInt();
                carService.deleteCar(id);
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private static void manageOrders(Scanner scanner) {
        System.out.println("1. List Orders");
        System.out.println("2. Create Order");
        System.out.println("3. Edit Order");
        System.out.println("4. Cancel Order");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1:
                orderService.listOrders().forEach(System.out::println);
                break;
            case 2:
                System.out.print("Car ID: ");
                int carId = scanner.nextInt();
                scanner.nextLine();
                Car car = carService.getCar(carId);
                if (car == null) {
                    System.out.println("Invalid Car ID.");
                    return;
                }
                orderService.createOrder(car, currentUser, "Pending", LocalDate.now());
                break;
            case 3:
                System.out.print("Order ID: ");
                int orderId = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Status: ");
                String status = scanner.nextLine();
                orderService.editOrder(orderId, status);
                break;
            case 4:
                System.out.print("Order ID: ");
                orderId = scanner.nextInt();
                orderService.cancelOrder(orderId);
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private static void viewUsers(Scanner scanner) {
        userService.listUsers().forEach(System.out::println);
    }

    private static void viewLogs(Scanner scanner) {
        logService.listLogs().forEach(System.out::println);
        System.out.print("Export logs to file? (yes/no): ");
        String choice = scanner.nextLine();
        if ("yes".equalsIgnoreCase(choice)) {
            System.out.print("File path: ");
            String filePath = scanner.nextLine();
            logService.exportLogs(filePath);
        }
    }
}
