package com.nileigee.entity;

import java.io.Serializable;

public class CarInfo implements Serializable {
    private int carId;
    private String carNumber;
    private String carColor;
    private String carType;
    private int carLoad;
    private int workerId;
    private int lineId;
    private String carStatus;
    private String carDescribe;
    private WorkerInfo workerInfo;

    @Override
    public String toString() {
        return "CarInfo{" +
                "carId=" + carId +
                ", carNumber='" + carNumber + '\'' +
                ", carColor='" + carColor + '\'' +
                ", carType='" + carType + '\'' +
                ", carLoad=" + carLoad +
                ", workerId=" + workerId +
                ", lineId=" + lineId +
                ", carStatus='" + carStatus + '\'' +
                ", carDescribe='" + carDescribe + '\'' +
                ", workerInfo=" + workerInfo +
                '}';
    }

    public String getCarColor() {
        return carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }

    public String getCarDescribe() {
        return carDescribe;
    }

    public void setCarDescribe(String carDescribe) {
        this.carDescribe = carDescribe;
    }

    public CarInfo() {
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public int getCarLoad() {
        return carLoad;
    }

    public void setCarLoad(int carLoad) {
        this.carLoad = carLoad;
    }

    public int getWorkerId() {
        return workerId;
    }

    public void setWorkerId(int workerId) {
        this.workerId = workerId;
    }

    public int getLineId() {
        return lineId;
    }

    public void setLineId(int lineId) {
        this.lineId = lineId;
    }

    public String getCarStatus() {
        return carStatus;
    }

    public void setCarStatus(String carStatus) {
        this.carStatus = carStatus;
    }

    public WorkerInfo getWorkerInfo() {
        return workerInfo;
    }

    public void setWorkerInfo(WorkerInfo workerInfo) {
        this.workerInfo = workerInfo;
    }

    public CarInfo(int carId, String carNumber, String carType, int carLoad, int workerId, int lineId, String carStatus, WorkerInfo workerInfo) {
        this.carId = carId;
        this.carNumber = carNumber;
        this.carType = carType;
        this.carLoad = carLoad;
        this.workerId = workerId;
        this.lineId = lineId;
        this.carStatus = carStatus;
        this.workerInfo = workerInfo;
    }

    public CarInfo(int carId, String carNumber, String carColor, String carType, int carLoad, int workerId, int lineId, String carStatus, String carDescribe, WorkerInfo workerInfo) {
        this.carId = carId;
        this.carNumber = carNumber;
        this.carColor = carColor;
        this.carType = carType;
        this.carLoad = carLoad;
        this.workerId = workerId;
        this.lineId = lineId;
        this.carStatus = carStatus;
        this.carDescribe = carDescribe;
        this.workerInfo = workerInfo;
    }
}
