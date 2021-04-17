package com.nileigee.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Time;
import java.util.Date;

public class LineInfo {
    private int lineId;
    private String lineName;
    private String lineType;
    private Double lineLength;

    private String lineStartTime;

    private String lineEndTime;
    private int transferId;
    private int workerId;
    private String lineStatus;
    private String lineDescribe;

    private WorkerInfo workerInfo;
    private TransferInfo transferInfo;

    @Override
    public String toString() {
        return "LineInfo{" +
                "lineId=" + lineId +
                ", lineName='" + lineName + '\'' +
                ", lineType='" + lineType + '\'' +
                ", lineLength=" + lineLength +
                ", lineStartTime='" + lineStartTime + '\'' +
                ", lineEndTime='" + lineEndTime + '\'' +
                ", transferId=" + transferId +
                ", workerId=" + workerId +
                ", lineStatus='" + lineStatus + '\'' +
                ", lineDescribe='" + lineDescribe + '\'' +
                ", workerInfo=" + workerInfo +
                ", transferInfo=" + transferInfo +
                '}';
    }

    public LineInfo() {
    }

    public int getLineId() {
        return lineId;
    }

    public void setLineId(int lineId) {
        this.lineId = lineId;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public String getLineType() {
        return lineType;
    }

    public void setLineType(String lineType) {
        this.lineType = lineType;
    }

    public Double getLineLength() {
        return lineLength;
    }

    public void setLineLength(Double lineLength) {
        this.lineLength = lineLength;
    }

    public String getLineStartTime() {
        return lineStartTime;
    }

    public void setLineStartTime(String lineStartTime) {
        this.lineStartTime = lineStartTime;
    }

    public String getLineEndTime() {
        return lineEndTime;
    }

    public void setLineEndTime(String lineEndTime) {
        this.lineEndTime = lineEndTime;
    }

    public int getTransferId() {
        return transferId;
    }

    public void setTransferId(int transferId) {
        this.transferId = transferId;
    }

    public int getWorkerId() {
        return workerId;
    }

    public void setWorkerId(int workerId) {
        this.workerId = workerId;
    }

    public String getLineStatus() {
        return lineStatus;
    }

    public void setLineStatus(String lineStatus) {
        this.lineStatus = lineStatus;
    }

    public String getLineDescribe() {
        return lineDescribe;
    }

    public void setLineDescribe(String lineDescribe) {
        this.lineDescribe = lineDescribe;
    }

    public WorkerInfo getWorkerInfo() {
        return workerInfo;
    }

    public void setWorkerInfo(WorkerInfo workerInfo) {
        this.workerInfo = workerInfo;
    }

    public TransferInfo getTransferInfo() {
        return transferInfo;
    }

    public void setTransferInfo(TransferInfo transferInfo) {
        this.transferInfo = transferInfo;
    }

    public LineInfo(int lineId, String lineName, String lineType, Double lineLength, String lineStartTime, String lineEndTime, int transferId, int workerId, String lineStatus, String lineDescribe, WorkerInfo workerInfo, TransferInfo transferInfo) {
        this.lineId = lineId;
        this.lineName = lineName;
        this.lineType = lineType;
        this.lineLength = lineLength;
        this.lineStartTime = lineStartTime;
        this.lineEndTime = lineEndTime;
        this.transferId = transferId;
        this.workerId = workerId;
        this.lineStatus = lineStatus;
        this.lineDescribe = lineDescribe;
        this.workerInfo = workerInfo;
        this.transferInfo = transferInfo;
    }
}