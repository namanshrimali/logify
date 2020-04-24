package com.logify.api.dao;

import com.logify.api.dto.EmployeeLogDTO;
import com.logify.api.model.EmployeeLog;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeDAO extends CrudRepository<EmployeeLog, Long> {
    @Query("SELECT e FROM User u JOIN u.employeeLog e where u.username=?1 AND e.logDate=?2")
    EmployeeLog findByUserNameAndLogDate(String username, LocalDate logDate);
    @Query("SELECT new com.logify.api.dto.EmployeeLogDTO(u.id, u.username, u.name, u.role, u.status, e.logDate, e.startTime, e.endTime, e.total) FROM User u JOIN u.employeeLog e where e.logDate=?1")
    List<EmployeeLogDTO> findByLogDate(LocalDate logDate);
    @Query("SELECT e FROM User u JOIN u.employeeLog e where u.username=?1")
    List<EmployeeLog> findByUsername(String employeeId);
}