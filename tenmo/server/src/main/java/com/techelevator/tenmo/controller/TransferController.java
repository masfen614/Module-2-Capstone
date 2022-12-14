package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
public class TransferController {

    @Autowired

    private TransferDao transferDao;

    @RequestMapping(path = "/transfer/{id}", method = RequestMethod.GET)
    public Transfer getTransferById(@PathVariable int transferId){
        Transfer transfer = transferDao.getTransfer(transferId);
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
    public Transfer addTransfer(@RequestBody Transfer transfer){
        return transferDao.createTransfer(transfer);
    }

    @PutMapping("/transfer/{id}")
    public void updateTransfer(@PathVariable int transferId, @RequestBody Transfer transfer){
        transfer.setTransferId(transferId);
        boolean updated = transferDao.updateTransfer(transfer);
        if (!updated){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "TRANSFER NOT UPDATED!");
        }
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "/transfer/{id}", method = RequestMethod.DELETE)
    public void deleteTransfer (@PathVariable int transferId){
        transferDao.deleteTransfer(transferId);
    }

}
