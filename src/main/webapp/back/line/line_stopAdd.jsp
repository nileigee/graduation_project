<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>


<div class="col-sm-10 bg-danger">
    <div class="container bg-success" style="margin-left: 0px;width: 100%;height: 100%;">
        <div class="page-header text-center" style="height: 40px;margin-top: 10px" >
            <h1><span>待分配站点</span></h1>
        </div>
        <div style="float: left;margin-bottom: 10px">
            <form class="form-inline" action="${pageContext.request.contextPath}/findUserByPageServlet" method="post">
                <div class="form-group">
                    <label for="stopName"><span style="font-size: small">站点名：</span></label>
                    <input type="text" class="form-control" id="stopName" name="stopName" value="${condition.stopName}">
                </div>
                <div class="form-group">
                    <label for="stopType"><span style="font-size: small">站点类型: </span></label>
                    <input type="text" class="form-control" id="stopType" name="stopType" value="${condition.stopType}">

                </div>
                <div class="form-group">
                    <label for="stopAddress"><span style="font-size: small">站点地址：</span></label>
                    <input type="text" class="form-control" id="stopAddress" name="stopAddress" value="${condition.stopAddress}">
                </div>
                <%--<button type="submit" class="btn btn-primary btn-sm" style="margin-left: 8px">查询</button>--%>
                <a class="btn btn-primary btn-sm" type="submit" href="javascript:$('#content').load('${pageContext.request.contextPath}/lineInfo/stopMoveToLineList',
                {'stopName':$('#stopName').val(),'stopType':$('#stopType').val(),'stopAddress':$('#stopAddress').val(),'lineId':'${condition.lineId}'});"  style="margin-left: 8px">查询</a>
            </form>


        </div>

        <div style="float: left;margin-left: 120px">
            <a class="btn btn-primary btn-sm" href="javascript:void(0);" id="btnDelStopSelected" >移入所选</a>
            <script>
                $("#btnDelStopSelected").click(function(){
                    var stopIds=new Array();

                    $.each($('input:checkbox:checked'),function(){
                        stopIds.push($(this).val());
                    });

                    if(confirm("您确认要将所选站点移入吗？")){
                        $('#content').load('${pageContext.request.contextPath}/lineInfo/lineAddStopSelected',{'stopIds[]':stopIds,'lineId':'${condition.lineId}'});
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
                    <th class="text-center">站点名</th>
                    <th class="text-center">站点类型</th>
                    <th class="text-center">站点经度</th>
                    <th class="text-center">站点纬度</th>
                    <th class="text-center">详细地址</th>
                    <th class="text-center">站点负责人</th>
                    <th class="text-center">电话</th>
                    <th class="text-center">使用状态</th>
                    <th class="text-center">站点描述</th>
                    <th class="text-center">操作</th>
                </tr>
                </thead>
                <tbody>
                    <c:forEach items="${stopsByLineId.list}" var="stopInfo" varStatus="s">
                    <tr>
                    <td class="text-center"><input type="checkbox" name="stopIds" id="stopIds" value="${stopInfo.stopId}"></td>
                        <td class="text-center">${s.count}</td>
                        <td class="text-center">${stopInfo.stopName}</td>
                        <td class="text-center">${stopInfo.stopType}</td>
                        <td class="text-center">${stopInfo.stopLongitude}</td>
                        <td class="text-center">${stopInfo.stopLatitude}</td>
                        <td class="text-center">${stopInfo.stopAddress}</td>
                        <td class="text-center">${stopInfo.workerInfo.workerName}</td>
                        <td class="text-center">${stopInfo.workerInfo.workerPhone}</td>
                        <td class="text-center">${stopInfo.stopStatus}</td>
                        <td class="text-center">${stopInfo.stopDescribe}</td>
                        <td class="text-center">
                            &nbsp;<a class="btn btn-primary btn-sm" href="javascript:void(0);"  onclick="btnLineAddStop('${stopInfo.stopId}','${condition.lineId}')">移入</a>
                            <script>
                                function btnLineAddStop(stopId,lineId) {
                                    if(confirm("您确认要将${stopInfo.stopName}移入吗？")){
                                        $("#content").load("${pageContext.request.contextPath}/lineInfo/lineAddStop",{"stopId":stopId,"lineId":lineId});
                                    }
                                }
                            </script>
                        </td>
                    </tr>
                    </c:forEach>

                </tbody>


            </table>
            </table>
        </form>
        <!--分页工具条-->
        <div class="text-center">
            <nav aria-label="Page navigation">
                <ul class="pagination">
                    <c:if test="${stopsByLineId.currentPage == 1}">
                    <li class="disabled">
                        </c:if>
                        <c:if test="${stopsByLineId.currentPage != 1}">
                    <li>
                        </c:if>
                        <a href="javascript:$('#content').load('${pageContext.request.contextPath}/lineInfo/stopMoveToLineList',
                        {'currentPage':'${stopsByLineId.currentPage-1}','rows':8,'stopName':'${condition.stopName}','stopType':'${condition.stopType}','stopAddress':'${condition.stopAddress}','lineId':'${condition.lineId}'});" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>

                    <c:forEach  begin="1" end="${stopsByLineId.totalPage}" var="i">
                        <c:if test="${stopsByLineId.currentPage ==i}">
                            <li class="active"><a href="javascript:$('#content').load('${pageContext.request.contextPath}/lineInfo/stopMoveToLineList',
                            {'currentPage':'${i}','rows':8,'stopName':'${condition.stopName}','stopType':'${condition.stopType}','stopAddress':'${condition.stopAddress}','lineId':'${condition.lineId}'});">${i}</a></li>
                        </c:if>
                        <c:if test="${stopsByLineId.currentPage !=i}">
                            <li><a href="javascript:$('#content').load('${pageContext.request.contextPath}/lineInfo/stopMoveToLineList',
                            {'currentPage':'${i}','rows':8,'stopName':'${condition.stopName}','stopType':'${condition.stopType}','stopAddress':'${condition.stopAddress}','lineId':'${condition.lineId}'});">${i}</a></li>
                        </c:if>
                    </c:forEach>

                    <c:if test="${stopsByLineId.currentPage == stopsByLineId.totalPage}" >
                    <li class="disabled">
                        </c:if>
                        <c:if test="${stopsByLineId.currentPage != stopsByLineId.totalPage}" >
                    <li>
                        </c:if>
                        <a href="javascript:$('#content').load('${pageContext.request.contextPath}/lineInfo/stopMoveToLineList',
                        {'currentPage':'${stopsByLineId.currentPage+1}','rows':8,'stopName':'${condition.stopName}','stopType':'${condition.stopType}','stopAddress':'${condition.stopAddress}','lineId':'${condition.lineId}'});" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                    <span style="font-size: 25px;margin-left: 5px">
                        共${stopsByLineId.totalCount}条记录，共${stopsByLineId.totalPage}页
                    </span>
                </ul>
            </nav>


        </div>

    </div>
</div>


