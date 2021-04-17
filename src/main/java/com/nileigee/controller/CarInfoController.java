package com.nileigee.controller;

import com.nileigee.entity.CarInfo;
import com.nileigee.entity.PageBean;
import com.nileigee.entity.WorkerInfo;
import com.nileigee.service.CarInfoService;
import com.nileigee.service.WorkerInfoService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/carInfo")
public class CarInfoController {

    @Autowired
    private CarInfoService carInfoService;

    @Autowired
    private WorkerInfoService workerInfoService;

    /**
     * 1.查找车辆列表
     */
    @RequestMapping("/findCarList")
    @ResponseBody
    public ModelAndView findCarList(){
        List<CarInfo> carList = carInfoService.findCarList();

        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("carList",carList);
        modelAndView.setViewName("car/car_list");
        return modelAndView;
    }
    /**
     * 2.保存车辆
     * @param carInfo
     * @return
     */
    @RequestMapping(value = "/saveCar")
    public String saveCar(CarInfo carInfo){
        System.out.println("传入的参数carInfo:"+carInfo);
        int saveCar = carInfoService.saveCar(carInfo);
        System.out.println("saveCar:"+saveCar);
       /* ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("car/car_list");*/
        return "redirect:/carInfo/findCarPageByCondition";
    }

    /**
     *3.为更新查找某个车辆和司机
     * @param carId
     * @return
     */
    @RequestMapping("/findCarAndDriverForUpdate")
    @ResponseBody
    public ModelAndView findCarAndDriverForUpdate(String carId){
        List<WorkerInfo> allDriver = workerInfoService.findAllDriver("司机");
        int carid = Integer.parseInt(carId);
        CarInfo carById = carInfoService.findCarById(carid);
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("allDriver",allDriver);
        modelAndView.addObject("carById",carById);
        modelAndView.setViewName("car/car_update");
        return modelAndView;
    }

    /**
     * 4.更新车辆
     * @param carInfo
     * @return
     */
    @RequestMapping("/updateCar")
    public String  updateCar(@Param("carInfo") CarInfo carInfo){
        System.out.println(carInfo);
        int updateCar = carInfoService.updateCar(carInfo);
        System.out.println("updateCar:"+updateCar);
       /* ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("index");*/
        return "redirect:/carInfo/findCarPageByCondition";
    }

    /**
     * 5.删除车辆
     * @param carId
     * @return
     */
    @RequestMapping("/deleteCar")
    public String deleteCar(String carId){
        System.out.println("carId:"+carId);
        int carid = Integer.parseInt(carId);
        int deleteCar = carInfoService.deleteCar(carid);
        System.out.println("deleteCar:"+deleteCar);
        return "redirect:/carInfo/findCarPageByCondition";
    }

    /**
     *6. 删除所选中的车辆
     * @param
     * @return
     */
    @RequestMapping("/deleteCarSelected")
    public String deleteCarSelected(@RequestParam("carIds[]") String[] carIds){
        for (int i = 0; i < carIds.length; i++) {
            System.out.println(carIds[i]);
            int deleteCars = carInfoService.deleteCar(Integer.parseInt(carIds[i]));
            System.out.println(deleteCars);
            System.out.println("==========");
        }

        return "redirect:/carInfo/findCarPageByCondition";
    }

    /**
     * 7.根据条件查找车辆
     * @param carNumber
     * @param carType
     * @param carLoad
     * @return
     */
    @RequestMapping("/findCarByCarNumber")
    @ResponseBody
    public ModelAndView findCarByCarNumber(String carNumber,String carType,String carLoad){
        CarInfo carInfo=new CarInfo();
        carInfo.setCarNumber(carNumber);
        carInfo.setCarType(carType);
        carInfo.setCarLoad(Integer.parseInt(carLoad));
        System.out.println("carInfo:"+carInfo);
        List<CarInfo> carByCarNumber = carInfoService.findCarByCarNumber(carInfo);
        System.out.println(carByCarNumber);

        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("carList",carByCarNumber);
        modelAndView.setViewName("car/car_list");
        return modelAndView;
    }

    /**
     * 8.列表分页
     * @param currentPage
     * @param rows
     * @return
     */
    @RequestMapping("/findCarByPage")
    @ResponseBody
    public ModelAndView findCarByPage(String currentPage,String rows){
        System.out.println("currentPage:"+currentPage);
        System.out.println("rows:"+rows);
        if (currentPage==null||currentPage==""){
            currentPage="1";
        }
        if (rows==null||rows==""){
            rows="8";
        }
        System.out.println("currentPage1:"+currentPage);
        System.out.println("rows1:"+rows);
        PageBean<CarInfo> carByPage = carInfoService.findCarByPage(Integer.parseInt(currentPage), Integer.parseInt(rows));
        System.out.println("carByPage:"+carByPage);
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("carByPage",carByPage);
        modelAndView.setViewName("car/car_list");
        return modelAndView;
    }

    /**
     *9.根据条件列表分页
     *
     * @return
     */
    @RequestMapping("/findCarPageByCondition")
    @ResponseBody
    public ModelAndView findCarPageByCondition (String currentPage, String rows,String carNumber,String carType,String carLoad){
        System.out.println("参数currentPage："+currentPage);
        System.out.println("参数rows："+rows);
        System.out.println("参数carNumber："+carNumber);
        System.out.println("参数carType："+carType);
        System.out.println("参数carLoad："+carLoad);
        Map<String, Object> condition=new HashMap<>();
        condition.put("carNumber",carNumber);
        condition.put("carType",carType);
        condition.put("carLoad",carLoad);
        System.out.println("参数condition:"+condition);
        if (currentPage==null||currentPage==""){
            currentPage="1";
        }
        if (rows==null||rows==""){
            rows="5";
        }
        PageBean<CarInfo> carPageByNumber = carInfoService.findCarPageByCondition(Integer.parseInt(currentPage),Integer.parseInt(rows),condition);
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("carByPage",carPageByNumber);
        modelAndView.addObject("condition",condition);
        modelAndView.setViewName("car/car_list");
        return modelAndView;
    }

}
