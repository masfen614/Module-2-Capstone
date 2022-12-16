package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
public class AccountController {
    @Autowired
    private AccountDao accountDao;
    @Autowired
    private UserDao userDao;


    @RequestMapping(path = "/account", method = RequestMethod.GET)
    public Account getAccountById(Principal principal) {
        int userId = userDao.findIdByUsername(principal.getName());
        Account account = accountDao.getAccount(userId);
        if (account == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ACCOUNT NOT FOUND!") ;
        } else {
            return account;
        }
    }

//    @RequestMapping(path = "/account", method = RequestMethod.GET)
//    public List<Account> getAllAccounts() {
//        return accountDao.getAllAccounts();
//    }


//    @PostMapping("/account")
//    @ResponseStatus(HttpStatus.CREATED)
//    public Account addAccount(@RequestBody Account account) {
//        return accountDao.createAccount(account);
//
//    }

//    @PutMapping("/account/{id}")
//    public void updateAccount(@PathVariable int accountId, @RequestBody Account account) {
//        account.setAccountId(accountId);
//        boolean updated = accountDao.updateAccount(account);
//        if (!updated) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ACCOUNT NOT UPDATED!");
//        }
//    }




}
