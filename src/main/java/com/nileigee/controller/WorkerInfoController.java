package com.nileigee.controller;

import com.nileigee.entity.PageBean;
import com.nileigee.entity.WorkerInfo;
import com.nileigee.service.WorkerInfoService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/workerInfo")
public class WorkerInfoController {

    @Autowired
    private WorkerInfoService workerInfoService;

    /**
     * 1.查询所有司机类型的工作人员
     *
     */
    @RequestMapping("/findAllDriver")
    @ResponseBody
    public ModelAndView findAllDriver() {
        String workerType="司机";
        List<WorkerInfo> allDrivers = workerInfoService.findAllDriver(workerType);

        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("allDrivers",allDrivers);
        modelAndView.setViewName("car/car_add");
        return modelAndView;
    }

    /**
     * 2.查询所有存放站点负责人
     * @return
     */
    @RequestMapping("/findAllStopWorkers")
    @ResponseBody
    public ModelAndView findAllStopWorkers() {
        String workerType = "存放站点负责人";
        List<WorkerInfo> allStopWorkers = workerInfoService.findAllDriver(workerType);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("allStopWorkers", allStopWorkers);
        modelAndView.setViewName("stop/stop_add");
        return modelAndView;
    }

    /**
     * 3.查询所有存放站点负责人
     * @return
     */
    @RequestMapping("/findAllTransferWorkers")
    @ResponseBody
    public ModelAndView findAllTransferWorkers() {
        String workerType = "中转站点负责人";
        List<WorkerInfo> allTransferWorkers = workerInfoService.findAllDriver(workerType);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("allTransferWorkers", allTransferWorkers);
        modelAndView.setViewName("transfer/transfer_add");
        return modelAndView;
    }

    /**
     * 3.查询所有线路负责人
     * @return
     */
    @RequestMapping("/findAllLineWorkers")
    @ResponseBody
    public ModelAndView findAllLineWorkers(){
        String workerType="线路负责人";
        List<WorkerInfo> allLineWorkers = workerInfoService.findAllDriver(workerType);

        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("allLineWorkers",allLineWorkers);
        modelAndView.setViewName("line/line_add");
        return modelAndView;
    }


    /**
     * 4.查询数据
     * @param currentPage
     * @param rows
     * @param workerName
     * @param workerPhone
     * @param workerType
     * @return
     */
    @RequestMapping("/findWorkerByCondition")
    @ResponseBody
    public ModelAndView findWorkerByCondition(String currentPage, String rows,String workerName,String workerPhone,String workerType){
        System.out.println("参数currentPage:"+currentPage);
        System.out.println("参数rows:"+rows);
        System.out.println("参数workerName:"+workerName);
        System.out.println("参数workerPhone:"+workerPhone);
        System.out.println("参数workerType:"+workerType);
        Map<String, Object> condition=new HashMap<>();
        condition.put("workerName",workerName);
        condition.put("workerPhone",workerPhone);
        condition.put("workerType",workerType);
        System.out.println("参数condition:"+condition);
        if (currentPage==null||"".equals(currentPage)){
            currentPage="1";
        }
        if (rows==null||"".equals(rows)){
            rows="5";
        }

        PageBean<WorkerInfo> workerByPage = workerInfoService.findWorkerByCondition(Integer.parseInt(currentPage), Integer.parseInt(rows), condition);
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("workerByPage",workerByPage);
        modelAndView.addObject("condition",condition);
        modelAndView.setViewName("worker/worker_list");
        return modelAndView;
    }

    /**
     * 5.保存人员
     * @param workerInfo
     * @return
     */
    @RequestMapping("/saveWorker")
    public String saveWorker(WorkerInfo workerInfo){
        System.out.println("参数workerInfo："+workerInfo);
        int saveWorker = workerInfoService.saveWorker(workerInfo);
        System.out.println("返回保存结果saveWorker:"+saveWorker);
        return "redirect:/workerInfo/findWorkerByCondition";
    }

    /**
     * 6.根据id查询
     * @param workerId
     * @return
     */
    @RequestMapping("/findWorkerById")
    @ResponseBody
    public ModelAndView findWorkerById(String workerId){
        System.out.println("参数workerId："+workerId);
        WorkerInfo workerById = workerInfoService.findWorkerById(Integer.parseInt(workerId));
        System.out.println("返回查询结果workerById："+workerById);

        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("workerById",workerById);
        modelAndView.setViewName("worker/worker_update");
        return modelAndView;
    }

    /**
     * 7.修改
     * @param workerInfo
     * @return
     */
    @RequestMapping("/updateWorker")
    public String updateWorker(WorkerInfo workerInfo){
        System.out.println("参数workerInfo："+workerInfo);
        int updateWorker = workerInfoService.updateWorker(workerInfo);
        System.out.println("updateWorker："+updateWorker);
        return "redirect:/workerInfo/findWorkerByCondition";
    }

    /**
     * 8.删除
     * @param workerId
     * @return
     */
    @RequestMapping("/deleteWorker")
    public String deleteWorker(String workerId){
        System.out.println("参数workerId："+workerId);
        int deleteWorker = workerInfoService.deleteWorker(Integer.parseInt(workerId));
        System.out.println("deleteWorker："+deleteWorker);
        return "redirect:/workerInfo/findWorkerByCondition";
    }

    /**
     * 9.删除所选
     * @param
     * @return
     */
    @RequestMapping("/deleteWorkerSelected")
    public String deleteWorkerSelected(@RequestParam("workerIds[]") String[] workerIds){
        for (int i = 0; i < workerIds.length; i++) {
            int deleteWorker = workerInfoService.deleteWorker(Integer.parseInt(workerIds[i]));
            System.out.println("返回删除所选结果deleteWorker："+deleteWorker);
            System.out.println("======");
        }


        return "redirect:/workerInfo/findWorkerByCondition";
    }
}
