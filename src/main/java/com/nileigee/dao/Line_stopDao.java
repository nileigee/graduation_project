package com.nileigee.dao;

import java.util.Map;

public interface Line_stopDao {
    /**
     * 1.根据lineId和stopId查询indexs
     * @param condition
     * @return
     */
    int findIndexByLineIdAndStopId(Map<String,Object> condition);

    /**
     * 2.根据lineId和stopId改变indexs
     * @param condition
     * @return
     */
    int updateIndexByLineIdAndStopId(Map<String,Object> condition);

    /**
     * 3.获得最大的index用于站点加入路线
     * @param lineId
     * @return
     */
    int findMaxIndex(int lineId);

    /**
     * 4.站点加入线路
     * @param condition
     * @return
     */
    int insertStopByLineIdAndStopId(Map<String,Object> condition);

    /**
     * 5.移出线路
     * @param condition
     * @return
     */
    int lineMoveStop(Map<String,Object> condition);
}
