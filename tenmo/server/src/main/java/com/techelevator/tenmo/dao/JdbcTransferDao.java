package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTransferDao implements TransferDao {

    JdbcTemplate jdbcTemplate;

    public JdbcTransferDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Transfer getTransfer(int transferId) {
        Transfer transfer = null;
        String sql = "SELECT * FROM transfer WHERE transfer_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, transferId);
        if (results.next()){
            transfer = mapRowToTransfer(results);
        }
        return transfer;
    }
    @Override
    public Transfer getTransferStatus(String transferStatus) {
        String sql = "SELECT transfer_status FROM transfer;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, transferStatus);
        if (rowSet.next()){
            return mapRowToTransfer(rowSet);
        }
        throw new UsernameNotFoundException("transfer " + transferStatus + " was not found.");
    }



    @Override
    public List<Transfer> getAllTransfers() {
        List<Transfer> transfers = new ArrayList<>();
        String sql = "SELECT * FROM transfer";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()){
            Transfer transfer = mapRowToTransfer(results);
            transfers.add(transfer);
        }
        return transfers;
    }



    @Override
    public Transfer createTransfer(Transfer transfer) {
        Transfer newTransfer = null;
        String sql = "INSERT INTO transfer (transfer_to_acct_id, transfer_from_acct_id, " +
                "transfer_amount, transfer_status) VALUES (?, ?, ?, ?) RETURNING transfer_id";
        Integer transferId = jdbcTemplate.queryForObject(sql, Integer.class, transfer.getTransferToAcctId(), transfer.getTransferFromAcctId(),
                transfer.getTransferAmount(), transfer.getTransferStatus());
        newTransfer = getTransfer(transferId);
        return newTransfer;
    }

    @Override
    public boolean updateTransfer(Transfer transfer) {
        String sql = "UPDATE transfer SET transfer_id = ?, transfer_to_acct_id = ?, transfer_from_acct_id = ?, " +
                "transfer_amount = ?, transfer_status = approved";
        boolean success = false;
        int linesReturned = jdbcTemplate.update(sql, transfer.getTransferId(), transfer.getTransferToAcctId(),
                transfer.getTransferFromAcctId(), transfer.getTransferAmount(), transfer.getTransferStatus());
        if (linesReturned == 1){
            success = true;
        }
        return success;
    }

    @Override
    public boolean deleteTransfer(int transferId) {
        String sql = "DELETE from transfer WHERE transfer_id = ?";
        boolean success = false;
        int linesAffected = jdbcTemplate.update(sql, transferId);
        if (linesAffected == 1){
            success = true;
        }
        return success;
    }



    private Transfer mapRowToTransfer(SqlRowSet results){
        int transferId = results.getInt("transfer_id");
        int transferToAcctId = results.getInt("transfer_to_acct_id");
        int transferFromAcctId = results.getInt("transfer_from_acct_id");
        BigDecimal transferAmount = results.getBigDecimal("transfer_amount");
        String transferStatus = results.getString("transfer_status");
        return new Transfer (transferId, transferToAcctId, transferFromAcctId, transferAmount, transferStatus);
    }
}
