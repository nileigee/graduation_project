package com.nileigee.service;

import com.nileigee.entity.CarInfo;
import com.nileigee.entity.PageBean;

import java.util.List;
import java.util.Map;

public interface CarInfoService {
    /**
     * 1.查找车辆列表
     * @return
     */
    List<CarInfo> findCarList();

    /**
     * 2.保存车辆
     * @param carInfo
     * @return
     */
    int saveCar(CarInfo carInfo);

    /**
     * 3.根据id查车辆
     * @param carId
     * @return
     */
    CarInfo findCarById(int carId);

    /**
     * 4.更新车辆信息
     * @param carInfo
     * @return
     */
    int updateCar(CarInfo carInfo);

    /**
     * 5.删除车辆
     * @param carId
     * @return
     */
    int deleteCar(int carId);

    /**
     * 6.根据条件查找车辆
     * @param carInfo
     * @return
     */
    List<CarInfo> findCarByCarNumber(CarInfo carInfo);

    /**
     * 7.列表分页
     * @param currentPage
     * @param rows
     * @return
     */
    PageBean<CarInfo> findCarByPage(Integer currentPage, Integer rows);

    /**
     * 8.根据条件列表分页
     *
     * @return
     */
    PageBean<CarInfo> findCarPageByCondition(Integer currentPage, Integer rows,Map<String,Object> condition);

    /**
     * 9.根据lineId查找车辆
     * @param
     * @return
     */
    PageBean<CarInfo> findCarsByLineId(Integer currentPage, Integer rows,Map<String,Object> condition);

    /**
     * 10.向线路添加或移出车辆
     * @param carId
     * @param lineId
     * @return
     */
    int lineRemoveOrJoinCar(Integer carId,Integer lineId);
}
