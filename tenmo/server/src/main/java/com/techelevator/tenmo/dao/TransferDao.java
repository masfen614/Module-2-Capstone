package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;

import java.util.List;

public interface TransferDao {
    //CRUD

    Transfer getTransfer (int transferId);

    List<Transfer> getAllTransfers();

    Transfer createTransfer (Transfer transfer);

    boolean updateTransfer(Transfer transfer);

    boolean deleteTransfer(int transferId);



}
