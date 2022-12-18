package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.dao.UserDao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.User;
import com.techelevator.tenmo.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
public class UserController {

    JdbcTemplate jdbcTemplate;

    @Autowired
    private UserDao userDao;
    @Autowired
    private TransferDao transferDao;

    private UserDto userDto;

    @RequestMapping(path = "/user", method = RequestMethod.GET)
    public List<User> getAllUsers(Principal principal){
        int userId = userDao.findIdByUsername(principal.getName());
        return userDao.findAll();
    }
//    @RequestMapping(path = "/user", method = RequestMethod.GET)
//    public List<User> getAllUsers(Principal principal, String getName){
//        int userId = userDao.findAllOtherUsers(principal.getName());
//        return userDao.findAllOtherUsers();
//    }

//    @RequestMapping(path = "/user", method = RequestMethod.GET)
//    public List<User> getAllOtherUsers(Principal principal){
//        String users = userDto.findAllOtherUsers.);
//        return userDto.findAllOtherUsers;
//    }


//    @RequestMapping(path = "/user", method = RequestMethod.GET)
//    public List<User> findAllOtherUsers(Principal principal){
//        int userId = Integer.parseInt(userDto.findAllOtherUsers(principal));
//        return userDao.findAll();
//    }

}
