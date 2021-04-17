package com.nileigee.controller;

import com.nileigee.utils.RandomValidateCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@Controller
@RequestMapping("/checkCodeController")
public class CheckCodeController {

    @RequestMapping("/checkCode")
    @ResponseBody
    public void checkCode(HttpServletRequest request, HttpServletResponse response) throws IOException {

            //设置相应类型,告诉浏览器输出的内容为图片
            response.setContentType("image/jpeg");

            //设置响应头信息，告诉浏览器不要缓存此内容
            response.setHeader("pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expire", 0);

            RandomValidateCode randomValidateCode = new RandomValidateCode();
            try {
                randomValidateCode.getRandcode(request, response);//输出图片方法
            } catch (Exception e) {
                e.printStackTrace();
            }

    }

}
