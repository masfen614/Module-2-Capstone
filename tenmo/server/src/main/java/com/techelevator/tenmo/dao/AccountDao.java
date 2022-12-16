package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;

import java.math.BigDecimal;
import java.util.List;

public interface AccountDao {

    //CRUD

    Account getAccount (int accountId);

    List<Account> getAllAccounts();

    Account createAccount (Account account);

    boolean updateAccount(Account account);

    boolean deleteAccount(int accountId);

    //Account getBalance(BigDecimal balance);

    BigDecimal getBalanceByUserId(int accountId);

    void subtractFromAccount(int senderUserId, Transfer transfer);

    void addToAccount(Transfer transfer);
}
