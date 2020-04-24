package com.logify.api.service;

import com.logify.api.dao.EmployeeDAO;
import com.logify.api.dao.UserDao;
import com.logify.api.dto.EmployeeLogDTO;
import com.logify.api.exception.DataNotPresentException;
import com.logify.api.exception.DuplicateLogTimeException;
import com.logify.api.exception.SessionAlreadyActiveException;
import com.logify.api.model.EmployeeLog;
import com.logify.api.model.User;
import com.logify.api.model.UserStatus;
import com.logify.api.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class EmployeeService {

    @Autowired
    EmployeeDAO employeeDAO;
    @Autowired
    UserDao userDao;
    @Autowired
    JwtUserDetailsService jwtUserDetailsService;
    public EmployeeLog logEmployeeCheckIn(String username) throws SessionAlreadyActiveException {
        User user = jwtUserDetailsService.getUser(username);
        if(user.getStatus()== UserStatus.ACTIVE) {
            throw new SessionAlreadyActiveException(String.format(Constants.DATA_ALREADY_PRESENT, username,java.time.LocalDate.now()));
        }
        user.setStatus(UserStatus.ACTIVE);
        EmployeeLog employeeLog = new EmployeeLog();
        employeeLog.setLogDate(java.time.LocalDate.now());
        employeeLog.setStartTime(java.time.LocalDateTime.now());
        user.getEmployeeLog().add(employeeLog);
        userDao.save(user);
        return employeeLog;
    }


    public EmployeeLog logEmployeeCheckOut(String username) throws DataNotPresentException {
        User user = jwtUserDetailsService.getUser(username);
        if(user.getStatus()!=UserStatus.ACTIVE) {
            if(user.getStatus()==UserStatus.DONE_FOR_THE_DAY) {
                throw new DuplicateLogTimeException(Constants.MULTIPLE_DATA_LOG);
            }
            throw new DataNotPresentException(String.format(Constants.DATA_NOT_PRESENT, username,java.time.LocalDate.now()));
        }

        EmployeeLog employeeLog = employeeDAO.findByUserNameAndLogDate(username, java.time.LocalDate.now());
        calculateTimeWorked(employeeLog);

        user.setStatus(UserStatus.DONE_FOR_THE_DAY);
        userDao.save(user);

        return employeeDAO.save(employeeLog);
    }

    private void calculateTimeWorked(EmployeeLog employeeLog) {
        LocalDateTime endTime = java.time.LocalDateTime.now();
        employeeLog.setEndTime(endTime);
        LocalDateTime startTime = employeeLog.getStartTime();
        float days = startTime.until(endTime, ChronoUnit.DAYS);
        float hours = startTime.until(endTime, ChronoUnit.HOURS) ;
        float minutes = startTime.until(endTime, ChronoUnit.MINUTES);
        float totalTime = (days*24 + hours + minutes/60);
        employeeLog.setTotal(totalTime);

    }

    public List<EmployeeLogDTO> getLogByDate(LocalDate date) {
        return employeeDAO.findByLogDate(date);
    }
    public List<User> getInactiveUsers() {
        return userDao.findByStatus(UserStatus.YET_TO_START);
    }
    public List<EmployeeLog> getLogHistory(String employeeId, boolean wantLastActiveSession) {
        if(wantLastActiveSession) {
            List<EmployeeLog> employeeLogs = new ArrayList<>();
            EmployeeLog employeeLog = getLastActiveSession(employeeId);
            employeeLogs.add(employeeLog);
            return employeeLogs;
        }
        return employeeDAO.findByUsername(employeeId);
    }
    private EmployeeLog getLastActiveSession(String employeeId) {
        EmployeeLog employeeLog = new EmployeeLog();
        employeeLog = employeeDAO.findByUserNameAndLogDate(employeeId, java.time.LocalDate.now());
        if(employeeLog != null) {
            return employeeLog;
        }
        return employeeDAO.findByUserNameAndLogDate(employeeId, java.time.LocalDate.now().minusDays(1));

    }
}
