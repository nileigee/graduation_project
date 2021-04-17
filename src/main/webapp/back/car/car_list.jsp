<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>

<div class="col-sm-10 bg-success">
    <div class="container" style="margin-left: 0px;width: 100%;height: 100%;">
        <div class="page-header text-center" style="height: 40px;margin-top: 10px" >
            <h1><span class="text-primary" style="font-style: initial">运收车辆管理</span></h1>
        </div>
        <div style="float: left;margin-bottom: 10px">
            <form class="form-inline" id="queryCarForm">
                <div class="form-group">
                    <label for="carNumber"><span style="font-size: small">车牌号：</span></label>
                    <input type="text" class="form-control" id="carNumber" name="carNumber" value="${condition.carNumber}">
                </div>
                <div class="form-group">
                    <label for="carType"><span style="font-size: small">车辆类型: </span></label>
                    <input type="text" class="form-control" id="carType" name="carType" value="${condition.carType}">

                </div>
                <div class="form-group">
                    <label for="carLoad"><span style="font-size: small">载重：</span></label>
                    <input type="text" class="form-control" id="carLoad" name="carLoad" value="${condition.carLoad}">
                </div>

                    <%--<button type="submit" class="btn btn-primary btn-sm" style="margin-left: 8px">查询</button>--%>
                <a class="btn btn-primary btn-sm" type="submit" href="javascript:$('#content').load('${pageContext.request.contextPath}/carInfo/findCarPageByCondition',
                {'carNumber':$('#carNumber').val(),'carType':$('#carType').val(),'carLoad':$('#carLoad').val()});"  style="margin-left: 8px">查询</a>

            </form>


        </div>

        <div style="float: left;margin-left: 120px">
            <a class="btn btn-primary btn-sm" href="javascript:$('#content').load('${pageContext.request.contextPath}/workerInfo/findAllDriver');" id="btnAddCar">添加车辆</a>&nbsp;&nbsp;&nbsp;
            <a class="btn btn-primary btn-sm" href="javascript:void(0);" id="delCarsSelected" >删除选中</a>
            <script>
                $("#delCarsSelected").click(function(){
                    var carIds=new Array();

                    $.each($('input:checkbox:checked'),function(){
                        carIds.push($(this).val());
                    });

                    if(confirm("您确认要将所选线路删除吗？")){
                        $('#content').load('${pageContext.request.contextPath}/carInfo/deleteCarSelected',{'carIds[]':carIds});
                    }

                });
            </script>
        </div>

        <form method="post" id="selectedForm">
            <table border="1" class="table table-bordered table-hover table-striped">
                <thead>
                <tr >
                    <th class="text-center"><input type="checkbox" id="firstCb"></th>
                    <th class="text-center">编号</th>
                    <th class="text-center">车牌号</th>
                    <th class="text-center">颜色</th>
                    <th class="text-center">车辆类型</th>
                    <th class="text-center">载重</th>
                    <th class="text-center">司机</th>
                    <th class="text-center">司机电话</th>
                    <th class="text-center">运行状态</th>
                    <th class="text-center">车辆描述</th>
                    <th class="text-center">操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${carByPage.list}" var="carInfo" varStatus="s">
                    <tr>
                        <td class="text-center"><input type="checkbox" name="carIds" value="${carInfo.carId}"></td>
                        <td class="text-center">${s.count}</td>
                        <td class="text-center">${carInfo.carNumber}</td>
                        <td class="text-center">${carInfo.carColor}</td>
                        <td class="text-center">${carInfo.carType}</td>
                        <td class="text-center">${carInfo.carLoad}</td>
                        <td class="text-center">${carInfo.workerInfo.workerName}</td>
                        <td class="text-center">${carInfo.workerInfo.workerPhone}</td>
                        <td class="text-center">${carInfo.carStatus}</td>
                        <td class="text-center">${carInfo.carDescribe}</td>
                        <td class="text-center"><a class="btn btn-primary btn-sm" href="javascript:$('#content').load('${pageContext.request.contextPath}/carInfo/findCarAndDriverForUpdate',{'carId':'${carInfo.carId}'});" id="btnUpdateCar">修改</a>
                            &nbsp;<a class="btn btn-primary btn-sm" href="javascript:void(0);" onclick="btDeleteCar('${carInfo.carId}')">删除</a>
                            <script>
                                function btDeleteCar(carId) {
                                    if (confirm("您确认要删除吗？")){
                                        $("#content").load("${pageContext.request.contextPath}/carInfo/deleteCar",{"carId":carId});
                                    }
                                  }
                            </script>
                        </td>
                    </tr>
                </c:forEach>

                </tbody>



            </table>
        </form>
        <!--分页工具条-->
        <div class="text-center">
            <nav aria-label="Page navigation">
                <ul class="pagination">
                    <c:if test="${carByPage.currentPage == 1}">
                        <li class="disabled">
                    </c:if>
                    <c:if test="${carByPage.currentPage != 1}">
                        <li>
                    </c:if>
                        <a href="javascript:$('#content').load('${pageContext.request.contextPath}/carInfo/findCarPageByCondition',
                        {'currentPage':'${carByPage.currentPage-1}','rows':5,'carNumber':'${condition.carNumber}','carType':'${condition.carType}','carLoad':'${condition.carLoad}'});" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>

                    <c:forEach  begin="1" end="${carByPage.totalPage}" var="i">
                        <c:if test="${carByPage.currentPage ==i}">
                            <li class="active"><a href="javascript:$('#content').load('${pageContext.request.contextPath}/carInfo/findCarPageByCondition',
                            {'currentPage':'${i}','rows':5,'carNumber':'${condition.carNumber}','carType':'${condition.carType}','carLoad':'${condition.carLoad}'});">${i}</a></li>
                        </c:if>
                        <c:if test="${carByPage.currentPage !=i}">
                            <li><a href="javascript:$('#content').load('${pageContext.request.contextPath}/carInfo/findCarPageByCondition',
                            {'currentPage':'${i}','rows':5,'carNumber':'${condition.carNumber}','carType':'${condition.carType}','carLoad':'${condition.carLoad}'});">${i}</a></li>
                        </c:if>
                    </c:forEach>

                    <c:if test="${carByPage.currentPage == carByPage.totalPage}" >
                        <li class="disabled">
                    </c:if>
                    <c:if test="${carByPage.currentPage != carByPage.totalPage}" >
                        <li>
                    </c:if>
                        <a href="javascript:$('#content').load('${pageContext.request.contextPath}/carInfo/findCarPageByCondition',
                        {'currentPage':'${carByPage.currentPage+1}','rows':5,'carNumber':'${condition.carNumber}','carType':'${condition.carType}','carLoad':'${condition.carLoad}'});" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                    <span style="font-size: 25px;margin-left: 5px">
                        共${carByPage.totalCount}条记录，共${carByPage.totalPage}页
                    </span>
                </ul>
            </nav>


        </div>

    </div>
</div>
