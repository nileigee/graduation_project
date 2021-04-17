<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>

<html>
<head>
    <title>登录</title>
    <!--更好的响应式支持-->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--引入bootstrap核心css文件-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/boot/css/bootstrap.min.css">
    <!--引入jqgrid核心css文件-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/boot/grid/ui.jqgrid-bootstrap.css">
    <!--引入jQuery核心js文件-->
    <script src="${pageContext.request.contextPath}/boot/js/jquery-3.5.1.min.js"></script>
    <!--引入jqgrid核心js文件-->
    <script src="${pageContext.request.contextPath}/boot/grid/jquery.jqGrid.min.js"></script>
    <!--引入jqgrid国际化核心js文件-->
    <script src="${pageContext.request.contextPath}/boot/grid/grid.locale-cn.js"></script>
    <!--引入bootstrap核心js文件-->
    <script src="${pageContext.request.contextPath}/boot/js/bootstrap.min.js"></script>
    <script>
        function refreshCode() {
            document.getElementById("vcode").onclick=function () {
                this.src="${pageContext.request.contextPath}/checkCodeController/checkCode?time="+new Date().getTime();
            }
        }
    </script>
</head>
<body>
<div class="container" style="width: 400px;">
    <h3 style="text-align: center;">管理员登录</h3>
    <form action="${pageContext.request.contextPath}/userInfo/userLogin" method="post">
        <div class="form-group">
            <label for="userName">用户名：</label>
            <input type="text" name="userName" class="form-control" id="userName" placeholder="请输入用户名"/>
        </div>

        <div class="form-group">
            <label for="password">密码：</label>
            <input type="password" name="password" class="form-control" id="password" placeholder="请输入密码"/>
        </div>

        <div class="form-inline">
            <label for="vcode">验证码：</label>
            <input type="text" name="inputCheckCode" class="form-control" id="inputCheckCode" placeholder="请输入验证码" style="width: 120px;"/>
            <a href="javascript:refreshCode()">
                <img class="passcode" src="${pageContext.request.contextPath}/checkCodeController/checkCode" title="看不清点击刷新" id="vcode"/>
            </a>
        </div>
        <hr/>
        <div class="form-group" style="text-align: center;">
            <input class="btn btn btn-primary" type="submit" value="登录">
        </div>
    </form>

    <!-- 出错显示的信息框 -->
    <div class="alert alert-warning alert-dismissible" role="alert">
        <button type="button" class="close" data-dismiss="alert" >
            <span>&times;</span> </button>
        <strong>
            ${message}
            <%= request.getAttribute("login_error") == null ?"" :request.getAttribute("login_error")%>

        </strong>
    </div>
</div>
</body>
</html>
