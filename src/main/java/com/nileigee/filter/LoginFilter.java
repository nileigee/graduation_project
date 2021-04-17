package com.nileigee.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        System.out.println(req);
        //0.强制转换
        HttpServletRequest request=(HttpServletRequest)req;

        //1.获取资源的请求路径
        String uri = request.getRequestURI();
        //2.判断是否含有登录相关资源路径,注意要排除掉css/js/图片/验证码等资源
        if(uri.contains("/login.jsp") || uri.contains("/userLogin") || uri.contains("/boot/css/") || uri.contains("/boot/js/") || uri.contains("/boot/fonts/") || uri.contains("/checkCode")){
            //包含，证明用户就是想登录
            chain.doFilter(req, resp);
        }else{
            //不包含，需要验证用户是否登录
            //3.从session中获取user
            Object backUser = request.getSession().getAttribute("backUser");
            if(backUser != null){
                //登陆了，放行
                chain.doFilter(req, resp);
            }else {
                //没登陆，转到登录页面
                request.setAttribute("login_msg","您尚未登录，请登录！");
                request.getRequestDispatcher("/back/login.jsp").forward(request,resp);
            }
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

    public void destroy() {
    }

}
