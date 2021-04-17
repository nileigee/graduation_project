package com.nileigee.service;


import com.nileigee.dao.StopInfoDao;
import com.nileigee.entity.PageBean;
import com.nileigee.entity.StopInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("stopInfoService")
@Transactional
public class StopInfoServiceImpl implements StopInfoService{

    @Autowired
    private StopInfoDao stopInfoDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public PageBean<StopInfo> findStopByPage(Integer currentPage, Integer rows) {
        System.out.println("currentPage2:"+currentPage);
        System.out.println("rows2:"+rows);
        //1.创建空的PageBean对象
        PageBean<StopInfo> stopInfoPageBean=new PageBean<StopInfo>();
        System.out.println("stopInfoPageBean11:"+stopInfoPageBean);
        //2.设置参数
        stopInfoPageBean.setCurrentPage(currentPage);
        stopInfoPageBean.setRows(rows);
        //3.调用查询总记录数
        int totalCount = stopInfoDao.findTotalCount();
        stopInfoPageBean.setTotalCount(totalCount);
        //4.调用查询List集合
        //计算开始的记录索引
        int start=(currentPage-1)*rows;
        System.out.println("stat:"+start);
        System.out.println("rows:"+rows);
        List<StopInfo> stopListByPage = stopInfoDao.findStopByPage(start, rows);
        System.out.println("stopByPage:"+stopListByPage);
        stopInfoPageBean.setList(stopListByPage);
        //5.计算总页码
        int totalPage= (totalCount % rows) ==0 ? totalCount/rows : (totalCount/rows)+1;
        stopInfoPageBean.setTotalPage(totalPage);
        System.out.println("stopInfoPageBean:"+stopInfoPageBean);
        return stopInfoPageBean;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public int saveStop(StopInfo stopInfo) {
        return stopInfoDao.saveStop(stopInfo);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public StopInfo findStopById(int stopId) {
        return stopInfoDao.findStopById(stopId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public int updateStop(StopInfo stopInfo) {
        return stopInfoDao.updateStop(stopInfo);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public int deleteStop(int stopId) {
        return stopInfoDao.deleteStop(stopId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public PageBean<StopInfo> findStopByCondition(Integer currentPage, Integer rows,Map<String, Object> condition) {
        System.out.println("service层condition："+condition);
        //1.创建空的PageBean对象
        PageBean<StopInfo> stopInfoPageBean=new PageBean<StopInfo>();
        //2.设置参数
        stopInfoPageBean.setCurrentPage(currentPage);
        stopInfoPageBean.setRows(rows);
        //3.调用查询总记录数
        int totalCount = stopInfoDao.findTotalCountByCondition(condition);
        System.out.println("service层totalCount:"+totalCount);
        stopInfoPageBean.setTotalCount(totalCount);
        //4.计算开始的记录索引
        int start=(currentPage-1)*rows;
        System.out.println("service层start:"+start);
        System.out.println("service层rows:"+rows);
        condition.put("start",start);
        condition.put("rows",rows);
        System.out.println("添加元素后的condition："+condition);
        //5.调用查询List集合
        List<StopInfo> stopListByPage=stopInfoDao.findStopByCondition(condition);
        System.out.println("查询结果stopListByPage："+stopListByPage);
        stopInfoPageBean.setList(stopListByPage);
        //6.计算总页码
        int totalPage= (totalCount % rows) ==0 ? totalCount/rows : (totalCount/rows)+1;
        stopInfoPageBean.setTotalPage(totalPage);
        System.out.println("返回结果stopInfoPageBean:"+stopInfoPageBean);
        return stopInfoPageBean;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public PageBean<StopInfo> findStopsByLineId(Integer currentPage, Integer rows, Map<String, Object> condition) {
        System.out.println("service层condition："+condition);
        //1.创建空的PageBean对象
        PageBean<StopInfo> stopInfoPageBean=new PageBean<StopInfo>();
        //2.设置参数
        stopInfoPageBean.setCurrentPage(currentPage);
        stopInfoPageBean.setRows(rows);
        //3.调用查询总记录数
        int totalCount = stopInfoDao.findTotalCountByLineId(condition);
        System.out.println("service层totalCount:"+totalCount);
        stopInfoPageBean.setTotalCount(totalCount);
        //4.计算开始的记录索引
        int start=(currentPage-1)*rows;
        System.out.println("service层start:"+start);
        System.out.println("service层rows:"+rows);
        condition.put("start",start);
        condition.put("rows",rows);
        System.out.println("添加元素后的condition："+condition);
        //5.调用查询List集合
        List<StopInfo> stopListByPage=stopInfoDao.findStopsByLineId(condition);
        System.out.println("查询结果stopListByPage："+stopListByPage);
        stopInfoPageBean.setList(stopListByPage);
        //6.计算总页码
        int totalPage= (totalCount % rows) ==0 ? totalCount/rows : (totalCount/rows)+1;
        stopInfoPageBean.setTotalPage(totalPage);
        System.out.println("返回结果stopInfoPageBean:"+stopInfoPageBean);
        return stopInfoPageBean;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<StopInfo> findStopsByLineIdNoPage(int lineId) {
        Map<String, Object> condition=new HashMap<>();
        condition.put("lineId",lineId);
        return stopInfoDao.findStopsByLineIdNoPage(condition);
    }
}
