package com.nileigee.service;

import com.nileigee.dao.Line_stopDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service("line_stopService")
@Transactional
public class Line_stopServiceImpl implements Line_stopService{

    @Autowired
    private Line_stopDao line_stopDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public int findIndexByLineIdAndStopId(int lineId,int stopId) {
        Map<String, Object> condition=new HashMap<>();
        condition.put("lineId",lineId);
        condition.put("stopId",stopId);
        return line_stopDao.findIndexByLineIdAndStopId(condition);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public int updateIndexByLineIdAndStopId(int lineId,int stopId,int indexs) {
        Map<String, Object> condition=new HashMap<>();
        condition.put("lineId",lineId);
        condition.put("stopId",stopId);
        condition.put("indexs",indexs);
        return line_stopDao.updateIndexByLineIdAndStopId(condition);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public int findMaxIndex(int lineId) {
        return line_stopDao.findMaxIndex(lineId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public int insertStopByLineIdAndStopId(int lineId, int stopId, int indexs) {
        Map<String, Object> condition=new HashMap<>();
        condition.put("lineId",lineId);
        condition.put("stopId",stopId);
        condition.put("indexs",indexs);
        return line_stopDao.insertStopByLineIdAndStopId(condition);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public int lineMoveStop(int lineId, int stopId) {
        Map<String, Object> condition=new HashMap<>();
        condition.put("lineId",lineId);
        condition.put("stopId",stopId);
        return line_stopDao.lineMoveStop(condition);
    }
}
