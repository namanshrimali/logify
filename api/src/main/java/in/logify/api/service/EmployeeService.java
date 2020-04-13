package in.logify.api.service;

import in.logify.api.dao.EmployeeDAO;
import in.logify.api.exception.DataAlreadyPresentException;
import in.logify.api.exception.DataNotPresentException;
import in.logify.api.exception.DuplicateLogTimeException;
import in.logify.api.model.EmployeeLog;
import in.logify.api.util.Constants;
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
    JwtUserDetailsService jwtUserDetailsService;
    public EmployeeLog logEmployeeCheckIn(String employeeId) throws DataAlreadyPresentException {
        EmployeeLog employeeLog = getLastActiveSession(employeeId);

        if(employeeLog!=null && employeeLog.getEndTime()==null) {
            throw new DataAlreadyPresentException(String.format(Constants.DATA_ALREADY_PRESENT, employeeId,java.time.LocalDate.now()));
        }
        employeeLog = new EmployeeLog();
        employeeLog.setUsername(employeeId);
        employeeLog.setLogDate(java.time.LocalDate.now());
        employeeLog.setStartTime(java.time.LocalDateTime.now());
        return employeeDAO.save(employeeLog);
    }
    public EmployeeLog logEmployeeCheckOut(String employeeId) throws DataNotPresentException {
        EmployeeLog employeeLog = null;
        EmployeeLog employeeLogToday = employeeDAO.findByUsernameAndLogDate(employeeId, java.time.LocalDate.now());
        if(employeeLogToday!=null) {
            employeeLog = employeeLogToday;
        } else {
            EmployeeLog employeeLogYesterday = employeeDAO.findByUsernameAndLogDate(employeeId, java.time.LocalDate.now().minusDays(1));
            if(employeeLogYesterday!=null) {
                employeeLog = employeeLogYesterday;
            }
        }
        if(employeeLog!=null) {
            if(employeeLog.getEndTime()!=null) {
                throw new DuplicateLogTimeException(Constants.MULTIPLE_DATA_LOG);
            }
        } else {
            throw new DataNotPresentException(String.format(Constants.DATA_NOT_PRESENT, employeeId,java.time.LocalDate.now()));
        }

        LocalDateTime endTime = java.time.LocalDateTime.now();
        employeeLog.setEndTime(endTime);

//        calculate total time worked
        LocalDateTime startTime = employeeLog.getStartTime();
        float days = startTime.until(endTime, ChronoUnit.DAYS);
        float hours = startTime.until(endTime, ChronoUnit.HOURS) ;
        float minutes = startTime.until(endTime, ChronoUnit.MINUTES);
        float totalTime = (days*24 + hours + minutes/60);

        employeeLog.setTotal(totalTime);

        return employeeDAO.save(employeeLog);
    }

    public List<EmployeeLog> getLogOfAllEmployees(LocalDate date) {
        Set<String> allEmployees = new HashSet<>();
        Set<String> checkedInEmployees = new HashSet<>();
        List<EmployeeLog> employeeLogs = employeeDAO.findByLogDate(date);
        if(employeeLogs == null) {
            employeeLogs = new ArrayList<>();
        } else {
            employeeLogs.forEach(employeeLog -> {checkedInEmployees.add(employeeLog.getUsername());});
        }
        jwtUserDetailsService.getAllUser().forEach(employees-> {allEmployees.add(employees.getUserName());});
        allEmployees.removeAll(checkedInEmployees);

        for(String nonCheckedInUsername: allEmployees) {
            EmployeeLog employeeLog = new EmployeeLog();
            employeeLog.setLogDate(date);
            employeeLog.setUsername(nonCheckedInUsername);
            employeeLogs.add(employeeLog);
        }
        return employeeLogs;
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
        employeeLog = employeeDAO.findByUsernameAndLogDate(employeeId, java.time.LocalDate.now());
        if(employeeLog != null) {
            return employeeLog;
        }
        return employeeDAO.findByUsernameAndLogDate(employeeId, java.time.LocalDate.now().minusDays(1));

    }
}
