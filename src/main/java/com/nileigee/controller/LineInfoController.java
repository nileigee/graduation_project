package com.nileigee.controller;

import com.nileigee.dao.Line_stopDao;
import com.nileigee.entity.*;
import com.nileigee.service.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/lineInfo")
public class LineInfoController {

    @Autowired
    private LineInfoService lineInfoService;
    @Autowired
    private WorkerInfoService workerInfoService;
    @Autowired
    private TransferInfoService transferInfoService;
    @Autowired
    private CarInfoService carInfoService;
    @Autowired
    private StopInfoService stopInfoService;
    @Autowired
    private Line_stopService line_stopService;
    /**
     * 1。根据条件查询
     * @param currentPage
     * @param rows
     * @param lineName
     * @param lineType
     * @param lineStatus
     * @return
     */
    @RequestMapping("/findLineByCondition")
    @ResponseBody
    public ModelAndView findLineByCondition(String currentPage, String rows,String lineName,String lineType,String lineStatus){
        System.out.println("参数currentPage:"+currentPage);
        System.out.println("参数rows:"+rows);
        System.out.println("参数lineName:"+lineName);
        System.out.println("参数lineType:"+lineType);
        System.out.println("参数lineStatus:"+lineStatus);
        Map<String, Object> condition=new HashMap<>();
        condition.put("lineName",lineName);
        condition.put("lineType",lineType);
        condition.put("lineStatus",lineStatus);
        System.out.println("参数condition:"+condition);
        if (currentPage==null||"".equals(currentPage)){
            currentPage="1";
        }
        if (rows==null||"".equals(rows)){
            rows="5";
        }

        PageBean<LineInfo> lineByPage = lineInfoService.findLineByCondition(Integer.parseInt(currentPage), Integer.parseInt(rows), condition);
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("lineByPage",lineByPage);
        modelAndView.addObject("condition",condition);
        modelAndView.setViewName("line/line_list");
        return modelAndView;
    }

    /**
     * 2.查找负责人和中转站
     * @return
     */
    @RequestMapping("/findAllWorkersAndTransfers")
    @ResponseBody
    public ModelAndView findAllWorkersAndTransfers(){

        String workerType="线路负责人";
        List<WorkerInfo> allLineWorkers = workerInfoService.findAllDriver(workerType);

        List<TransferInfo> allTransfers = transferInfoService.findAllTransfers();

        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("allLineWorkers",allLineWorkers);
        modelAndView.addObject("allTransfers",allTransfers);
        modelAndView.setViewName("line/line_add");
        return modelAndView;
    }

    /**
     * 3.保存
     * @param lineInfo
     * @return
     */
    @RequestMapping("/saveLine")
    public String saveLine(LineInfo lineInfo){
        System.out.println("参数lineInfo："+lineInfo);
        int saveLine = lineInfoService.saveLine(lineInfo);
        System.out.println("返回保存结果saveLine："+saveLine);
        return "redirect:/lineInfo/findLineByCondition";
    }

    /**
     * 4.根据id查找
     * @param lineId
     * @return
     */
    @RequestMapping("/findLineById")
    @ResponseBody
    public ModelAndView findLineById(String lineId){
        System.out.println("参数lineId："+lineId);
        LineInfo lineById = lineInfoService.findLineById(Integer.parseInt(lineId));
        System.out.println("返回查询结果lineById："+lineById);

        List<WorkerInfo> allLineWorkers = workerInfoService.findAllDriver("线路负责人");
        System.out.println("返回查询司机allLineWorkers："+allLineWorkers);

        List<TransferInfo> allTransfers = transferInfoService.findAllTransfers();
        System.out.println("返回查询中转站allTransfers："+allTransfers);

        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("lineById",lineById);
        modelAndView.addObject("allLineWorkers",allLineWorkers);
        modelAndView.addObject("allTransfers",allTransfers);
        modelAndView.setViewName("line/line_update");
        return modelAndView;
    }

    /**
     * 5.修改
     * @param lineInfo
     * @return
     */
    @RequestMapping("/updateLine")
    public String updateLine(LineInfo lineInfo){
        System.out.println("参数lineInfo："+lineInfo);
        int updateLine = lineInfoService.updateLine(lineInfo);
        System.out.println("修改结果updateLine："+updateLine);
        return "redirect:/lineInfo/findLineByCondition";
    }

