package com.backend.ws.hjemmesalgws.service;

import com.backend.ws.hjemmesalgws.shared.dao.User_DAO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    User_DAO createUser(User_DAO user);

    User_DAO getUser(String email);

    User_DAO getUserByPublicUserId(String id);

    User_DAO updateUser(String id, User_DAO user_dao);

    void deleteUser(String id);

    List<User_DAO> getUsers(int page, int limit);
}
