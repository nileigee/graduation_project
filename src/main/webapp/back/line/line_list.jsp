<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>


<div class="col-sm-10 bg-danger">
    <div class="container bg-success" style="margin-left: 0px;width: 100%;height: 100%;">
        <div class="page-header text-center" style="height: 40px;margin-top: 10px" >
            <h1><span>线路管理</span></h1>
        </div>
        <div style="float: left;margin-bottom: 10px">
            <form class="form-inline" action="${pageContext.request.contextPath}/findUserByPageServlet" method="post">
                <div class="form-group">
                    <label for="lineName"><span style="font-size: small">线路名：</span></label>
                    <input type="text" class="form-control" id="lineName" name="lineName" value="${condition.lineName}">
                </div>
                <div class="form-group">
                    <label for="lineType"><span style="font-size: small">线路类型: </span></label>
                    <input type="text" class="form-control" id="lineType" name="lineType" value="${condition.lineType}">

                </div>
                <div class="form-group">
                    <label for="lineStatus"><span style="font-size: small">运行状态：</span></label>
                    <input type="text" class="form-control" id="lineStatus" name="lineStatus" value="${condition.lineStatus}">
                </div>
                <a class="btn btn-primary btn-sm" type="submit" href="javascript:$('#content').load('${pageContext.request.contextPath}/lineInfo/findLineByCondition',
                {'lineName':$('#lineName').val(),'lineType':$('#lineType').val(),'lineStatus':$('#lineStatus').val()});"  style="margin-left: 8px">查询</a>
            </form>


        </div>

        <div style="float: left;margin-left: 120px">
            <a class="btn btn-primary btn-sm" href="javascript:$('#content').load('${pageContext.request.contextPath}/lineInfo/findAllWorkersAndTransfers');" id="btnAddTransfer">添加线路</a>&nbsp;&nbsp;&nbsp;
            <a class="btn btn-primary btn-sm" href="javascript:void(0);" id="btnDelWorkerSelected" >删除选中</a>
            <script>
                $("#btnDelWorkerSelected").click(function(){
                    var lineIds=new Array();

                    $.each($('input:checkbox:checked'),function(){
                        lineIds.push($(this).val());
                    });

                    if(confirm("您确认要将所选线路删除吗？")){
                        $('#content').load('${pageContext.request.contextPath}/lineInfo/deleteLineSelected',{'lineIds[]':lineIds});
                    }

                });
            </script>

        </div>

        <form  method="post" name="selectedForm" id="selectedForm" >
            <table border="1" class="table table-bordered table-hover table-striped">
                <thead>
                <tr >
                    <th class="text-center"><input type="checkbox" id="firstCb"></th>
                    <th class="text-center">编号</th>
                    <th class="text-center">管理</th>
                    <th class="text-center">线路名</th>
                    <th class="text-center">类型</th>
                    <th class="text-center">线路长度</th>
                    <th class="text-center">开始时间</th>
                    <th class="text-center">结束时间</th>
                    <th class="text-center">负责人</th>
                    <th class="text-center">联系方式</th>
                    <th class="text-center">运行状态</th>
                    <th class="text-center">描述</th>
                    <th class="text-center">操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${lineByPage.list}" var="line" varStatus="s">
                    <tr>
                        <td class="text-center"><input type="checkbox" id="lineIds" name="lineIds" value="${line.lineId}"></td>
                        <td class="text-center">${s.count}</td>
                        <td class="text-center">
                            <a class="btn btn-primary btn-sm" href="javascript:$('#content').load('${pageContext.request.contextPath}/lineInfo/showStopsByLineId',{'lineId':'${line.lineId}'});">站点显示</a>
                            <a class="btn btn-primary btn-sm" href="javascript:$('#content').load('${pageContext.request.contextPath}/lineInfo/findStopsByLineId',{'lineId':'${line.lineId}'});">站点分配</a>
                            <a class="btn btn-primary btn-sm"
                               href="javascript:$('#content').load('${pageContext.request.contextPath}/lineInfo/findCarsByLineId',{'lineId':'${line.lineId}'});">车辆分配</a>

                        </td>
                        <td class="text-center">${line.lineName}</td>
                        <td class="text-center">${line.lineType}</td>
                        <td class="text-center">${line.lineLength}</td>
                        <td class="text-center">${line.lineStartTime}</td>
                        <td class="text-center">${line.lineEndTime}</td>
                        <td class="text-center">${line.workerInfo.workerName}</td>
                        <td class="text-center">${line.workerInfo.workerPhone}</td>
                        <td class="text-center">${line.lineStatus}</td>
                        <td class="text-center">${line.lineDescribe}</td>
                        <td class="text-center"><a class="btn btn-primary btn-sm" href="javascript:$('#content').load('${pageContext.request.contextPath}/lineInfo/findLineById',{'lineId':'${line.lineId}'});" >修改</a>
                            &nbsp;<a class="btn btn-primary btn-sm" href="javascript:void(0);" onclick="btnDelLine('${line.lineId}')">删除</a>
                            <script>
                                function btnDelLine(lineId) {
                                    if(confirm("您确认要删除吗？")){
                                        $("#content").load("${pageContext.request.contextPath}/lineInfo/deleteLine",{"lineId":lineId});
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
                    <c:if test="${lineByPage.currentPage == 1}">
                    <li class="disabled">
                        </c:if>
                        <c:if test="${lineByPage.currentPage != 1}">
                    <li>
                        </c:if>
                        <a href="javascript:$('#content').load('${pageContext.request.contextPath}/lineInfo/findLineByCondition',
                        {'currentPage':'${lineByPage.currentPage-1}','rows':5,'lineName':'${condition.lineName}','lineType':'${condition.lineType}','lineStatus':'${condition.lineStatus}'});" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>

                    <c:forEach  begin="1" end="${lineByPage.totalPage}" var="i">
                        <c:if test="${lineByPage.currentPage ==i}">
                            <li class="active"><a href="javascript:$('#content').load('${pageContext.request.contextPath}/lineInfo/findLineByCondition',
                            {'currentPage':'${i}','rows':5,'lineName':'${condition.lineName}','lineType':'${condition.lineType}','lineStatus':'${condition.lineStatus}'});">${i}</a></li>
                        </c:if>
                        <c:if test="${lineByPage.currentPage !=i}">
                            <li><a href="javascript:$('#content').load('${pageContext.request.contextPath}/lineInfo/findLineByCondition',
                            {'currentPage':'${i}','rows':5,'lineName':'${condition.lineName}','lineType':'${condition.lineType}','lineStatus':'${condition.lineStatus}'});">${i}</a></li>
                        </c:if>
                    </c:forEach>

                    <c:if test="${lineByPage.currentPage == lineByPage.totalPage}" >
                    <li class="disabled">
                        </c:if>
                        <c:if test="${lineByPage.currentPage != lineByPage.totalPage}" >
                    <li>
                        </c:if>
                        <a href="javascript:$('#content').load('${pageContext.request.contextPath}/lineInfo/findLineByCondition',
                        {'currentPage':'${lineByPage.currentPage+1}','rows':5,'lineName':'${condition.lineName}','lineType':'${condition.lineType}','lineStatus':'${condition.lineStatus}'});" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                    <span style="font-size: 25px;margin-left: 5px">
                        共${lineByPage.totalCount}条记录，共${lineByPage.totalPage}页
                    </span>
                </ul>
            </nav>


        </div>

    </div>
</div>


