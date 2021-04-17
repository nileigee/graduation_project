package com.nileigee.entity;

import java.io.Serializable;

public class WorkerInfo implements Serializable {
    private int workerId;
    private String workerName;
    private String workerGender;
    private int workerAge;
    private String workerPhone;
    private String workerType;
    private String workerAddress;
    private String workerStatus;
    private String workerDescribe;

    public WorkerInfo() {
    }

    public String getWorkerGender() {
        return workerGender;
    }

    public void setWorkerGender(String workerGender) {
        this.workerGender = workerGender;
    }

    public String getWorkerDescribe() {
        return workerDescribe;
    }

    public void setWorkerDescribe(String workerDescribe) {
        this.workerDescribe = workerDescribe;
    }

    @Override
    public String toString() {
        return "WorkerInfo{" +
                "workerId=" + workerId +
                ", workerName='" + workerName + '\'' +
                ", workerGender='" + workerGender + '\'' +
                ", workerAge=" + workerAge +
                ", workerPhone='" + workerPhone + '\'' +
                ", workerType='" + workerType + '\'' +
                ", workerAddress='" + workerAddress + '\'' +
                ", workerStatus='" + workerStatus + '\'' +
                ", workerDescribe='" + workerDescribe + '\'' +
                '}';
    }

    public WorkerInfo(int workerId, String workerName, String workerGender, int workerAge, String workerPhone, String workerType, String workerAddress, String workerStatus, String workerDescribe) {
        this.workerId = workerId;
        this.workerName = workerName;
        this.workerGender = workerGender;
        this.workerAge = workerAge;
        this.workerPhone = workerPhone;
        this.workerType = workerType;
        this.workerAddress = workerAddress;
        this.workerStatus = workerStatus;
        this.workerDescribe = workerDescribe;
    }

    public WorkerInfo(int workerId, String workerName, int workerAge, String workerPhone, String workerType, String workerAddress, String workerStatus) {
        this.workerId = workerId;
        this.workerName = workerName;
        this.workerAge = workerAge;
        this.workerPhone = workerPhone;
        this.workerType = workerType;
        this.workerAddress = workerAddress;
        this.workerStatus = workerStatus;
    }

    public int getWorkerId() {
        return workerId;
    }

    public void setWorkerId(int workerId) {
        this.workerId = workerId;
    }

    public String getWorkerName() {
        return workerName;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }

    public int getWorkerAge() {
        return workerAge;
    }

    public void setWorkerAge(int workerAge) {
        this.workerAge = workerAge;
    }

    public String getWorkerPhone() {
        return workerPhone;
    }

    public void setWorkerPhone(String workerPhone) {
        this.workerPhone = workerPhone;
    }

    public String getWorkerType() {
        return workerType;
    }

    public void setWorkerType(String workerType) {
        this.workerType = workerType;
    }

    public String getWorkerAddress() {
        return workerAddress;
    }

    public void setWorkerAddress(String workerAddress) {
        this.workerAddress = workerAddress;
    }

    public String getWorkerStatus() {
        return workerStatus;
    }

    public void setWorkerStatus(String workerStatus) {
        this.workerStatus = workerStatus;
    }
}
