<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>

<div class="col-sm-10">
    <div class="container">
        <center><h3>添加联系人页面</h3></center>
        <form>
            <div class="form-group" >
                <label for="carNumber">车牌号：</label>
                <input type="text" class="form-control" id="carNumber" name="carNumber" placeholder="请输入车牌号">
            </div>

            <div class="form-group">
                <label for="carColor">车辆颜色：</label>
                <input type="text" class="form-control" id="carColor" name="carColor" placeholder="请输入车辆颜色">
            </div>

            <div class="form-group">
                <label for="carType">车辆类型：</label>
                <input type="text" class="form-control" id="carType" name="carType" placeholder="请输入车辆类型">
            </div>

            <div class="form-group">
                <label for="carLoad">载重：</label>
                <input type="text" class="form-control" id="carLoad" name="carLoad" placeholder="请输入车辆载重">
            </div>

            <div class="form-group">
                <label for="carStatus">运行状态</label>
                <select class="form-control" id="carStatus" name="carStatus">
                    <option>运行</option>
                    <option>停运</option>
                </select>
            </div>

            <div class="form-group">
                <label for="workerId">司机</label>
                    <select class="form-control" id="workerId" name="workerId">
                        <c:forEach items="${allDrivers}" var="driver" varStatus="s">
                        <option value="${driver.workerId}">${driver.workerName}</option>
                        </c:forEach>
                    </select>
            </div>

            <div class="form-group">
                <label for="carDescribe">车辆描述：</label>
                <textarea class="form-control" id="carDescribe" name="carDescribe" placeholder="请输入对车辆的描述">
                </textarea>
            </div>
                <a class="btn btn-primary btn-sm" type="submit"
                   href="javascript:$('#content').load('${pageContext.request.contextPath}/carInfo/saveCar',{'carNumber':$('#carNumber').val(),'carColor':$('#carColor').val(),'carType':$('#carType').val(),'carLoad':$('#carLoad').val(),
                   'carStatus':$('#carStatus').val(),'workerId':$('#workerId').val(),'carDescribe':$('#carDescribe').val()});">添加</a>
        </form>
    </div>
</div>