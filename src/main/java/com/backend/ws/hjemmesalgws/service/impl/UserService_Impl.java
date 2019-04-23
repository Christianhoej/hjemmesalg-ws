package com.backend.ws.hjemmesalgws.service.impl;

import com.backend.ws.hjemmesalgws.exceptions.UserServiceException;
import com.backend.ws.hjemmesalgws.io.repositories.UserRepository;
import com.backend.ws.hjemmesalgws.io.entity.User_Entity;
import com.backend.ws.hjemmesalgws.service.UserService;
import com.backend.ws.hjemmesalgws.shared.Utils;
import com.backend.ws.hjemmesalgws.shared.dao.User_DAO;
import com.backend.ws.hjemmesalgws.ui.usermodel.response.ErrorMessages;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService_Impl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    Utils utils;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User_DAO createUser(User_DAO user) {

        if(userRepository.findByEmail(user.getEmail())!=null) throw new RuntimeException("User by that email already exist");//Tjekker om allrede findes.

        User_Entity user_entity = new User_Entity();
        BeanUtils.copyProperties(user, user_entity);

        String publicUserID = utils.generateUserId(30);
        user_entity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword())); //Denne værdi kopieres ikke over gennem BeanUtils, så derfor skal den sættes.
        user_entity.setUserId(publicUserID);

        User_Entity storedUserDetails = userRepository.save(user_entity);
        User_DAO ret = new User_DAO();
        BeanUtils.copyProperties(storedUserDetails, ret);
        return ret;
    }

    @Override
    public User_DAO getUser(String email) {

        User_Entity user_entity = userRepository.findByEmail(email);
        if(user_entity== null) throw new UsernameNotFoundException(email);

        User_DAO ret = new User_DAO();
        BeanUtils.copyProperties(user_entity, ret);
        return ret;
    }

    @Override
    public User_DAO getUserByPublicUserId(String id) {
        User_DAO ret = new User_DAO();
        User_Entity user_entity = userRepository.findByUserId(id);

        if(user_entity== null) throw new UsernameNotFoundException("User with ID: " + id + " not found");

        BeanUtils.copyProperties(user_entity, ret);

        return ret;

    }

    @Override
    public User_DAO updateUser(String id, User_DAO user_dao) {
        User_DAO ret = new User_DAO();
        User_Entity user_entity = userRepository.findByUserId(id);

        if(user_entity== null) throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        user_entity.setFirstName(user_dao.getFirstName());
        user_entity.setLastName(user_dao.getLastName());
        user_entity.setAddress((user_dao.getAddress()));
        user_entity.setBirthday(user_dao.getBirthday());
        user_entity.setGender(user_dao.getGender());
        user_entity.setPostalNr(user_dao.getPostalNr());
        user_entity.setPhonenumber(user_dao.getPhonenumber());
        //TODO.............. Anvend Builder Pattern når der er såå mange felter der skal/kan opdateres
        //TODO bør evt. også kunne ændre password. Dette skal køres gennem "encryption"

        User_Entity updUserDetails = userRepository.save(user_entity);
        BeanUtils.copyProperties(updUserDetails, ret);

        return ret;
    }

    @Override
    public void deleteUser(String id) {
        User_Entity user_entity = userRepository.findByUserId(id);
        if(user_entity== null) throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        userRepository.delete(user_entity);

    }

    @Override
    public List<User_DAO> getUsers(int page, int limit) {
        List<User_DAO> ret = new ArrayList<>();

        if(page>0) page = page-1;

        Pageable pageAbleRequest = PageRequest.of(page, limit);
        Page<User_Entity> usersPage = userRepository.findAll(pageAbleRequest);
        List<User_Entity> users = usersPage.getContent();

        for(User_Entity u : users){
            User_DAO user_dao = new User_DAO();
            BeanUtils.copyProperties(u,user_dao);
            ret.add(user_dao);
        }
        return ret;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User_Entity user_entity = userRepository.findByEmail(email);
        if(user_entity== null) throw new UsernameNotFoundException(email);
        return new User(user_entity.getEmail(),user_entity.getEncryptedPassword(), new ArrayList<>());
    }


}
