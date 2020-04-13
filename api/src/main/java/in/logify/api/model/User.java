package in.logify.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;


@Entity
@Table(name = "user_credentials")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column()
    private String username;
    @Column(name="name")
    private String name;
    @Column
    @JsonIgnore
    private String password;
    @Column
    private String role;
//    @OneToMany(fetch = FetchType.LAZY, mappedBy="id", cascade=CascadeType.ALL)
//    private List<EmployeeLog> employeeLog;
//
//
//    public List<EmployeeLog> getEmployeeLog() {
//        return employeeLog;
//    }
//
//    public void setEmployeeLog(List<EmployeeLog> employeeLog) {
//        this.employeeLog = employeeLog;
//    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


//    public Set<Role> getRoles() {
//        return roles;
//    }
//
//    public void setRoles(Set<Role> roles) {
//        this.roles = roles;
//    }

}