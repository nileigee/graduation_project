package com.nileigee.service;

import com.nileigee.entity.PageBean;
import com.nileigee.entity.StopInfo;

import java.util.List;
import java.util.Map;


public interface StopInfoService {
    /**
     * 1.站点分页查询
     * @param currentPage
     * @param rows
     * @return
     */
    PageBean<StopInfo> findStopByPage(Integer currentPage, Integer rows);

    /**
     * 2.保存存放站点
     * @param stopInfo
     * @return
     */
    int saveStop(StopInfo stopInfo);

    /**
     * 3.根据id查找存放站点
     * @param stopId
     * @return
     */
    StopInfo findStopById(int stopId);

    /**
     * 4.修改站点信息
     * @param stopInfo
     * @return
     */
    int updateStop(StopInfo stopInfo);

    /**
     * 5.删除站点
     * @param stopId
     * @return
     */
    int deleteStop(int stopId);

    /**
     ** 6.根据条件查找
     * *
     * @return
     */
    PageBean<StopInfo> findStopByCondition(Integer currentPage, Integer rows,Map<String, Object> condition);

    /**
     * 7.路线站点分配列表
     * @param currentPage
     * @param rows
     * @param condition
     * @return
     */
    PageBean<StopInfo> findStopsByLineId(Integer currentPage, Integer rows,Map<String, Object> condition);

    /**
     * 8.未分页的查询线路上的站点
     * @param lineId
     * @return
     */
    List<StopInfo> findStopsByLineIdNoPage(int lineId);
}