    /**
     * 6.删除
     * @param lineId
     * @return
     */
    @RequestMapping("/deleteLine")
    public String deleteLine(String lineId){
        System.out.println("参数lineId："+lineId);
        int deleteLine = lineInfoService.deleteLine(Integer.parseInt(lineId));
        System.out.println("删除结果deleteLine："+deleteLine);
        return "redirect:/lineInfo/findLineByCondition";
    }

    /**
     * 7.删除所选
     * @param
     * @return
     */
    @RequestMapping("/deleteLineSelected")
    public String deleteLineSelected(@RequestParam("lineIds[]") String[] lineIds){
        System.out.println("参数lineIds："+lineIds);
        for (int i = 0; i < lineIds.length; i++) {
            int deleteLine = lineInfoService.deleteLine(Integer.parseInt(lineIds[i]));
            System.out.println("删除所选deleteLine："+deleteLine);
            System.out.println("====");
        }

        return "redirect:/lineInfo/findLineByCondition";
    }

    /**
     * 8.根据lineId查找车辆
     * @param lineId
     * @return
     */
    @RequestMapping("/findCarsByLineId")
    @ResponseBody
    public ModelAndView findCarsByLineId(String currentPage, String rows, String lineId){
        System.out.println("参数lineId："+lineId);
        if (currentPage==null||"".equals(currentPage)){
            currentPage="1";
        }
        if (rows==null||"".equals(rows)){
            rows="8";
        }

        Map<String,Object> condition=new HashMap<>();
        condition.put("lineId",lineId);
        System.out.println("返回参数condition："+condition);
        PageBean<CarInfo> carsByLineId = carInfoService.findCarsByLineId(Integer.parseInt(currentPage), Integer.parseInt(rows), condition);
        System.out.println("查询结果carsByLineId："+carsByLineId);


        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("carsByLineId",carsByLineId);
        modelAndView.addObject("condition",condition);
        modelAndView.setViewName("line/line_carManage");
        return modelAndView;
    }

    /**
     * 9.线路添加车辆页面
     * @param currentPage
     * @param rows
     * @param carNumber
     * @param carType
     * @param carLoad
     * @param lineId
     * @param changeLineId
     * @return
     */
    @RequestMapping("/carMoveToLineList")
    @ResponseBody
    public ModelAndView carMoveToLineList(String currentPage, String rows,String carNumber,String carType,String carLoad, String lineId,String changeLineId){
        System.out.println("参数lineId："+lineId);
        System.out.println("参数changeLineId："+changeLineId);
        if (currentPage==null||"".equals(currentPage)){
            currentPage="1";
        }
        if (rows==null||"".equals(rows)){
            rows="8";
        }

        Map<String,Object> condition=new HashMap<>();
        condition.put("lineId",lineId);
        condition.put("changeLineId",changeLineId);
        condition.put("carNumber",carNumber);
        condition.put("carType",carType);
        condition.put("carLoad",carLoad);

        System.out.println("返回参数condition："+condition);

        PageBean<CarInfo> carsByLineId = carInfoService.findCarsByLineId(Integer.parseInt(currentPage), Integer.parseInt(rows), condition);
        System.out.println("查询结果carsByLineId："+carsByLineId);


        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("carsByLineId",carsByLineId);
        modelAndView.addObject("condition",condition);
        modelAndView.setViewName("line/line_carAdd");
        return modelAndView;
    }

    /**
     * 10.移出路线车辆
     * @param carId
     * @return
     */
    @RequestMapping("/lineRemoveCar")
    public String lineRemoveCar(String carId,String lineId, String changeLineId){
        System.out.println("参数carId："+carId);
        System.out.println("lineRemoveCar参数lineId："+lineId);
        System.out.println("lineRemoveCar参数changeLineId:"+changeLineId);
        int removeCar = carInfoService.lineRemoveOrJoinCar(Integer.parseInt(carId), Integer.parseInt(lineId));
        System.out.println("移出车辆结果："+removeCar);
        System.out.println("lineRemoveCar!!!!!!!!!!!!!!!!!!!");
        return "redirect:/lineInfo/findCarsByLineId?lineId="+changeLineId;
    }

