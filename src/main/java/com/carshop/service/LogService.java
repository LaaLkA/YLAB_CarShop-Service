package com.carshop.service;

import com.carshop.model.LogEntry;
import com.carshop.model.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LogService {
    private List<LogEntry> logs = new ArrayList<>();
    private int nextId = 1;

    public void logAction(User user, String action) {
        logs.add(new LogEntry(nextId++, user, action, LocalDateTime.now()));
    }

    public List<LogEntry> listLogs() {
        return new ArrayList<>(logs);
    }

    public void exportLogs(String filePath) {
        try (PrintWriter writer = new PrintWriter(new File(filePath))) {
            for (LogEntry log : logs) {
                writer.println(log);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
