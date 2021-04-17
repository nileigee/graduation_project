<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>


<div class="col-sm-10 bg-danger">
    <div class="container bg-success" style="margin-left: 0px;width: 100%;height: 100%;">
        <div class="page-header text-center" style="height: 40px;margin-top: 10px" >
            <h1>工作人员管理</h1>
        </div>
        <div style="float: left;margin-bottom: 10px">
            <form class="form-inline" action="${pageContext.request.contextPath}/findUserByPageServlet" method="post">
                <div class="form-group">
                    <label for="workerName"><span style="font-size: small">姓名：</span></label>
                    <input type="text" class="form-control" id="workerName" name="workerName" value="${condition.workerName}">
                </div>
                <div class="form-group">
                    <label for="workerPhone"><span style="font-size: small">手机: </span></label>
                    <input type="text" class="form-control" id="workerPhone" name="workerPhone" value="${condition.workerPhone}">

                </div>
                <div class="form-group">
                    <label for="workerType"><span style="font-size: small">工种：</span></label>
                    <input type="text" class="form-control" id="workerType" name="workerType" value="${condition.workerType}">
                </div>
                <a class="btn btn-primary btn-sm" type="submit" href="javascript:$('#content').load('${pageContext.request.contextPath}/workerInfo/findWorkerByCondition',
                {'workerName':$('#workerName').val(),'workerPhone':$('#workerPhone').val(),'workerType':$('#workerType').val()});"  style="margin-left: 8px">查询</a>
            </form>


        </div>

        <div style="float: left;margin-left: 120px">
            <a class="btn btn-primary btn-sm" href="javascript:$('#content').load(l${pageContext.request.contextPath}ine_add.jsp);" id="btnAddTransfer">添加工作人员</a>&nbsp;&nbsp;&nbsp;
            <a class="btn btn-primary btn-sm" href="javascript:void(0);" id="btnDelWorkerSelected" >删除选中</a>
            <script>
                $("#btnDelWorkerSelected").click(function(){
                    var workerIds=new Array();

                    $.each($('input:checkbox:checked'),function(){
                        workerIds.push($(this).val());
                    });

                    if(confirm("您确认要将所选人员删除吗？")){
                        $('#content').load('${pageContext.request.contextPath}/workerInfo/deleteWorkerSelected',{'workerIds[]':workerIds});
                    }

                });
            </script>
        </div>

        <form method="post" name="selectedForm" id="selectedForm" onsubmit="return selectedForm()">
            <table border="1" class="table table-bordered table-hover table-striped">
                <thead>
                <tr >
                    <th class="text-center"><input type="checkbox" id="firstCb"></th>
                    <th class="text-center">编号</th>
                    <th class="text-center">姓名</th>
                    <th class="text-center">性别</th>
                    <th class="text-center">年龄</th>
                    <th class="text-center">电话</th>
                    <th class="text-center">工种</th>
                    <th class="text-center">地址</th>
                    <th class="text-center">在岗状态</th>
                    <th class="text-center">描述</th>
                    <th class="text-center">操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${workerByPage.list}" var="worker" varStatus="s">
                    <tr>
                        <td class="text-center"><input type="checkbox" id="workerIds" name="workerIds" value="${worker.workerId}"></td>
                        <td class="text-center">${s.count}</td>
                        <td class="text-center">${worker.workerName}</td>
                        <td class="text-center">${worker.workerGender}</td>
                        <td class="text-center">${worker.workerAge}</td>
                        <td class="text-center">${worker.workerPhone}</td>
                        <td class="text-center">${worker.workerType}</td>
                        <td class="text-center">${worker.workerAddress}</td>
                        <td class="text-center">${worker.workerStatus}</td>
                        <td class="text-center">${worker.workerDescribe}</td>
                        <td class="text-center"><a class="btn btn-primary btn-sm" href="javascript:$('#content').load('${pageContext.request.contextPath}/workerInfo/findWorkerById',{'workerId':'${worker.workerId}'});" id="btnUpdateTransfer">修改</a>
                            &nbsp;<a class="btn btn-primary btn-sm" href="javascript:void(0);" onclick="btnDelWorker('${worker.workerId}')">删除</a>
                            <script>
                                function btnDelWorker(workerId) {
                                    if(confirm("您确认要删除吗？")){
                                        $("#content").load("${pageContext.request.contextPath}/workerInfo/deleteWorker",{"workerId":workerId});
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
                    <c:if test="${workerByPage.currentPage == 1}">
                    <li class="disabled">
                        </c:if>
                        <c:if test="${workerByPage.currentPage != 1}">
                    <li>
                        </c:if>
                        <a href="javascript:$('#content').load('${pageContext.request.contextPath}/workerInfo/findWorkerByCondition',
                        {'currentPage':'${workerByPage.currentPage-1}','rows':5,'workerName':'${condition.workerName}','workerPhone':'${condition.workerPhone}','workerType':'${condition.workerType}'});" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>

                    <c:forEach  begin="1" end="${workerByPage.totalPage}" var="i">
                        <c:if test="${workerByPage.currentPage ==i}">
                            <li class="active"><a href="javascript:$('#content').load('${pageContext.request.contextPath}/workerInfo/findWorkerByCondition',
                            {'currentPage':'${i}','rows':5,'workerName':'${condition.workerName}','workerPhone':'${condition.workerPhone}','workerType':'${condition.workerType}'});">${i}</a></li>
                        </c:if>
                        <c:if test="${workerByPage.currentPage !=i}">
                            <li><a href="javascript:$('#content').load('${pageContext.request.contextPath}/workerInfo/findWorkerByCondition',
                            {'currentPage':'${i}','rows':5,'workerName':'${condition.workerName}','workerPhone':'${condition.workerPhone}','workerType':'${condition.workerType}'});">${i}</a></li>
                        </c:if>
                    </c:forEach>

                    <c:if test="${workerByPage.currentPage == workerByPage.totalPage}" >
                    <li class="disabled">
                        </c:if>
                        <c:if test="${workerByPage.currentPage != workerByPage.totalPage}" >
                    <li>
                        </c:if>
                        <a href="javascript:$('#content').load('${pageContext.request.contextPath}/workerInfo/findWorkerByCondition',
                        {'currentPage':'${workerByPage.currentPage+1}','rows':5,'workerName':'${condition.workerName}','workerPhone':'${condition.workerPhone}','workerType':'${condition.workerType}'});" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                    <span style="font-size: 25px;margin-left: 5px">
                        共${workerByPage.totalCount}条记录，共${workerByPage.totalPage}页
                    </span>
                </ul>
            </nav>


        </div>

    </div>
</div>


