package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JdbcAccountDao implements AccountDao {

    JdbcTemplate jdbcTemplate;

    public JdbcAccountDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Account getAccount(int accountId) {
        return null;
    }

    @Override
    public List<Account> getAllAccounts() {
        return null;
    }

    @Override
    public Account createAccount(Account account) {
        return null;
    }

    @Override
    public boolean updateAccount(Account account) {
        return false;
    }

    @Override
    public boolean deleteAccount(int accountId) {
        return false;
    }
}
