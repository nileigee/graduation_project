package com.nileigee.controller;

import com.nileigee.entity.PageBean;
import com.nileigee.entity.StopInfo;
import com.nileigee.entity.WorkerInfo;
import com.nileigee.service.StopInfoService;
import com.nileigee.service.WorkerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/stopInfo")
public class StopInfoController {

    @Autowired
    private StopInfoService stopInfoService;
    @Autowired
    private WorkerInfoService workerInfoService;

    /**
     * 1.站点分页查询
     * @param currentPage
     * @param rows
     * @return
     */
    @RequestMapping("/findStopByPage")
    @ResponseBody
    public ModelAndView findStopByPage(String currentPage,String rows){
        System.out.println("currentPage:"+currentPage);
        System.out.println("rows:"+rows);
        if (currentPage==null||"".equals(currentPage)){
            currentPage="1";
        }
        if (rows==null||"".equals(rows)){
            rows="5";
        }
        System.out.println("currentPage1:"+currentPage);
        System.out.println("rows1:"+rows);
        PageBean<StopInfo> stopByPage = stopInfoService.findStopByPage(Integer.parseInt(currentPage), Integer.parseInt(rows));
        System.out.println("stopByPage"+stopByPage);
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("stopByPage",stopByPage);
        modelAndView.setViewName("stop/stop_list");
        return modelAndView;
    }

    /**
     * 2.保存车辆
     * @param stopInfo
     * @return
     */
    @RequestMapping("/saveStop")
    public String saveStop(StopInfo stopInfo){
        System.out.println("传入的参数stopInfo："+stopInfo);
        int saveStop = stopInfoService.saveStop(stopInfo);
        System.out.println("saveStop结果："+saveStop);
        return "redirect:/stopInfo/findStopByCondition";
    }

    /**
     * 3.根据id查找存放站点和工作者
     * @param stopId
     * @return
     */
    @RequestMapping("/findStopByIdAndWorkers")
    @ResponseBody
    public ModelAndView findStopByIdAndWorkers(String stopId){
        System.out.println("参数stopId:"+stopId);
        StopInfo stopById = stopInfoService.findStopById(Integer.parseInt(stopId));
        System.out.println("stopById:"+stopById);
        List<WorkerInfo> stopWorkerList = workerInfoService.findAllDriver("存放站点负责人");
        System.out.println("stopWorkerList:"+stopWorkerList);

        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("stopById",stopById);
        modelAndView.addObject("stopWorkerList",stopWorkerList);
        modelAndView.setViewName("stop/stop_update");
        return modelAndView;
    }

    /**
     * 4.修改站点信息
     * @param stopInfo
     * @return
     */
    @RequestMapping("/updateStop")
    public String updateStop(StopInfo stopInfo){
        System.out.println("更新参数stopInfo："+stopInfo);
        int updateStop = stopInfoService.updateStop(stopInfo);
        System.out.println("updateStop结果："+updateStop);
        return "redirect:/stopInfo/findStopByCondition";
    }

    /**
     * 5.删除站点
     * @param stopId
     * @return
     */
    @RequestMapping("/deleteStop")
    public String deleteStop(String stopId){
        System.out.println("更新参数stopId："+stopId);
        int deleteStop = stopInfoService.deleteStop(Integer.parseInt(stopId));
        System.out.println("结果deleteStop："+deleteStop);
        return "redirect:/stopInfo/findStopByCondition";
    }

    /**
     * 6.删除所选的站点
     * @return
     */
    @RequestMapping("/deleteStopSelected")
    public String deleteStopSelected(@RequestParam("stopIds[]") String[] stopIds){
        System.out.println(stopIds.toString());
        for (int i = 0; i < stopIds.length; i++) {
            int deleteStop = stopInfoService.deleteStop(Integer.parseInt(stopIds[i]));
            System.out.println("删除结果deleteStop："+deleteStop);
            System.out.println("=========");
        }

        return "redirect:/stopInfo/findStopByCondition";
    }

    /**
     * 7.根据条件查找站点
     *
     * @return
     */
    @RequestMapping("/findStopByCondition")
    @ResponseBody
    public ModelAndView findStopByCondition(String currentPage, String rows,String stopName,String stopType,String stopAddress){
        System.out.println("参数currentPage:"+currentPage);
        System.out.println("参数rows:"+rows);
        System.out.println("参数stopName:"+stopName);
        System.out.println("参数stopType:"+stopType);
        System.out.println("参数stopAddress:"+stopAddress);
        Map<String, Object> condition=new HashMap<>();
        condition.put("stopName",stopName);
        condition.put("stopType",stopType);
        condition.put("stopAddress",stopAddress);
        System.out.println("参数condition:"+condition);
        if (currentPage==null||"".equals(currentPage)){
            currentPage="1";
        }
        if (rows==null||"".equals(rows)){
            rows="5";
        }

        PageBean<StopInfo> stopByPage = stopInfoService.findStopByCondition(Integer.parseInt(currentPage), Integer.parseInt(rows), condition);
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("stopByPage",stopByPage);
        modelAndView.addObject("condition",condition);
        modelAndView.setViewName("stop/stop_list");
        return modelAndView;
    }
}
