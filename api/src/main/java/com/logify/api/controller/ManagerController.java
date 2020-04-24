package com.logify.api.controller;

import com.logify.api.dto.EmployeeLogDTO;
import com.logify.api.model.UserResponse;
import com.logify.api.service.EmployeeService;
import com.logify.api.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/v1/manager")
public class ManagerController {
    @Autowired
    JwtUserDetailsService jwtUserDetailsService;
    @Autowired
    EmployeeService employeeService;
    @GetMapping("employeeLog")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<EmployeeLogDTO>> getLogOfEmployees(@RequestParam  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return new ResponseEntity<>(employeeService.getLogByDate(date), HttpStatus.OK);
    }
    @GetMapping("allUsers")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserResponse>> getAllUserData() {
        return new ResponseEntity<>(jwtUserDetailsService.getAllUser(), HttpStatus.OK);
    }
}
