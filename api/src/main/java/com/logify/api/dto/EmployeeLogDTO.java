package com.logify.api.dto;

import com.logify.api.model.UserStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class EmployeeLogDTO {
    private long id;
    private String username;
    private String name;
    private String role;
    private UserStatus status;
    private LocalDate logDate;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private float total;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public LocalDate getLogDate() {
        return logDate;
    }

    public void setLogDate(LocalDate logDate) {
        this.logDate = logDate;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public EmployeeLogDTO() {
    }

    public EmployeeLogDTO(long id, String username, String name, String role, UserStatus status, LocalDate logDate, LocalDateTime startTime, LocalDateTime endTime, float total) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.role = role;
        this.status = status;
        this.logDate = logDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.total = total;
    }
}
