package com.nileigee.service;

import com.nileigee.dao.TransferInfoDao;
import com.nileigee.entity.PageBean;
import com.nileigee.entity.StopInfo;
import com.nileigee.entity.TransferInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service("transferInfoService")
@Transactional
public class TransferInfoServiceImpl implements TransferInfoService {

    @Autowired
    private TransferInfoDao transferInfoDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public PageBean<TransferInfo> findTransferByCondition(Integer currentPage, Integer rows, Map<String, Object> condition) {
        System.out.println("service层condition："+condition);
        //1.创建空的PageBean对象
        PageBean<TransferInfo> transferInfoPageBean=new PageBean<TransferInfo>();
        //2.设置参数
        transferInfoPageBean.setCurrentPage(currentPage);
        transferInfoPageBean.setRows(rows);
        //3.调用查询总记录数
      int totalCount =transferInfoDao.findTotalCountByCondition(condition);
        System.out.println("service层totalCount:"+totalCount);
        transferInfoPageBean.setTotalCount(totalCount);
        //4.计算开始的记录索引
        int start=(currentPage-1)*rows;
        System.out.println("service层start:"+start);
        System.out.println("service层rows:"+rows);
        condition.put("start",start);
        condition.put("rows",rows);
        System.out.println("添加元素后的condition："+condition);
        //5.调用查询List集合
        List<TransferInfo> transferListByPage = transferInfoDao.findTransferByCondition(condition);
        System.out.println("查询结果transferListByPage："+transferListByPage);
        transferInfoPageBean.setList(transferListByPage);
        //6.计算总页码
        int totalPage= (totalCount % rows) ==0 ? totalCount/rows : (totalCount/rows)+1;
        transferInfoPageBean.setTotalPage(totalPage);
        System.out.println("返回结果transferInfoPageBean:"+transferInfoPageBean);
        return transferInfoPageBean;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public int saveTransfer(TransferInfo transferInfo) {
        return transferInfoDao.saveTransfer(transferInfo);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public TransferInfo findTransferById(int transferId) {
        return transferInfoDao.findTransferById(transferId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public int updateTransfer(TransferInfo transferInfo) {
        return transferInfoDao.updateTransfer(transferInfo);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public int deleteTransfer(int transferId) {
        return transferInfoDao.deleteTransfer(transferId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<TransferInfo> findAllTransfers() {
        return transferInfoDao.findAllTransfers();
    }

}
