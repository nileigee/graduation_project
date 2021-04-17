<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>

<div class="col-sm-10">
    <div class="container" style="width: 400px;">
        <h3 style="text-align: center;">修改工作人员信息</h3>
        <form action="${pageContext.request.contextPath}/updateUserServlet" method="post">
            <!--隐藏域用于获得user的id-->
            <input type="hidden" name="workerId" id="workerId" value="${workerById.workerId}" >
            <div class="form-group">
                <label for="workerName">姓名：</label>
                <input type="text" class="form-control" id="workerName" name="workerName" value="${workerById.workerName}">
            </div>

            <div class="form-group">
                <label for="workerGender">性别：</label>
                <select class="form-control" id="workerGender" name="workerGender">
                    <option>男</option>
                    <option>女</option>
                </select>
            </div>

            <div class="form-group">
                <label for="workerAge">年龄：</label>
                <input type="text" class="form-control" id="workerAge" name="workerAge" value="${workerById.workerAge}">
            </div>

            <div class="form-group">
                <label for="workerPhone">联系方式：</label>
                <input type="text" class="form-control" id="workerPhone" name="workerPhone" value="${workerById.workerPhone}">
            </div>

            <div class="form-group">
                <label for="workerType">工种：</label>
                <select class="form-control" id="workerType" name="workerType">
                    <option>司机</option>
                    <option>线路负责人</option>
                    <option>存放站点负责人</option>
                    <option>中转站点负责人</option>
                </select>
            </div>

            <div class="form-group">
                <label for="workerAddress">住址：</label>
                <input type="text" class="form-control" id="workerAddress" name="workerAddress" value="${workerById.workerAddress}">
            </div>

            <div class="form-group">
                <label for="workerStatus">状态</label>
                <select class="form-control" id="workerStatus" name="workerStatus">
                    <option>在岗</option>
                    <option>离岗</option>
                </select>
            </div>

            <div class="form-group">
                <label for="workerDescribe">工作人员描述：</label>
                <textarea class="form-control" id="workerDescribe" name="workerDescribe">${workerById.workerDescribe}
                </textarea>
            </div>

            <div class="form-group" style="text-align: center">
                <a class="btn btn-primary btn-sm" style="width: 100%;line-height: 25px" type="submit"
                   href="javascript:$('#content').load('${pageContext.request.contextPath}/workerInfo/updateWorker',
                {'workerId':$('#workerId').val(),'workerName':$('#workerName').val(),'workerGender':$('#workerGender').val(),'workerAge':$('#workerAge').val(),'workerPhone':$('#workerPhone').val(),
                 'workerAddress':$('#workerAddress').val(),'workerType':$('#workerType').val(),'workerStatus':$('#workerStatus').val(),'workerDescribe':$('#workerDescribe').val()});">修改</a>
            </div>


        </form>
    </div>
</div>