    /**
     * 11.移出路线车辆
     * @param
     * @return
     */
    @RequestMapping("/lineRemoveCarsSelected")
    public String lineRemoveCarsSelected(@RequestParam("carIds[]") String[] carIds,String lineId, String changeLineId){
        System.out.println("参数carIds："+carIds);
        System.out.println("lineRemoveCarsSelected参数lineId："+lineId);
        System.out.println("lineRemoveCarsSelected参数changeLineId:"+changeLineId);
        for (int i = 0; i < carIds.length; i++) {
            int removeCar = carInfoService.lineRemoveOrJoinCar(Integer.parseInt(carIds[i]), Integer.parseInt(lineId));
            System.out.println("移出车辆结果："+removeCar);
            System.out.println("=====");
        }
        System.out.println("lineRemoveCarsSelected!!!!!!!!!!!!");
        return "redirect:/lineInfo/findCarsByLineId?lineId="+changeLineId;
    }

    /**
     * 12.移入路线车辆
     * @param carId
     * @return
     */
    @RequestMapping("/lineAddCar")
    public String lineAddCar(String carId,String lineId){
        System.out.println("参数carId："+carId);
        System.out.println("lineAddCar参数lineId："+lineId);
        int addCar = carInfoService.lineRemoveOrJoinCar(Integer.parseInt(carId), Integer.parseInt(lineId));
        System.out.println("移入车辆结果："+addCar);
        System.out.println("lineAddCar!!!!!!!!!!!!!!!!!");
        return "redirect:/lineInfo/findCarsByLineId?lineId="+lineId;
    }

    /**
     * 13.移入线路所选车辆
     * @param carIds
     * @param lineId
     * @return
     */
    @RequestMapping("/lineAddCarsSelected")
    public String lineAddCarsSelected(@RequestParam("carIds[]") String[] carIds, String lineId){
        System.out.println("参数carIds："+carIds.toString());
        System.out.println("参数carIds："+carIds);
        System.out.println("lineAddCarsSelected参数lineId:"+lineId);
        for (int i = 0; i < carIds.length; i++) {
            int joinCar = carInfoService.lineRemoveOrJoinCar(Integer.parseInt(carIds[i]), Integer.parseInt(lineId));
            System.out.println("移入车辆结果："+joinCar);
            System.out.println("=====");
        }
        System.out.println("lineAddCarsSelected!!!!!!!!!!!!!!!!!!!!");
        return "redirect:/lineInfo/findCarsByLineId?lineId="+lineId;
    }

    /**
     * 14.查找相同线路的站点
     *
     * @param lineId
     * @return
     */
    @RequestMapping("/findStopsByLineId")
    public ModelAndView findStopsByLineId(String lineId){
        System.out.println("参数lineId："+lineId);

        Map<String,Object> condition=new HashMap<>();
        condition.put("lineId",lineId);
        System.out.println("返回参数condition："+condition);
        List<StopInfo> stopsByLineIdNoPage = stopInfoService.findStopsByLineIdNoPage(Integer.parseInt(lineId));

        System.out.println("查询结果stopsByLineIdNoPage："+stopsByLineIdNoPage);


        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("stopsByLineIdNoPage",stopsByLineIdNoPage);
        modelAndView.addObject("condition",condition);
        modelAndView.setViewName("line/line_stopManage");
        return modelAndView;
    }

    /**
     * 15.显示查找相同线路的站点
     * @param currentPage
     * @param rows
     * @param lineId
     * @return
     */
    @RequestMapping("/showStopsByLineId")
    public ModelAndView showStopsByLineId(String currentPage, String rows, String lineId){
        System.out.println("参数lineId："+lineId);
        if (currentPage==null||"".equals(currentPage)){
            currentPage="1";
        }
        if (rows==null||"".equals(rows)){
            rows="8";
        }

        Map<String,Object> condition=new HashMap<>();
        condition.put("lineId",lineId);
        System.out.println("返回参数condition："+condition);
        PageBean<StopInfo> stopsByLineId = stopInfoService.findStopsByLineId(Integer.parseInt(currentPage), Integer.parseInt(rows), condition);
        System.out.println("查询结果showStopsByLineId："+stopsByLineId);

        List<StopInfo> list = stopsByLineId.getList();
        double[] stopLongitude=new double[list.size()];
        double[] stopLatitude=new double[list.size()];
        for (int i = 0; i < list.size(); i++) {
            StopInfo stopInfo = list.get(i);
           stopLongitude[i]=stopInfo.getStopLongitude();
           stopLatitude[i]=stopInfo.getStopLatitude();
            System.out.println(stopLongitude[i]);
            System.out.println(stopLatitude[i]);
            System.out.println("=========");
        }


        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("stopLongitude",stopLongitude);
        modelAndView.addObject("stopLatitude",stopLatitude);
        modelAndView.setViewName("line/line_stopShow");
        return modelAndView;
    }

