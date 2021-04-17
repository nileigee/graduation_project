package com.nileigee.controller;

import com.nileigee.entity.PageBean;
import com.nileigee.entity.StopInfo;
import com.nileigee.entity.TransferInfo;
import com.nileigee.entity.WorkerInfo;
import com.nileigee.service.TransferInfoService;
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
@RequestMapping("/transferInfo")
public class TransferInfoController {

    @Autowired
    private TransferInfoService transferInfoService;
    @Autowired
    private WorkerInfoService workerInfoService;

    /**
     * 1.根据条件分页查询数据
     * @param currentPage
     * @param rows
     * @param transferName
     * @param transferType
     * @param transferAddress
     * @return
     */
    @RequestMapping("/findTransferByCondition")
    @ResponseBody
    public ModelAndView findTransferByCondition(String currentPage, String rows,String transferName,String transferType,String transferAddress){
        System.out.println("参数currentPage:"+currentPage);
        System.out.println("参数rows:"+rows);
        System.out.println("参数transferName:"+transferName);
        System.out.println("参数transferType:"+transferType);
        System.out.println("参数transferAddress:"+transferAddress);
        Map<String, Object> condition=new HashMap<>();
        condition.put("transferName",transferName);
        condition.put("transferType",transferType);
        condition.put("transferAddress",transferAddress);
        System.out.println("参数condition:"+condition);
        if (currentPage==null||"".equals(currentPage)){
            currentPage="1";
        }
        if (rows==null||"".equals(rows)){
            rows="5";
        }

        PageBean<TransferInfo> transferByPage = transferInfoService.findTransferByCondition(Integer.parseInt(currentPage), Integer.parseInt(rows), condition);
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("transferByPage",transferByPage);
        modelAndView.addObject("condition",condition);
        modelAndView.setViewName("transfer/transfer_list");
        return modelAndView;
    }

    /**
     * 2.保存中转站
     * @param transferInfo
     * @return
     */
    @RequestMapping("/saveTransfer")
    public String saveTransfer(TransferInfo transferInfo){
        System.out.println("参数transferInfo："+transferInfo);
        int saveTransfer = transferInfoService.saveTransfer(transferInfo);
        System.out.println("返回保存结果saveTransfer:"+saveTransfer);
        return "redirect:/transferInfo/findTransferByCondition";
    }

    /**
     * 3.根据id查中转站和工作者
     * @param transferId
     * @return
     */
    @RequestMapping("/findTransferById")
    @ResponseBody
    public ModelAndView findTransferById(String transferId){
        System.out.println("参数transferId："+transferId);
        TransferInfo transferById = transferInfoService.findTransferById(Integer.parseInt(transferId));
        System.out.println("返回的transferById："+transferById);
        List<WorkerInfo> transferWorkerList = workerInfoService.findAllDriver("中转站点负责人");
        System.out.println("查询的负责人列表transferWorkerList："+transferWorkerList);

        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("transferById",transferById);
        modelAndView.addObject("transferWorkerList",transferWorkerList);
        modelAndView.setViewName("transfer/transfer_update");
        return modelAndView;
    }

    /**
     * 4.修改车辆信息
     * @param transferInfo
     * @return
     */
    @RequestMapping("/updateTransfer")
    public String updateTransfer(TransferInfo transferInfo){
        System.out.println("参数transferInfo："+transferInfo);
        int updateTransfer = transferInfoService.updateTransfer(transferInfo);
        System.out.println("返回更新结果updateTransfer："+updateTransfer);
        return "redirect:/transferInfo/findTransferByCondition";
    }

    /**
     * 5.删除某个
     * @param transferId
     * @return
     */
    @RequestMapping("/deleteTransfer")
    public String deleteTransfer(String transferId){
        System.out.println("参数transferId："+transferId);
        int deleteTransfer = transferInfoService.deleteTransfer(Integer.parseInt(transferId));
        System.out.println("删除结果deleteTransfer："+deleteTransfer);
        return "redirect:/transferInfo/findTransferByCondition";
    }

    /**
     * 6.删除所选
     * @param
     * @return
     */
    @RequestMapping("/deleteTransferSelected")
    public String deleteTransferSelected(@RequestParam("transferIds[]") String[] transferIds){
        System.out.println("参数transferId："+transferIds.toString());
        for (int i = 0; i < transferIds.length; i++) {
            int deleteTransfers = transferInfoService.deleteTransfer(Integer.parseInt(transferIds[i]));
            System.out.println("删除结果deleteTransfers："+deleteTransfers);
            System.out.println("====");
        }

        return "redirect:/transferInfo/findTransferByCondition";
    }
}
