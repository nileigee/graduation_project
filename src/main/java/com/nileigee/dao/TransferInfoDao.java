package com.nileigee.dao;

import com.nileigee.entity.TransferInfo;

import java.util.List;
import java.util.Map;

public interface TransferInfoDao {
    /**
     * 1.根据条件查找数据总量
     * @param condition
     * @return
     */
    int findTotalCountByCondition(Map<String, Object> condition);

    /**
     * 2.根据条件查找中转站
     * @param condition
     * @return
     */
    List<TransferInfo> findTransferByCondition(Map<String, Object> condition);

    /**
     * 3.保存中转站
     * @param transferInfo
     * @return
     */
    int saveTransfer(TransferInfo transferInfo);

    /**
     * 4.根据id查询中转站
     * @param transferId
     * @return
     */
    TransferInfo findTransferById(int transferId);

    /**
     * 5.修改某个站点信息
     * @param transferInfo
     * @return
     */
    int updateTransfer(TransferInfo transferInfo);

    /**
     * 6.删除某个站点
     * @param transferId
     * @return
     */
    int deleteTransfer(int transferId);

    /**
     * 7.查询所有中转站
     * @return
     */
    List<TransferInfo> findAllTransfers();
}
