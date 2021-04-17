<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>

<div class="col-sm-10 bg-danger">
    <div class="container" style="margin-left: 0px;width: 100%;height: 100%;">
        <div class="page-header text-center" style="height: 40px;margin-top: 10px" >
            <h2>线路车辆列表</h2>
        </div>

        <div style="float: left;margin-bottom: 10px">
            <a class="btn btn-primary btn-sm" href="javascript:$('#content').load('${pageContext.request.contextPath}/lineInfo/carMoveToLineList',{'lineId':0,'changeLineId':'${condition.lineId}'});">加入新车辆</a>
            <a class="btn btn-primary btn-sm" href="#" id="btnRemoveCarsSelected">移出所选车辆</a>
            <script>
                $("#btnRemoveCarsSelected").click(function(){
                    var carIds=new Array();

                    $.each($('input:checkbox:checked'),function(){
                        carIds.push($(this).val());
                    });
                    alert(carIds);
                    if(confirm("您确认要将所选车辆移出本线路吗？")){
                        $('#content').load('${pageContext.request.contextPath}/lineInfo/lineRemoveCarsSelected',{'carIds[]':carIds,'lineId':'0','changeLineId':'${condition.lineId}'});
                    }

                });
            </script>

        </div>


        <form action="${pageContext.request.contextPath}/carInfo/deleteCarSelected" method="post" id="selectedForm">
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
                <c:forEach items="${carsByLineId.list}" var="carInfo" varStatus="s">
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
                        <td class="text-center">
                            <a class="btn btn-primary btn-sm"
                               href="javascript:void(0);" onclick="btnRemoveCar('${carInfo.carId}','0','${condition.lineId}')" >移出本线路</a>
                            <script>
                                function btnRemoveCar(carId,lineId,changeLineId) {
                                    if(confirm("您确认要将本车辆从该线路上移出吗？")){
                                        $("#content").load("${pageContext.request.contextPath}/lineInfo/lineRemoveCar",{"carId":carId,"lineId":lineId,"changeLineId":changeLineId});
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
        <div>
            <nav aria-label="Page navigation">
                <ul class="pagination">
                    <c:if test="${carsByLineId.currentPage == 1}">
                        <li class="disabled">
                    </c:if>
                    <c:if test="${carsByLineId.currentPage != 1}">
                        <li>
                    </c:if>
                        <a href="javascript:$('#content').load('${pageContext.request.contextPath}/lineInfo/findCarsByLineId',
                        {'currentPage':'${carsByLineId.currentPage-1}','rows':8,'lineId':'${condition.lineId}'});" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>

                    <c:forEach  begin="1" end="${carsByLineId.totalPage}" var="i">
                        <c:if test="${carsByLineId.currentPage ==i}">
                            <li class="active"><a href="javascript:$('#content').load('${pageContext.request.contextPath}/lineInfo/findCarsByLineId',
                            {'currentPage':'${i}','rows':8,'lineId':'${condition.lineId}'});">${i}</a></li>
                        </c:if>
                        <c:if test="${carsByLineId.currentPage !=i}">
                            <li><a href="javascript:$('#content').load('${pageContext.request.contextPath}/lineInfo/findCarsByLineId',
                            {'currentPage':'${i}','rows':8,'lineId':'${condition.lineId}'});">${i}</a></li>
                        </c:if>
                    </c:forEach>

                    <c:if test="${carsByLineId.currentPage == carsByLineId.totalPage}" >
                        <li class="disabled">
                    </c:if>
                    <c:if test="${carsByLineId.currentPage != carsByLineId.totalPage}" >
                        <li>
                    </c:if>
                        <a href="javascript:$('#content').load('${pageContext.request.contextPath}/lineInfo/findCarsByLineId',
                        {'currentPage':'${carsByLineId.currentPage+1}','rows':8,'lineId':'${condition.lineId}'});" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                    <span style="font-size: 25px;margin-left: 5px">
                        共${carsByLineId.totalCount}条记录，共${carsByLineId.totalPage}页
                    </span>
                </ul>
            </nav>


        </div>

    </div>
</div>
