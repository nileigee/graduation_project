package com.nileigee.service;

import com.nileigee.dao.LineInfoDao;
import com.nileigee.entity.LineInfo;
import com.nileigee.entity.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service("lineInfoService")
@Transactional
public class LineInfoServiceImpl implements LineInfoService {

    @Autowired
    private LineInfoDao lineInfoDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public PageBean<LineInfo> findLineByCondition(Integer currentPage, Integer rows, Map<String, Object> condition) {
        System.out.println("service层condition："+condition);
        //1.创建空的PageBean对象
        PageBean<LineInfo> lineInfoPageBean=new PageBean<LineInfo>();
        //2.设置参数
        lineInfoPageBean.setCurrentPage(currentPage);
        lineInfoPageBean.setRows(rows);
        //3.调用查询总记录数
       int totalCount= lineInfoDao.findTotalCountByCondition(condition);
        System.out.println("service层totalCount:"+totalCount);
        lineInfoPageBean.setTotalCount(totalCount);
        //4.计算开始的记录索引
        int start=(currentPage-1)*rows;
        System.out.println("service层start:"+start);
        System.out.println("service层rows:"+rows);
        condition.put("start",start);
        condition.put("rows",rows);
        System.out.println("添加元素后的condition："+condition);
        //5.调用查询List集合
        List<LineInfo> lineListByPage = lineInfoDao.findLineByCondition(condition);
        System.out.println("查询结果lineListByPage："+lineListByPage);
        lineInfoPageBean.setList(lineListByPage);
        //6.计算总页码
        int totalPage= (totalCount % rows) ==0 ? totalCount/rows : (totalCount/rows)+1;
        lineInfoPageBean.setTotalPage(totalPage);
        System.out.println("返回结果lineInfoPageBean:"+lineInfoPageBean);
        return lineInfoPageBean;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public int saveLine(LineInfo lineInfo) {
        return lineInfoDao.saveLine(lineInfo);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public LineInfo findLineById(int lineId) {
        return lineInfoDao.findLineById(lineId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public int updateLine(LineInfo lineInfo) {
        return lineInfoDao.updateLine(lineInfo);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public int deleteLine(int lineId) {
        return lineInfoDao.deleteLine(lineId);
    }
}
