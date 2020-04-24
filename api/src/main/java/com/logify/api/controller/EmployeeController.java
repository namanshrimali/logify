package com.logify.api.controller;

import com.logify.api.exception.SessionAlreadyActiveException;
import com.logify.api.model.EmployeeLog;
import com.logify.api.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequestMapping("api/v1/employee")
@RestController
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    @PreAuthorize("hasAnyRole('USER')")
    @PostMapping("{employeeId}/checkin")
    public ResponseEntity<EmployeeLog> logCheckInTime(@PathVariable String employeeId) throws SessionAlreadyActiveException {
        return new ResponseEntity<>(employeeService.logEmployeeCheckIn(employeeId), HttpStatus.OK);
    }

    @PostMapping("{employeeId}/checkout")
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<EmployeeLog> logCheckOutTime(@PathVariable String employeeId) throws SessionAlreadyActiveException {
        return new ResponseEntity<>(employeeService.logEmployeeCheckOut(employeeId), HttpStatus.OK);
    }

    @GetMapping("{employeeId}/history")
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<List<EmployeeLog>> getLoggedHistory(@PathVariable String employeeId, @RequestParam(required = false) boolean lastActiveSession) {
        return new ResponseEntity<>(employeeService.getLogHistory(employeeId, lastActiveSession), HttpStatus.OK);
    }
}
