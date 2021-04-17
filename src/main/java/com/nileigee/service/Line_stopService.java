package com.nileigee.service;

import java.util.Map;

public interface Line_stopService {
    /**
     * 1.根据lineId和stopId查询indexs
     *
     * @return
     */
    int findIndexByLineIdAndStopId(int lineId,int stopId);

    /**
     * 2.根据lineId和stopId改变indexs
     *
     * @return
     */
    int updateIndexByLineIdAndStopId(int lineId,int stopId,int indexs);

    /**
     * 3.获得最大的index用于站点加入路线
     * @param lineId
     * @return
     */
    int findMaxIndex(int lineId);

    /**
     * 4.站点加入线路
     * @param
     * @return
     */
    int insertStopByLineIdAndStopId(int lineId,int stopId,int indexs);

    /**
     * 5.移出线路
     * @param
     * @return
     */
    int lineMoveStop(int lineId,int stopId);
}
