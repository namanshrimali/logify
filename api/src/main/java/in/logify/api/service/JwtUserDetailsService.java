package in.logify.api.service;

import in.logify.api.model.Role;
import in.logify.api.model.User;
import in.logify.api.model.UserDTO;
import in.logify.api.dao.UserDao;
import in.logify.api.model.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;


// JWTUserDetailsService implements the Spring Security UserDetailsService interface. It overrides the loadUserByUsername for fetching user details
// from the database using the username. The Spring Security Authentication Manager calls this method for getting the user details from the database
// when authenticating the user details provided by the user. Password for a user is stored in encrypted format using BCrypt



@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(user));
    }
    public User save(UserDTO user) {
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        newUser.setName(user.getName());
        newUser.setRole("USER");
        return userDao.save(newUser);
    }
    public List<UserResponse> getAllUser() {
        List<UserResponse> userList = new ArrayList<>();
        userDao.findAll().forEach(user -> {
            UserResponse userResponse = new UserResponse();
            userResponse.setName(user.getName());
            userResponse.setUserName(user.getUsername());
            userResponse.setRole(user.getRole());
            userList.add(userResponse);
        });
        return userList;
    }
    private Set getAuthority(User user) {
        Set authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
        return authorities;
    }
}