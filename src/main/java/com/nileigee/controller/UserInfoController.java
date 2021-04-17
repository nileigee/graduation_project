package com.nileigee.controller;

import com.nileigee.entity.UserInfo;
import com.nileigee.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/userInfo")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    /**
     * 1.登录
     * @param userName
     * @param password
     * @return
     */
    @RequestMapping("/userLogin")
    @ResponseBody
    public ModelAndView login(String userName, String password, String inputCheckCode,HttpServletRequest request){
        System.out.println("userName:"+userName);
        System.out.println("password:"+password);
        //输入的验证码
        System.out.println("inputCheckCode:"+inputCheckCode);
        //前端生成的验证码
        HttpSession session = request.getSession();
        String randomCodeKey = (String)session.getAttribute("randomcode_key");
        System.out.println("randomCodeKey:"+randomCodeKey);
        //将前端输入的用户名和密码封装成userInfo对象
        UserInfo loginUser=new UserInfo();
        loginUser.setUserName(userName);
        loginUser.setPassword(password);

        UserInfo backUser = userInfoService.findUserByNameAndPassword(loginUser);
        System.out.println("backUser:"+backUser);
        ModelAndView modelAndView=new ModelAndView();

        if(randomCodeKey!=null&&randomCodeKey.equalsIgnoreCase(inputCheckCode)){
            //验证码正确

            if(backUser==null){
                //登录失败
                modelAndView.addObject("message","用户名或密码输入错误！");
                modelAndView.setViewName("login");

            }else {
                //登录成功
                session.setAttribute("backUser",backUser);
                modelAndView.addObject("backUser",backUser);
                modelAndView.setViewName("index");
            }


        }else {
            //验证码不一致
            modelAndView.addObject("message","验证码输入错误！");
            modelAndView.setViewName("login");
        }



            return modelAndView;
    }


}
