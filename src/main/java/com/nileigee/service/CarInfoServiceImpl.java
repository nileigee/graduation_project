package com.nileigee.service;

import com.nileigee.dao.CarInfoDao;
import com.nileigee.entity.CarInfo;
import com.nileigee.entity.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("carInfoService")
@Transactional
public class CarInfoServiceImpl implements CarInfoService{

    @Autowired
    private CarInfoDao carInfoDao;

    /**
     * 1.查找车辆列表
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<CarInfo> findCarList() {
        return carInfoDao.findCarList();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public int saveCar(CarInfo carInfo) {
        return carInfoDao.saveCar(carInfo);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public CarInfo findCarById(int carId) {
        return carInfoDao.findCarById(carId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public int updateCar(CarInfo carInfo) {

        return carInfoDao.updateCar(carInfo);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public int deleteCar(int carId) {
        return carInfoDao.deleteCar(carId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<CarInfo> findCarByCarNumber(CarInfo carInfo) {
        return carInfoDao.findCarByCarNumber(carInfo);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public PageBean<CarInfo> findCarByPage(Integer currentPage, Integer rows) {
        //1.创建空的PageBean对象
        PageBean<CarInfo> carInfoPageBean=new PageBean<CarInfo>();
        //2.设置参数
        carInfoPageBean.setCurrentPage(currentPage);
        carInfoPageBean.setRows(rows);
        //3.调用查询总记录数
        int totalCount=carInfoDao.findTotalCount();
        carInfoPageBean.setTotalCount(totalCount);
        //4.调用查询List集合
        //计算开始的记录索引
        int start=(currentPage-1)*rows;
        System.out.println("stat:"+start);
        System.out.println("rows:"+rows);
        List<CarInfo> carInfoList=carInfoDao.findCarByPage(start,rows);
        System.out.println("carInfoList:"+carInfoList);
        carInfoPageBean.setList(carInfoList);
        //5.计算总页码
        int totalPage= (totalCount % rows) ==0 ? totalCount/rows : (totalCount/rows)+1;
        carInfoPageBean.setTotalPage(totalPage);
        System.out.println("carInfoPageBean:"+carInfoPageBean);
        return carInfoPageBean;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public PageBean<CarInfo> findCarPageByCondition(Integer currentPage, Integer rows, Map<String,Object> condition) {
       /* System.out.println("service层currentPage："+currentPage);
        System.out.println("service层rows："+rows);*/
        System.out.println("service层condition："+condition);
        //1.创建空的PageBean对象
        PageBean<CarInfo> carInfoPageBean=new PageBean<CarInfo>();
        //2.设置参数
        carInfoPageBean.setCurrentPage(currentPage);
        carInfoPageBean.setRows(rows);
        //3.调用查询总记录数
        int totalCount = carInfoDao.findQueryTotalCount(condition);
        System.out.println("service层totalCount："+totalCount);
        carInfoPageBean.setTotalCount(totalCount);
        //4.计算开始的记录索引
        int start=(currentPage-1)*rows;
        System.out.println("service层start:"+start);
        System.out.println("service层rows:"+rows);
        condition.put("start",start);
        condition.put("rows",rows);
        System.out.println("添加元素后的condition："+condition);
        //5.调用查询List集合
        List<CarInfo> carPageByNumber = carInfoDao.findCarPageByCondition(condition);
        System.out.println("查询结果carPageByNumber:"+carPageByNumber);
        carInfoPageBean.setList(carPageByNumber);
        //6.计算总页码
        int totalPage= (totalCount % rows) ==0 ? totalCount/rows : (totalCount/rows)+1;
        carInfoPageBean.setTotalPage(totalPage);
        System.out.println("返回结果的carInfoPageBean："+carInfoPageBean);
        return carInfoPageBean;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public PageBean<CarInfo> findCarsByLineId(Integer currentPage, Integer rows, Map<String,Object> condition) {
        //1.创建空的PageBean对象
        PageBean<CarInfo> carInfoPageBean=new PageBean<CarInfo>();
        //2.设置参数
        carInfoPageBean.setCurrentPage(currentPage);
        carInfoPageBean.setRows(rows);
        //3.调用查询总记录数
        int totalCount = carInfoDao.findTotalCountByLineId(condition);
        carInfoPageBean.setTotalCount(totalCount);
        //4.计算开始的记录索引
        int start=(currentPage-1)*rows;
        condition.put("start",start);
        condition.put("rows",rows);
        //5.调用查询List集合
        List<CarInfo> carsByLineId = carInfoDao.findCarsByLineId(condition);
        carInfoPageBean.setList(carsByLineId);
        //6.计算总页码
        int totalPage= (totalCount % rows) ==0 ? totalCount/rows : (totalCount/rows)+1;
        carInfoPageBean.setTotalPage(totalPage);
        return carInfoPageBean;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public int lineRemoveOrJoinCar(Integer carId, Integer lineId) {
        return carInfoDao.lineRemoveOrJoinCar(carId,lineId);
    }


}
