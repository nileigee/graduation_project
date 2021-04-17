package com.nileigee.dao;

import com.nileigee.entity.StopInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface StopInfoDao {
    /**
     * 1.分页查询站点
     * @param start
     * @param rows
     * @return
     */
    List<StopInfo> findStopByPage(@Param("start") Integer start,@Param("rows") Integer rows);

    /**
     * 2.查询车站总数
     * @return
     */
    int findTotalCount();

    /**
     * 3.保存存放站点
     * @param stopInfo
     * @return
     */
    int saveStop(StopInfo stopInfo);

    /**
     * 4.根据id查找存放站点
     * @param stopId
     * @return
     */
    StopInfo findStopById(int stopId);

    /**
     * 5.修改站点信息
     * @param stopInfo
     * @return
     */
    int updateStop(StopInfo stopInfo);

    /**
     * 6.删除站点
     * @param stopId
     * @return
     */
    int deleteStop(int stopId);

    /**
     *      * 7.根据条件查找
     *      * @param stopInfo
     * @return
     */
    List<StopInfo> findStopByCondition(Map<String, Object> condition);

    /**
     * 8.根据条件查找总数
     * @param condition
     * @return
     */
    int findTotalCountByCondition(Map<String, Object> condition);

    /**
     * 9.路线站点分配列表
     * @param condition
     * @return
     */
    List<StopInfo> findStopsByLineId(Map<String, Object> condition);

    /**
     * 10.路线站点总数
     * @param condition
     * @return
     */
    int findTotalCountByLineId(Map<String, Object> condition);

    /**
     * 11.未分页的查询线路上的站点
     * @param condition
     * @return
     */
    List<StopInfo> findStopsByLineIdNoPage(Map<String, Object> condition);
}
