package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcAccountDao implements AccountDao {

    JdbcTemplate jdbcTemplate;

    public JdbcAccountDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Account getAccount(int accountId) {
        Account account = null;
        String sql = "SELECT * FROM account WHERE account_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, accountId);
        if (results.next()){
            account = mapRowToAccount(results);
        }
        return account;
    }
    @Override
    public BigDecimal getBalanceByUserId(int userId) {
        BigDecimal balance = new BigDecimal(0);
        String sql = "SELECT * FROM account WHERE user_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        if (results.next()){
            balance = mapRowToAccount(results).getBalance();
        }
        return balance;
    }

    @Override
    public List<Account> getAllAccounts() {
        List<Account> accounts = new ArrayList<>();
        String sql = "SELECT * FROM account";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()){
            Account account = mapRowToAccount(results);
            accounts.add(account);
        }
        return accounts;
    }

    @Override
    public Account createAccount(Account account) {
        Account newAccount = null;
        String sql = "INSERT INTO account (account_id, user_id, balance " +
                "VALUES (?, ?, ?) RETURNING account_id = ?";
        Integer accountId = jdbcTemplate.queryForObject(sql, Integer.class, account.getAccountId(),
                account.getUserId(), account.getBalance());
        newAccount = getAccount(accountId);
        return newAccount;
    }

    @Override
    public boolean updateAccount(Account account) {
        String sql = "UPDATE account SET account_id = ?, user_id = ?, balance = ?" ;
        boolean success = false;
        int linesReturned = jdbcTemplate.update(sql, account.getAccountId(), account.getUserId(), account.getBalance());
        if(linesReturned == 1){
            success = true;
        }

        return success;
    }
    @Override
    public void subtractFromAccount(int senderUserId, Transfer transfer){
        String sql = "UPDATE account SET balance = balance - ? WHERE user_id = ?";
        jdbcTemplate.update(sql, transfer.getTransferAmount(), senderUserId);

    }
    @Override
    public void addToAccount(Transfer transfer){
        String sql = "UPDATE account SET balance = balance + ? WHERE user_id = ?";
        jdbcTemplate.update(sql, transfer.getTransferAmount(), transfer.getTransferToAcctId());

    }


    @Override
    public boolean deleteAccount(int accountId) {
        String sql = "DELETE from account WHERE account_id = ?";
        boolean success = false;
        int lineAffected = jdbcTemplate.update(sql, accountId);
        if(lineAffected == 1){
            success = true;

        }
        return success;
    }



    private Account mapRowToAccount(SqlRowSet results){
        int accountId = results.getInt("account_id");
        int userId = results.getInt("user_id");
        BigDecimal accountBalance = results.getBigDecimal("balance");
        return new Account (accountId, userId, accountBalance);
    }
}
