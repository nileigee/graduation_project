package com.nileigee.entity;

public class TransferInfo {
    private int transferId;
    private String transferName;
    private String transferType;
    private Double transferLongitude;
    private Double transferLatitude;
    private String transferAddress;
    private int workerId;
    private String transferStatus;
    private String transferDescribe;
    private WorkerInfo workerInfo;

    public TransferInfo() {
    }

    public TransferInfo(int transferId, String transferName, String transferType, Double transferLongitude, Double transferLatitude, String transferAddress, int workerId, String transferStatus, String transferDescribe, WorkerInfo workerInfo) {
        this.transferId = transferId;
        this.transferName = transferName;
        this.transferType = transferType;
        this.transferLongitude = transferLongitude;
        this.transferLatitude = transferLatitude;
        this.transferAddress = transferAddress;
        this.workerId = workerId;
        this.transferStatus = transferStatus;
        this.transferDescribe = transferDescribe;
        this.workerInfo = workerInfo;
    }

    @Override
    public String toString() {
        return "TransferInfo{" +
                "transferId=" + transferId +
                ", transferName='" + transferName + '\'' +
                ", transferType='" + transferType + '\'' +
                ", transferLongitude=" + transferLongitude +
                ", transferLatitude=" + transferLatitude +
                ", transferAddress='" + transferAddress + '\'' +
                ", workerId=" + workerId +
                ", transferStatus='" + transferStatus + '\'' +
                ", transferDescribe='" + transferDescribe + '\'' +
                ", workerInfo=" + workerInfo +
                '}';
    }

    public int getTransferId() {
        return transferId;
    }

    public void setTransferId(int transferId) {
        this.transferId = transferId;
    }

    public String getTransferName() {
        return transferName;
    }

    public void setTransferName(String transferName) {
        this.transferName = transferName;
    }

    public String getTransferType() {
        return transferType;
    }

    public void setTransferType(String transferType) {
        this.transferType = transferType;
    }

    public Double getTransferLongitude() {
        return transferLongitude;
    }

    public void setTransferLongitude(Double transferLongitude) {
        this.transferLongitude = transferLongitude;
    }

    public Double getTransferLatitude() {
        return transferLatitude;
    }

    public void setTransferLatitude(Double transferLatitude) {
        this.transferLatitude = transferLatitude;
    }

    public String getTransferAddress() {
        return transferAddress;
    }

    public void setTransferAddress(String transferAddress) {
        this.transferAddress = transferAddress;
    }

    public int getWorkerId() {
        return workerId;
    }

    public void setWorkerId(int workerId) {
        this.workerId = workerId;
    }

    public String getTransferStatus() {
        return transferStatus;
    }

    public void setTransferStatus(String transferStatus) {
        this.transferStatus = transferStatus;
    }

    public String getTransferDescribe() {
        return transferDescribe;
    }

    public void setTransferDescribe(String transferDescribe) {
        this.transferDescribe = transferDescribe;
    }

    public WorkerInfo getWorkerInfo() {
        return workerInfo;
    }

    public void setWorkerInfo(WorkerInfo workerInfo) {
        this.workerInfo = workerInfo;
    }
}
