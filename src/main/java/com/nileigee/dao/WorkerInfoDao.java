package com.nileigee.dao;

import com.nileigee.entity.WorkerInfo;

import java.util.List;
import java.util.Map;

public interface WorkerInfoDao {
    /**
     * 1.查询所有司机类型的工作人员
     *
     */
    List<WorkerInfo> findAllDriver(String workerType);

    /**
     * 2.查询总数
     * @param condition
     * @return
     */
    int findTotalCountByCondition(Map<String, Object> condition);

    /**
     * 3.根据条件
     * @param condition
     * @return
     */
    List<WorkerInfo> findWorkerByCondition(Map<String, Object> condition);

    /**
     * 4.新增人员
     * @param workerInfo
     * @return
     */
    int saveWorker(WorkerInfo workerInfo);

    /**
     * 5.根据id查询
     * @param workerId
     * @return
     */
    WorkerInfo findWorkerById(int workerId);

    /**
     * 6.更新
     * @param workerInfo
     * @return
     */
    int updateWorker(WorkerInfo workerInfo);

    /**
     * 7.删除
     * @param workerId
     * @return
     */
    int deleteWorker(int workerId);
}
