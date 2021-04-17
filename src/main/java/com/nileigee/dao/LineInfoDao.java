package com.nileigee.dao;

import com.nileigee.entity.LineInfo;

import java.util.List;
import java.util.Map;

public interface LineInfoDao {
    /**
     * 1.根据条件查询
     * @param condition
     * @return
     */
    List<LineInfo> findLineByCondition(Map<String,Object> condition);

    /**
     * 2.查询总数
     * @param condition
     * @return
     */
    int findTotalCountByCondition(Map<String,Object> condition);

    /**
     * 3.保存
     * @param lineInfo
     * @return
     */
    int saveLine(LineInfo lineInfo);

    /**
     * 4根据id查
     * @param lineId
     * @return
     */
    LineInfo findLineById(int lineId);

    /**
     * 5.修改
     * @param lineInfo
     * @return
     */
    int updateLine(LineInfo lineInfo);

    /**
     * 6.删除
     * @param lineId
     * @return
     */
    int deleteLine(int lineId);
}
