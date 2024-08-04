package com.carshop.model;

import java.time.LocalDateTime;

public class LogEntry {
    private int id;
    private User user;
    private String action;
    private LocalDateTime dateTime;

    public LogEntry(int id, User user, String action, LocalDateTime dateTime) {
        this.id = id;
        this.user = user;
        this.action = action;
        this.dateTime = dateTime;
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getAction() {
        return action;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    @Override
    public String toString() {
        return "LogEntry{" +
                "id=" + id +
                ", user=" + user.getUsername() +
                ", action='" + action + '\'' +
                ", dateTime=" + dateTime +
                '}';
    }
}