    /**
     * 16.站点上移
     * @param stopId
     * @param sCount
     * @param lineId
     * @return
     */
    @RequestMapping("/lineStopUp")
    public String lineStopUp(String stopId,String sCount,String lineId){

        System.out.println("参数lineId："+lineId);
        System.out.println("参数stopId："+stopId);
        /*1.查询需要被交换的站点*/
        List<StopInfo> stopsByLineIdNoPage = stopInfoService.findStopsByLineIdNoPage(Integer.parseInt(lineId));
        StopInfo tStopInfo = stopsByLineIdNoPage.get(Integer.parseInt(sCount));

        /*2获得被下移点的stopId*/
        int tStopId = tStopInfo.getStopId();
        System.out.println("查询返回的tStopId："+tStopId);

        /*3.查询上移站点的index*/
        int index = line_stopService.findIndexByLineIdAndStopId(Integer.parseInt(lineId), Integer.parseInt(stopId));
        System.out.println("参数index："+index);

        /*4.查询被下移站点的index*/
        int tIndex = line_stopService.findIndexByLineIdAndStopId(Integer.parseInt(lineId), tStopId);
        System.out.println("参数tIndex："+tIndex);

        /*5更新被下移点的index为上移点的index*/
        int tUpdate = line_stopService.updateIndexByLineIdAndStopId(Integer.parseInt(lineId), tStopId, index);
        System.out.println("修改结果tUpdate："+tUpdate);

        /*6.更新上移点的index为被下移点的index*/
        int update = line_stopService.updateIndexByLineIdAndStopId(Integer.parseInt(lineId), Integer.parseInt(stopId), tIndex);
        System.out.println("修改结果update："+update);

        return "redirect:/lineInfo/findStopsByLineId?lineId="+lineId;
    }

    /**
     * 17.下移
     * @param stopId
     * @param sCount
     * @param lineId
     * @return
     */
    @RequestMapping("/lineStopDown")
    public String lineStopDown(String stopId,String sCount,String lineId){
        System.out.println("参数lineId："+lineId);
        System.out.println("参数stopId："+stopId);

        /*1.查询需要被交换的站点*/
        List<StopInfo> stopsByLineIdNoPage = stopInfoService.findStopsByLineIdNoPage(Integer.parseInt(lineId));
        StopInfo tStopInfo = stopsByLineIdNoPage.get(Integer.parseInt(sCount));

        /*2获得被上移点的stopId*/
        int tStopId = tStopInfo.getStopId();
        System.out.println("查询返回的tStopId："+tStopId);

        /*3.查询下移站点的index*/
        int index = line_stopService.findIndexByLineIdAndStopId(Integer.parseInt(lineId), Integer.parseInt(stopId));
        System.out.println("参数index："+index);

        /*4.查询被上移站点的index*/
        int tIndex = line_stopService.findIndexByLineIdAndStopId(Integer.parseInt(lineId), tStopId);
        System.out.println("参数tIndex："+tIndex);

        /*5更新被上移点的index为下移点的index*/
        int tUpdate = line_stopService.updateIndexByLineIdAndStopId(Integer.parseInt(lineId), tStopId, index);
        System.out.println("修改结果tUpdate："+tUpdate);

        /*6.更新下移点的index为被上移点的index*/
        int update = line_stopService.updateIndexByLineIdAndStopId(Integer.parseInt(lineId), Integer.parseInt(stopId), tIndex);
        System.out.println("修改结果update："+update);

        return "redirect:/lineInfo/findStopsByLineId?lineId="+lineId;
    }

