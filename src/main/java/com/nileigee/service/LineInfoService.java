package com.nileigee.service;

import com.nileigee.entity.LineInfo;
import com.nileigee.entity.PageBean;

import java.util.Map;

public interface LineInfoService {
    /**
     * 1.根据条件查找
     * @param currentPage
     * @param rows
     * @param condition
     * @return
     */
    PageBean<LineInfo> findLineByCondition(Integer currentPage, Integer rows, Map<String, Object> condition);
    /**
     * 2.保存
     * @param lineInfo
     * @return
     */
    int saveLine(LineInfo lineInfo);
    /**
     * 3.根据id查
     * @param lineId
     * @return
     */
    LineInfo findLineById(int lineId);
    /**
     * 4.修改
     * @param lineInfo
     * @return
     */
    int updateLine(LineInfo lineInfo);

    /**
     * 5.删除
     * @param lineId
     * @return
     */
    int deleteLine(int lineId);
}
