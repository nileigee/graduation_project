package com.nileigee.entity;

public class StopInfo {
    private int stopId;
    private String stopName;
    private String stopType;
    private double stopLongitude;
    private double stopLatitude;
    private String stopAddress;
    private int workerId;
    private String stopStatus;
    private String stopDescribe;
    private WorkerInfo workerInfo;

    public StopInfo() {
    }

    public StopInfo(int stopId, String stopName, String stopType, double stopLongitude, double stopLatitude, String stopAddress, int workerId, String stopStatus, String stopDescribe, WorkerInfo workerInfo) {
        this.stopId = stopId;
        this.stopName = stopName;
        this.stopType = stopType;
        this.stopLongitude = stopLongitude;
        this.stopLatitude = stopLatitude;
        this.stopAddress = stopAddress;
        this.workerId = workerId;
        this.stopStatus = stopStatus;
        this.stopDescribe = stopDescribe;
        this.workerInfo = workerInfo;
    }

    @Override
    public String toString() {
        return "StopInfo{" +
                "stopId=" + stopId +
                ", stopName='" + stopName + '\'' +
                ", stopType='" + stopType + '\'' +
                ", stopLongitude=" + stopLongitude +
                ", stopLatitude=" + stopLatitude +
                ", stopAddress='" + stopAddress + '\'' +
                ", workerId=" + workerId +
                ", stopStatus='" + stopStatus + '\'' +
                ", stopDescribe='" + stopDescribe + '\'' +
                ", workerInfo=" + workerInfo +
                '}';
    }

    public int getStopId() {
        return stopId;
    }

    public void setStopId(int stopId) {
        this.stopId = stopId;
    }

    public String getStopName() {
        return stopName;
    }

    public void setStopName(String stopName) {
        this.stopName = stopName;
    }

    public String getStopType() {
        return stopType;
    }

    public void setStopType(String stopType) {
        this.stopType = stopType;
    }

    public double getStopLongitude() {
        return stopLongitude;
    }

    public void setStopLongitude(double stopLongitude) {
        this.stopLongitude = stopLongitude;
    }

    public double getStopLatitude() {
        return stopLatitude;
    }

    public void setStopLatitude(double stopLatitude) {
        this.stopLatitude = stopLatitude;
    }

    public String getStopAddress() {
        return stopAddress;
    }

    public void setStopAddress(String stopAddress) {
        this.stopAddress = stopAddress;
    }

    public int getWorkerId() {
        return workerId;
    }

    public void setWorkerId(int workerId) {
        this.workerId = workerId;
    }

    public String getStopStatus() {
        return stopStatus;
    }

    public void setStopStatus(String stopStatus) {
        this.stopStatus = stopStatus;
    }

    public String getStopDescribe() {
        return stopDescribe;
    }

    public void setStopDescribe(String stopDescribe) {
        this.stopDescribe = stopDescribe;
    }

    public WorkerInfo getWorkerInfo() {
        return workerInfo;
    }

    public void setWorkerInfo(WorkerInfo workerInfo) {
        this.workerInfo = workerInfo;
    }
}