    /**
     * 18.移出
     * @param stopId
     * @param lineId
     * @return
     */
    @RequestMapping("/lineMoveStop")
    public String lineMoveStop(String stopId,String lineId){
        int lineMoveStop = line_stopService.lineMoveStop(Integer.parseInt(lineId), Integer.parseInt(stopId));
        System.out.println("移出结果lineMoveStop："+lineMoveStop);
        return "redirect:/lineInfo/findStopsByLineId?lineId="+lineId;
    }

    /**
     * 19.移出所选
     * @param stopIds
     * @param lineId
     * @return
     */
    @RequestMapping("/lineMoveStopSelected")
    public String lineMoveStopSelected(@RequestParam("stopIds[]") String[] stopIds, String lineId){

        for (int i = 0; i < stopIds.length; i++) {
            System.out.println("循环stopIds[i]："+stopIds[i]);
            int lineMoveStop = line_stopService.lineMoveStop(Integer.parseInt(lineId), Integer.parseInt(stopIds[i]));
            System.out.println("返回结果lineMoveStop："+lineMoveStop);
            System.out.println("===========");
        }
        return "redirect:/lineInfo/findStopsByLineId?lineId="+lineId;
    }

    /**
     * 20.站点添加列表
     * @param currentPage
     * @param rows
     * @param lineId
     * @return
     */
    @RequestMapping("/stopMoveToLineList")
    @ResponseBody
    public ModelAndView stopMoveToLineList(String currentPage, String rows, String stopName, String stopType, String stopAddress, String lineId){
        System.out.println("参数lineId："+lineId);
        if (currentPage==null||"".equals(currentPage)){
            currentPage="1";
        }
        if (rows==null||"".equals(rows)){
            rows="8";
        }

        Map<String,Object> condition=new HashMap<>();
        condition.put("lineId",lineId);
        condition.put("stopName",stopName);
        condition.put("stopType",stopType);
        condition.put("stopAddress",stopAddress);

        System.out.println("返回参数condition："+condition);

        PageBean<StopInfo> stopsByLineId = stopInfoService.findStopsByLineId(Integer.parseInt(currentPage), Integer.parseInt(rows), condition);
        System.out.println("查询结果showStopsByLineId："+stopsByLineId);

        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("stopsByLineId",stopsByLineId);
        modelAndView.addObject("condition",condition);
        modelAndView.setViewName("line/line_stopAdd");
        return modelAndView;
    }



    /**
     * 21.向线路上增加站点
     * @param stopId
     * @param lineId
     * @return
     */
    @RequestMapping("/lineAddStop")
    public String lineAddStop(String stopId,String lineId){
        System.out.println("参数stopId："+stopId);
        System.out.println("参数lineId："+lineId);
        /*1.获取line_stop表中某个线路的最大idexs*/
        int maxIndex = line_stopService.findMaxIndex(Integer.parseInt(lineId));
        /*2.获得即将要插入index*/
        int idexs=maxIndex+1;
        /*3.插入关系表*/
        int lineAddStop = line_stopService.insertStopByLineIdAndStopId(Integer.parseInt(lineId), Integer.parseInt(stopId), idexs);
        System.out.println("返回结果lineAddStop："+lineAddStop);
        return "redirect:/lineInfo/findStopsByLineId?lineId="+lineId;
    }

    /**
     * 22.向线路上增所选站点
     * @param stopIds
     * @param lineId
     * @return
     */
    @RequestMapping("/lineAddStopSelected")
    public String lineAddStopSelected(@RequestParam("stopIds[]") String[] stopIds, String lineId){
        for (int i = 0; i < stopIds.length; i++) {
            System.out.println("循环stopIds[i]："+stopIds[i]);
            /*1.获取line_stop表中某个线路的最大idexs*/
            int maxIndex = line_stopService.findMaxIndex(Integer.parseInt(lineId));
            /*2.获得即将要插入index*/
            int idexs=maxIndex+1;
            /*3.插入关系表*/
            int lineAddStop = line_stopService.insertStopByLineIdAndStopId(Integer.parseInt(lineId), Integer.parseInt(stopIds[i]), idexs);
            System.out.println("返回结果lineAddStop："+lineAddStop);
            System.out.println("=======");
        }

        return "redirect:/lineInfo/findStopsByLineId?lineId="+lineId;
    }
}
