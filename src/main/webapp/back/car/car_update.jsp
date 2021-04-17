<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>

<div class="col-sm-10">
    <div class="container" style="width: 400px;">
        <h3 style="text-align: center;">修改联系人</h3>
        <form>
            <!--隐藏域用于获得user的id-->
            <div class="form-group">
            <input type="hidden" id="carId" name="carId" value="${carById.carId}" >
            </div>
            <div class="form-group">
                <label for="carNumber">车牌号：</label>
                <input type="text" class="form-control" id="carNumber" name="carNumber" value="${carById.carNumber}">
            </div>

            <div class="form-group">
                <label for="carColor">车辆颜色：</label>
                <input type="text" class="form-control" id="carColor" name="carColor" value="${carById.carColor}" >
            </div>

            <div class="form-group">
                <label for="carType">车辆类型：</label>
                <input type="text" class="form-control" id="carType" name="carType" value="${carById.carType}">
            </div>

            <div class="form-group">
                <label for="carLoad">载重：</label>
                <input type="text" class="form-control" id="carLoad" name="carLoad" value="${carById.carLoad}">
            </div>

            <div class="form-group">
                <label for="workerId">司机</label>
                <select class="form-control" id="workerId" name="workerId">
                    <c:forEach items="${allDriver}" var="driver" varStatus="s">
                        <option value="${driver.workerId}">${driver.workerName}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-group">
                <label for="carStatus">运行状态</label>
                <select class="form-control" id="carStatus" name="carStatus">
                    <option>运行</option>
                    <option>停运</option>
                </select>
            </div>

            <div class="form-group">
                <label for="carDescribe">车辆描述：</label>
                <textarea class="form-control" id="carDescribe" name="carDescribe">${carById.carDescribe}
                </textarea>
            </div>

            <a class="btn btn-primary btn-sm" type="submit" href="javascript:$('#content').load('${pageContext.request.contextPath}/carInfo/updateCar',{'carId':$('#carId').val(),'carNumber':$('#carNumber').val(),'carColor':$('#carColor').val(),'carType':$('#carType').val(),'carLoad':$('#carLoad').val(),
                   'carStatus':$('#carStatus').val(),'workerId':$('#workerId').val(),'carDescribe':$('#carDescribe').val()});">提交</a>

        </form>
    </div>
</div>