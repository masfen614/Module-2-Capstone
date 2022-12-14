package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class Transfer {
    private int transferId;
    private int transferToAcctId;
    private int transferFromAcctId;
    private BigDecimal transferAmount;
    private String transferStatus;

    public Transfer() {
    }

    public Transfer(int transferId, int transferToAcctId, int transferFromAcctId, BigDecimal transferAmount, String transferStatus) {
        this.transferId = transferId;
        this.transferToAcctId = transferToAcctId;
        this.transferFromAcctId = transferFromAcctId;
        this.transferAmount = transferAmount;
        this.transferStatus = transferStatus;
    }

    public int getTransferId() {
        return transferId;
    }

    public void setTransferId(int transferId) {
        this.transferId = transferId;
    }

    public int getTransferToAcctId() {
        return transferToAcctId;
    }

    public void setTransferToAcctId(int transferToAcctId) {
        this.transferToAcctId = transferToAcctId;
    }

    public int getTransferFromAcctId() {
        return transferFromAcctId;
    }

    public void setTransferFromAcctId(int transferFromAcctId) {
        this.transferFromAcctId = transferFromAcctId;
    }

    public BigDecimal getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(BigDecimal transferAmount) {
        this.transferAmount = transferAmount;
    }

    public String getTransferStatus() {
        return transferStatus;
    }

    public void setTransferStatus(String transferStatus) {
        this.transferStatus = transferStatus;
    }

    @Override
    public String toString() {
        return "Transfer{" +
                "transferId=" + transferId +
                ", transferToAcctId=" + transferToAcctId +
                ", transferFromAcctId=" + transferFromAcctId +
                ", transferAmount=" + transferAmount +
                ", transferStatus='" + transferStatus + '\'' +
                '}';
    }
}
