package in.logify.api.dao;

import in.logify.api.model.EmployeeLog;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EmployeeDAO extends CrudRepository<EmployeeLog, Long> {
    EmployeeLog findByUsernameAndLogDate(String userName, LocalDate logDate);
    List<EmployeeLog> findByLogDate(LocalDate logDate);

    List<EmployeeLog> findByUsername(String employeeId);
}
