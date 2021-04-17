package com.nileigee.service;

import com.nileigee.entity.PageBean;
import com.nileigee.entity.WorkerInfo;

import java.util.List;
import java.util.Map;


public interface WorkerInfoService {
    /**
     * 1.查询所有类型的工作人员
     *
     */
    List<WorkerInfo> findAllDriver(String workerType);

    /**
     * 2.根据条件查询数据
     * @param currentPage
     * @param rows
     * @param condition
     * @return
     */
    PageBean<WorkerInfo> findWorkerByCondition(Integer currentPage, Integer rows, Map<String, Object> condition);

    /**
     * 3.新增人员
     * @param workerInfo
     * @return
     */
    int saveWorker(WorkerInfo workerInfo);

    /**
     * 4.根据id查询
     * @param workerId
     * @return
     */
    WorkerInfo findWorkerById(int workerId);

    /**
     * 5.更新
     * @param workerInfo
     * @return
     */
    int updateWorker(WorkerInfo workerInfo);

    /**
     * 6.删除
     * @param workerId
     * @return
     */
    int deleteWorker(int workerId);
}
