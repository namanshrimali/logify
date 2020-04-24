package com.logify.api.dao;
import com.logify.api.model.User;
import com.logify.api.model.UserStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends CrudRepository<User, Integer> {
    User findByUsername(String username);
    long deleteByUsername(String username);
    List<User> findByStatus(UserStatus userStatus);
}