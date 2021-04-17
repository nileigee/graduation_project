<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>

<div class="col-sm-10">
    <div class="container">
        <center><h3>添加路线页面</h3></center>
        <form  role="form">
            <div class="form-group">
                <label for="lineName">线路名：</label>
                <input type="text" class="form-control" id="lineName" name="lineName" placeholder="请输入线路名">
            </div>

            <div class="form-group">
                <label for="lineType">线路类型：</label>
                <select class="form-control" id="lineType" name="lineType">
                    <option>长途</option>
                    <option>中途</option>
                    <option>短途</option>
                </select>
            </div>

            <div class="form-group">
                <label for="lineLength">线路长度(km)：</label>
                <input type="text" class="form-control" id="lineLength" name="lineLength" placeholder="请输入线路长度">
            </div>

            <div class="form-group">
                <label for="lineStartTime">开始时间：</label>
                <input type="time" class="form-control" id="lineStartTime" name="lineStartTime" placeholder="请输入开始时间">
            </div>

            <div class="form-group">
                <label for="lineEndTime">结束时间：</label>
                <input type="time" class="form-control" id="lineEndTime" name="lineEndTime" placeholder="请输入结束时间">
            </div>

            <div class="form-group">
                <label for="transferId">中转站：</label>
                <select class="form-control" id="transferId" name="transferId">
                    <c:forEach items="${allTransfers}" var="transfer" varStatus="s">
                        <option value="${transfer.transferId}">${transfer.transferName}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-group">
                <label for="workerId">负责人：</label>
                <select class="form-control" id="workerId" name="workerId">
                 <c:forEach items="${allLineWorkers}" var="worker" varStatus="s">
                     <option value="${worker.workerId}">${worker.workerName}</option>
                 </c:forEach>
                </select>
            </div>

            <div class="form-group">
                <label for="lineStatus">状态</label>
                <select class="form-control" id="lineStatus" name="lineStatus">
                    <option>在岗</option>
                    <option>离岗</option>
                </select>
            </div>

            <div class="form-group">
                <label for="lineDescribe">线路描述：</label>
                <textarea class="form-control" id="lineDescribe" name="lineDescribe" placeholder="请输入对线路的描述">
                </textarea>
            </div>

            <div class="form-group" >
                <a class="btn btn-primary btn-sm" style="width: 100%;line-height: 25px" type="submit"
                   href="javascript:$('#content').load('${pageContext.request.contextPath}/lineInfo/saveLine',
                {'lineName':$('#lineName').val(),'lineType':$('#lineType').val(),'lineLength':$('#lineLength').val(),'lineStartTime':$('#lineStartTime').val(),
                 'lineEndTime':$('#lineEndTime').val(),'transferId':$('#transferId').val(),'workerId':$('#workerId').val(),'lineStatus':$('#lineStatus').val(),'lineDescribe':$('#lineDescribe').val()});">添加</a>
            </div>

        </form>
    </div>
</div>
