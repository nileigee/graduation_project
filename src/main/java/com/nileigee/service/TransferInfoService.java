package com.nileigee.service;

import com.nileigee.entity.PageBean;
import com.nileigee.entity.TransferInfo;

import java.util.List;
import java.util.Map;

public interface TransferInfoService {
    /**
     * 1.根据条件分页查询中转站
     * @param currentPage
     * @param rows
     * @param condition
     * @return
     */
    PageBean<TransferInfo> findTransferByCondition(Integer currentPage, Integer rows, Map<String, Object> condition);

    /**
     * 2.保存中转站
     * @param transferInfo
     * @return
     */
    int saveTransfer(TransferInfo transferInfo);

    /**
     * 3.根据id查询中转站
     * @param transferId
     * @return
     */
    TransferInfo findTransferById(int transferId);

    /**
     * 4.修改某个站点信息
     * @param transferInfo
     * @return
     */
    int updateTransfer(TransferInfo transferInfo);

    /**
     * 5.删除某个站点
     * @param transferId
     * @return
     */
    int deleteTransfer(int transferId);

    /**
     * 6.查询所有中转站
     * @return
     */
    List<TransferInfo> findAllTransfers();

}
