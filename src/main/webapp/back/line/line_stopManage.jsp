<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>

<c:set var="test" value="${stopsByLineId.list}"></c:set>
<div class="col-sm-10 ">
    <div class="container bg-success" style="margin-left: 0px;width: 100%;height: 100%;">
        <div class="page-header text-center" style="height: 40px;margin-top: 10px" >
            <h1><span>本线路站点列表</span></h1>
        </div>
        <div style="float: left;margin-bottom: 10px">

        </div>

        <div style="float: left;margin-left: 0px">
            <a class="btn btn-primary btn-sm" href="javascript:$('#content').load('${pageContext.request.contextPath}/lineInfo/stopMoveToLineList',{'lineId':'${condition.lineId}'});" id="btnAddStop">加入新站点</a>&nbsp;&nbsp;&nbsp;
            <a class="btn btn-primary btn-sm" href="javascript:void(0);" id="btnLineMoveStopSelected" >移出选中</a>
            <script>
                $("#btnLineMoveStopSelected").click(function(){
                    var stopIds=new Array();

                    $.each($('input:checkbox:checked'),function(){
                        stopIds.push($(this).val());
                    });

                    if(confirm("您确认要将所选站点移出吗？")){
                        $('#content').load('${pageContext.request.contextPath}/lineInfo/lineMoveStopSelected',{'stopIds[]':stopIds,'lineId':'${condition.lineId}'});
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
                    <c:forEach items="${stopsByLineIdNoPage}" var="stopInfo" varStatus="s">
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
                            &nbsp;<a class="btn btn-primary btn-sm text-center" href="javascript:void(0);"  onclick="btnMoveStop('${stopInfo.stopId}','${condition.lineId}')">移出线路</a>
                            <script>
                                /*3.移出站点*/
                                function btnMoveStop(stopId,lineId) {

                                    if(confirm("您确认要将'${stopInfo.stopName}'移出？")){
                                        $("#content").load("${pageContext.request.contextPath}/lineInfo/lineMoveStop",{"stopId":stopId,"lineId":lineId});
                                    }
                                }
                            </script>
                             <a class="btn btn-primary btn-sm text-center" href="javascript:void(0);"  onclick="btnStopUp('${stopInfo.stopId}','${s.count}','${condition.lineId}')">上移</a>
                             <a class="btn btn-primary btn-sm text-center" href="javascript:void(0);"  onclick="btnStopDown('${stopInfo.stopId}','${s.count}','${condition.lineId}')">下移</a>
                        </td>
                    </tr>
                    </c:forEach>

                    <script>
                        /*1.上移*/
                        function btnStopUp(stopId,sCount,lineId) {
                            if (sCount==1){
                                confirm("这是第一行，不能上移！");
                            }else {
                                var sCount=sCount-2;
                                $("#content").load("${pageContext.request.contextPath}/lineInfo/lineStopUp",{"stopId":stopId,"sCount":sCount,"lineId":lineId});
                            }
                        }

                        /*2.下移*/
                        function btnStopDown(stopId,sCount,lineId) {
                            $("#content").load("${pageContext.request.contextPath}/lineInfo/lineStopDown",{"stopId":stopId,"sCount":sCount,"lineId":lineId});
                        }


                    </script>

                </tbody>


            </table>
            </table>
        </form>
        <!--分页工具条-->
        <%--<div class="text-center">
            <nav aria-label="Page navigation">
                <ul class="pagination">
                    <c:if test="${stopsByLineId.currentPage == 1}">
                    <li class="disabled">
                        </c:if>
                        <c:if test="${stopsByLineId.currentPage != 1}">
                    <li>
                        </c:if>
                        <a href="javascript:$('#content').load('${pageContext.request.contextPath}/lineInfo/findStopsByLineId',
                        {'currentPage':'${stopsByLineId.currentPage-1}','rows':8,'lineId':'${condition.lineId}'});" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>

                    <c:forEach  begin="1" end="${stopsByLineId.totalPage}" var="i">
                        <c:if test="${stopsByLineId.currentPage ==i}">
                            <li class="active"><a href="javascript:$('#content').load('${pageContext.request.contextPath}/lineInfo/findStopsByLineId',
                            {'currentPage':'${i}','rows':8,'lineId':'${condition.lineId}'});">${i}</a></li>
                        </c:if>
                        <c:if test="${stopsByLineId.currentPage !=i}">
                            <li><a href="javascript:$('#content').load('${pageContext.request.contextPath}/lineInfo/findStopsByLineId',
                            {'currentPage':'${i}','rows':8,'lineId':'${condition.lineId}'});">${i}</a></li>
                        </c:if>
                    </c:forEach>

                    <c:if test="${stopsByLineId.currentPage == stopsByLineId.totalPage}" >
                    <li class="disabled">
                        </c:if>
                        <c:if test="${stopsByLineId.currentPage != stopsByLineId.totalPage}" >
                    <li>
                        </c:if>
                        <a href="javascript:$('#content').load('${pageContext.request.contextPath}/lineInfo/findStopsByLineId',
                        {'currentPage':'${stopsByLineId.currentPage+1}','rows':8,'lineId':'${condition.lineId}'});" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                    <span style="font-size: 25px;margin-left: 5px">
                        共${stopsByLineId.totalCount}条记录，共${stopsByLineId.totalPage}页
                    </span>
                </ul>
            </nav>


        </div>--%>

    </div>
</div>


