<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>

<div class="col-sm-10 bg-danger">
    <div class="container bg-success" style="margin-left: 0px;width: 100%;height: 100%;">
        <div class="page-header text-center" style="height: 40px;margin-top: 10px" >
            <h1>中转站点管理</h1>
        </div>
        <div style="float: left;margin-bottom: 10px">
            <form class="form-inline" action="${pageContext.request.contextPath}/findUserByPageServlet" method="post">
                <div class="form-group">
                    <label for="transferName"><span style="font-size: small">中转名：</span></label>
                    <input type="text" class="form-control" id="transferName" name="transferName" value="${condition.transferName}">
                </div>
                <div class="form-group">
                    <label for="transferType"><span style="font-size: small">类型: </span></label>
                    <input type="text" class="form-control" id="transferType" name="transferType" value="${condition.transferType}">

                </div>
                <div class="form-group">
                    <label for="transferAddress"><span style="font-size: small">地址：</span></label>
                    <input type="text" class="form-control" id="transferAddress" name="transferAddress" value="${condition.transferAddress}">
                </div>

                <a class="btn btn-primary btn-sm" type="submit" href="javascript:$('#content').load('${pageContext.request.contextPath}/transferInfo/findTransferByCondition',
                {'transferName':$('#transferName').val(),'transferType':$('#transferType').val(),'transferAddress':$('#transferAddress').val()});"  style="margin-left: 8px">查询</a>
            </form>


        </div>

        <div style="float: left;margin-left: 120px">
            <a class="btn btn-primary btn-sm" href="javascript:$('#content').load('${pageContext.request.contextPath}/workerInfo/findAllTransferWorkers');" id="btnAddTransfer">添加中转站</a>&nbsp;&nbsp;&nbsp;
            <a class="btn btn-primary btn-sm" href="javascript:void(0);" id="btnDelStopSelected" >删除选中</a>
            <script>
                $("#btnDelStopSelected").click(function(){
                    var transferIds=new Array();

                    $.each($('input:checkbox:checked'),function(){
                        transferIds.push($(this).val());
                    });

                    if(confirm("您确认要将所选中转站删除吗？")){
                        $('#content').load('${pageContext.request.contextPath}/transferInfo/deleteTransferSelected',{'transferIds[]':transferIds});
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
                    <th class="text-center">中转站</th>
                    <th class="text-center">类型</th>
                    <th class="text-center">经度</th>
                    <th class="text-center">纬度</th>
                    <th class="text-center">详细地址</th>
                    <th class="text-center">负责人</th>
                    <th class="text-center">电话</th>
                    <th class="text-center">使用状态</th>
                    <th class="text-center">中转站描述</th>
                    <th class="text-center">操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${transferByPage.list}" var="transferInfo" varStatus="s">
                    <tr>
                        <td class="text-center"><input type="checkbox" name="transferIds" id="transferIds" value="${transferInfo.transferId}"></td>
                        <td class="text-center">${s.count}</td>
                        <td class="text-center">${transferInfo.transferName}</td>
                        <td class="text-center">${transferInfo.transferType}</td>
                        <td class="text-center">${transferInfo.transferLongitude}</td>
                        <td class="text-center">${transferInfo.transferLatitude}</td>
                        <td class="text-center">${transferInfo.transferAddress}</td>
                        <td class="text-center">${transferInfo.workerInfo.workerName}</td>
                        <td class="text-center">${transferInfo.workerInfo.workerPhone}</td>
                        <td class="text-center">${transferInfo.transferStatus}</td>
                        <td class="text-center">${transferInfo.transferDescribe}</td>
                        <td class="text-center"><a class="btn btn-primary btn-sm" href="javascript:$('#content').load('${pageContext.request.contextPath}/transferInfo/findTransferById',{'transferId':'${transferInfo.transferId}'});" id="btnUpdateTransfer">修改</a>
                            &nbsp;<a class="btn btn-primary btn-sm" href="javascript:void(0);" onclick="btnDelTransfer('${transferInfo.transferId}')">删除</a>
                            <script>
                                function btnDelTransfer(transferId) {
                                    if(confirm("您确认要删除吗？")){
                                        $("#content").load("${pageContext.request.contextPath}/transferInfo/deleteTransfer",{"transferId":transferId});
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
                    <c:if test="${transferByPage.currentPage == 1}">
                    <li class="disabled">
                        </c:if>
                        <c:if test="${transferByPage.currentPage != 1}">
                    <li>
                        </c:if>
                        <a href="javascript:$('#content').load('${pageContext.request.contextPath}/transferInfo/findTransferByCondition',
                        {'currentPage':'${transferByPage.currentPage-1}','rows':5,'transferName':'${condition.transferName}','transferType':'${condition.transferType}','stopAddress':'${condition.transferAddress}'});" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>

                    <c:forEach  begin="1" end="${transferByPage.totalPage}" var="i">
                        <c:if test="${transferByPage.currentPage ==i}">
                            <li class="active"><a href="javascript:$('#content').load('${pageContext.request.contextPath}/transferInfo/findTransferByCondition',
                            {'currentPage':'${i}','rows':5,'transferName':'${condition.transferName}','transferType':'${condition.transferType}','stopAddress':'${condition.transferAddress}'});">${i}</a></li>
                        </c:if>
                        <c:if test="${transferByPage.currentPage !=i}">
                            <li><a href="javascript:$('#content').load('${pageContext.request.contextPath}/transferInfo/findTransferByCondition',
                            {'currentPage':'${i}','rows':5,'transferName':'${condition.transferName}','transferType':'${condition.transferType}','stopAddress':'${condition.transferAddress}'});">${i}</a></li>
                        </c:if>
                    </c:forEach>

                    <c:if test="${transferByPage.currentPage == transferByPage.totalPage}" >
                    <li class="disabled">
                        </c:if>
                        <c:if test="${transferByPage.currentPage != transferByPage.totalPage}" >
                    <li>
                        </c:if>
                        <a href="javascript:$('#content').load('${pageContext.request.contextPath}/transferInfo/findTransferByCondition',
                        {'currentPage':'${transferByPage.currentPage+1}','rows':5,'transferName':'${condition.transferName}','transferType':'${condition.transferType}','stopAddress':'${condition.transferAddress}'});" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                    <span style="font-size: 25px;margin-left: 5px">
                        共${transferByPage.totalCount}条记录，共${transferByPage.totalPage}页
                    </span>
                </ul>
            </nav>


        </div>

    </div>
</div>


