package com.nileigee.dao;


import com.nileigee.entity.CarInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CarInfoDao {
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
     * @param start
     * @param rows
     * @return
     */
    List<CarInfo> findCarByPage(@Param("start") Integer start,@Param("rows") Integer rows);

    /**
     * 8.查询车辆的总数
     * @return
     */
    int findTotalCount();

    /**
     * 9.根据条件查询车辆的总数
     * @return
     */
    int findQueryTotalCount(Map<String,Object> condition);

    /**
     * 10.根据条件列表分页
     *
     * @return
     */
    List<CarInfo> findCarPageByCondition(Map<String,Object> condition);

    /**
     * 11.根据lineId查找车辆
     * @param
     * @return
     */
    List<CarInfo> findCarsByLineId(Map<String,Object> condition);

    /**
     * 12.根据lineId查找总数
     * @param condition
     * @return
     */
    int findTotalCountByLineId(Map<String,Object> condition);

    /**
     * 13.向线路添加或移出车辆
     * @param carId
     * @param lineId
     * @return
     */
    int lineRemoveOrJoinCar(@Param("carId") Integer carId,@Param("lineId") Integer lineId);
}
