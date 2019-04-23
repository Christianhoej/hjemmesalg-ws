package com.backend.ws.hjemmesalgws.ui.controller;

import com.backend.ws.hjemmesalgws.exceptions.UserServiceException;
import com.backend.ws.hjemmesalgws.service.UserService;
import com.backend.ws.hjemmesalgws.shared.dao.User_DAO;
import com.backend.ws.hjemmesalgws.ui.usermodel.request.RequestOperationName;
import com.backend.ws.hjemmesalgws.ui.usermodel.request.User;
import com.backend.ws.hjemmesalgws.ui.usermodel.response.ErrorMessages;
import com.backend.ws.hjemmesalgws.ui.usermodel.response.OperationStatusModel;
import com.backend.ws.hjemmesalgws.ui.usermodel.response.RequestOperationStatus;
import com.backend.ws.hjemmesalgws.ui.usermodel.response.UserRest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.hibernate.hql.internal.antlr.SqlTokenTypes.DELETE;

//notationer herunder tilkendegiver at det er en restcontroller, dvs. håndterer rest kald
// og nedenunder tilkendegives at det skal indeholde mapping i form af "users"
@RestController
@RequestMapping("users") //http://localhost:8080/users
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(path="/{id}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}) //Read
    public UserRest getUser(@PathVariable String id){
        UserRest ret = new UserRest();

        User_DAO user_dao = userService.getUserByPublicUserId(id);

        BeanUtils.copyProperties(user_dao, ret);

        return ret;
    }
    @PostMapping(
            consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}) //Create
    public UserRest createUser(@RequestBody User userDetails) throws Exception {
        UserRest ret = new UserRest();

        if(userDetails.getFirstName().isEmpty()) throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
        User_DAO user_dao = new User_DAO();
        BeanUtils.copyProperties(userDetails, user_dao);//Metoden populerer dao'en (gennem getters og setters)
        User_DAO createdUser = userService.createUser(user_dao);
        BeanUtils.copyProperties(createdUser,ret);

        return ret;
    }
    @PutMapping(path="/{id}",
            consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}) //Update
    public UserRest updateUser(@PathVariable String id, @RequestBody User userDetails) {
        UserRest ret = new UserRest();

        if(userDetails.getFirstName().isEmpty()) throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
        User_DAO user_dao = new User_DAO();
        BeanUtils.copyProperties(userDetails, user_dao);//Metoden populerer dao'en (gennem getters og setters)
        User_DAO updatedUser = userService.updateUser(id, user_dao);
        BeanUtils.copyProperties(updatedUser,ret);
        return ret;
    }



    @DeleteMapping(path="/{id}",
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}) //Delete
    public OperationStatusModel deleteUser(@PathVariable String id) {
        OperationStatusModel ret = new OperationStatusModel();
        ret.setOperationName(RequestOperationName.DELETE.name());

        userService.deleteUser(id);

        ret.setOperationResult(RequestOperationStatus.SUCCESS.name());


        return ret;
    }

    @GetMapping(produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public List<UserRest> getUsers(@RequestParam(value="page",defaultValue="0") int page, @RequestParam(value="limit",defaultValue="25") int limit) {

        List<UserRest> ret = new ArrayList<>();
        List<User_DAO> users = userService.getUsers(page, limit);

        for(User_DAO user_dao: users){
            UserRest user = new UserRest();
            BeanUtils.copyProperties(user_dao, user);
            ret.add(user);
        }
        return ret;
    }

}
