package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
public class TransferController {

    @Autowired
    private TransferDao transferDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private AccountDao accountDao;

    @RequestMapping(path = "/transfer", method = RequestMethod.GET)
    public Transfer getTransferById(Principal principal){
        int userId = userDao.findIdByUsername(principal.getName());
        Transfer transfer = transferDao.getTransfer(userId);
        if (transfer == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "TRANSFER NOT FOUND!") ;
        } else {
            return transfer;
        }
    }



    @RequestMapping(path = "/transfers", method = RequestMethod.GET)
    public List<Transfer> getAllTransfers(){
        return transferDao.getAllTransfers();
    }

    @PostMapping("/transfer")
    @ResponseStatus(HttpStatus.CREATED)
    public Transfer createTransfer(Principal principal, @RequestBody Transfer transfer){
        int senderUserId = userDao.findIdByUsername(principal.getName());

        //subtracting balance from principal user
        transfer.setTransferStatus("approved");
        accountDao.subtractFromAccount(senderUserId, transfer);//set approved
        //looking at balance and sending an amount
       // BigDecimal amountSent = accountDao.getBalanceByUserId(transfer.getTransferFromAcctId());
        //the user we're sending that amount to
        accountDao.addToAccount(transfer);
        //returning the transfer and persons involved
        return transferDao.createTransfer(transfer);


    }


//    @PutMapping("/transfer/{id}")
//    public void updateTransfer(@PathVariable int transferId, @RequestBody Transfer transfer){
//        transfer.setTransferId(transferId);
//        boolean updated = transferDao.updateTransfer(transfer);
//        if (!updated){
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "TRANSFER NOT UPDATED!");
//        }
   // }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "/transfer/{id}", method = RequestMethod.DELETE)
    public void deleteTransfer (@PathVariable int transferId){
        transferDao.deleteTransfer(transferId);
    }

}
