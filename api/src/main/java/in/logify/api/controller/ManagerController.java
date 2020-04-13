package in.logify.api.controller;

import in.logify.api.model.EmployeeLog;
import in.logify.api.model.User;
import in.logify.api.model.UserResponse;
import in.logify.api.service.EmployeeService;
import in.logify.api.service.JwtUserDetailsService;
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
    public ResponseEntity<List<EmployeeLog>> getLogOfEmployees(@RequestParam  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return new ResponseEntity<>(employeeService.getLogOfAllEmployees(date), HttpStatus.OK);
    }
    @GetMapping("allUsers")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserResponse>> getAllUserData() {
        return new ResponseEntity<>(jwtUserDetailsService.getAllUser(), HttpStatus.OK);
    }
}
