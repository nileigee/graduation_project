<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>

<div class="col-sm-10">
    <div class="container">
        <center><h3>添加工作人员页面</h3></center>
        <form  role="form">
            <div class="form-group">
                <label for="workerName">姓名：</label>
                <input type="text" class="form-control" id="workerName" name="workerName" placeholder="请输入姓名">
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
                <input type="text" class="form-control" id="workerAge" name="workerAge" placeholder="请输入年龄">
            </div>

            <div class="form-group">
                <label for="workerPhone">联系方式：</label>
                <input type="text" class="form-control" id="workerPhone" name="workerPhone" placeholder="请输入联系方式">
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
                <input type="text" class="form-control" id="workerAddress" name="workerAddress" placeholder="请输入住址">
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
                <textarea class="form-control" id="workerDescribe" name="workerDescribe" placeholder="请输入对车辆的描述">
                </textarea>
            </div>

            <div class="form-group" >
                <a class="btn btn-primary btn-sm" style="width: 100%;line-height: 25px" type="submit"
                   href="javascript:$('#content').load('${pageContext.request.contextPath}/workerInfo/saveWorker',
                {'workerName':$('#workerName').val(),'workerGender':$('#workerGender').val(),'workerAge':$('#workerAge').val(),'workerPhone':$('#workerPhone').val(),
                 'workerAddress':$('#workerAddress').val(),'workerType':$('#workerType').val(),'workerStatus':$('#workerStatus').val(),'workerDescribe':$('#workerDescribe').val()});">添加</a>
            </div>

        </form>
    </div>
</div>
