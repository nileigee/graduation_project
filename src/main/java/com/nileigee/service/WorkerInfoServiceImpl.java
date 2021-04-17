package com.nileigee.service;

import com.nileigee.dao.WorkerInfoDao;
import com.nileigee.entity.PageBean;
import com.nileigee.entity.WorkerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service("workerInfoService")
@Transactional
public class WorkerInfoServiceImpl implements WorkerInfoService {

    @Autowired
    private WorkerInfoDao workerInfoDao;

    /**
     * 1.查询所有类型的工作人员
     *
     */

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<WorkerInfo> findAllDriver(String workerType) {
        return workerInfoDao.findAllDriver(workerType);
    }

    /**
     * 2.查询数据
     * @param currentPage
     * @param rows
     * @param condition
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public PageBean<WorkerInfo> findWorkerByCondition(Integer currentPage, Integer rows, Map<String, Object> condition) {
        System.out.println("service层condition："+condition);
        //1.创建空的PageBean对象
        PageBean<WorkerInfo> workerInfoPageBean=new PageBean<WorkerInfo>();
        //2.设置参数
        workerInfoPageBean.setCurrentPage(currentPage);
        workerInfoPageBean.setRows(rows);
        //3.调用查询总记录数
        int totalCount=workerInfoDao.findTotalCountByCondition(condition);
        System.out.println("service层totalCount:"+totalCount);
        workerInfoPageBean.setTotalCount(totalCount);
        //4.计算开始的记录索引
        int start=(currentPage-1)*rows;
        System.out.println("service层start:"+start);
        System.out.println("service层rows:"+rows);
        condition.put("start",start);
        condition.put("rows",rows);
        System.out.println("添加元素后的condition："+condition);
        //5.调用查询List集合
        List<WorkerInfo> workerListByPage = workerInfoDao.findWorkerByCondition(condition);
        System.out.println("查询结果workerListByPage："+workerListByPage);
        workerInfoPageBean.setList(workerListByPage);
        //6.计算总页码
        int totalPage= (totalCount % rows) ==0 ? totalCount/rows : (totalCount/rows)+1;
        workerInfoPageBean.setTotalPage(totalPage);
        System.out.println("返回结果workerInfoPageBean:"+workerInfoPageBean);
        return workerInfoPageBean;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public int saveWorker(WorkerInfo workerInfo) {
        return workerInfoDao.saveWorker(workerInfo);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public WorkerInfo findWorkerById(int workerId) {
        return workerInfoDao.findWorkerById(workerId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public int updateWorker(WorkerInfo workerInfo) {
        return workerInfoDao.updateWorker(workerInfo);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public int deleteWorker(int workerId) {
        return workerInfoDao.deleteWorker(workerId);
    }


}
