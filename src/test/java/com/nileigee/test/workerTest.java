package com.nileigee.test;

import com.nileigee.entity.CarInfo;
import com.nileigee.entity.PageBean;
import com.nileigee.entity.WorkerInfo;
import com.nileigee.service.CarInfoService;
import com.nileigee.service.WorkerInfoService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.List;

public class workerTest {
    @Test
    public void test1() throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
       WorkerInfoService workerInfoService = (WorkerInfoService) context.getBean("workerInfoService");
        List<WorkerInfo> allDrivers = workerInfoService.findAllDriver("司机");
        System.out.println(allDrivers);
    }

    @Test
    public void test2() throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        CarInfoService carInfoService = (CarInfoService)context.getBean("carInfoService");
        int deleteCar = carInfoService.deleteCar(15);
        System.out.println(deleteCar);
    }

    @Test
    public void test3() throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        CarInfoService carInfoService = (CarInfoService)context.getBean("carInfoService");
        PageBean<CarInfo> carByPage = carInfoService.findCarByPage(1, 5);
        System.out.println(carByPage);
    }
}
