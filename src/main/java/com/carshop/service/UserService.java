package com.carshop.service;

import com.carshop.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserService {
    private Map<String, User> users = new HashMap<>();

    public boolean register(String username, String password, String role) {
        if (users.containsKey(username)) {
            return false;
        }
        users.put(username, new User(username, password, role));
        return true;
    }

    public User login(String username, String password) {
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public List<User> listUsers() {
        return new ArrayList<>(users.values());
    }

    public boolean addEmployee(String username, String password, String role) {
        return register(username, password, role);
    }

    public boolean editEmployee(String username, String newPassword, String newRole) {
        User user = users.get(username);
        if (user != null) {
            user.setPassword(newPassword);
            user.setRole(newRole);
            return true;
        }
        return false;
    }
}
