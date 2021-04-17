<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="chrome=1">
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">
    <title>主页</title>
    <!--更好的响应式支持-->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--引入bootstrap核心css文件-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/boot/css/bootstrap.min.css">
    <!--引入jqgrid核心css文件-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/boot/grid/ui.jqgrid-bootstrap.css">
    <!--引入jQuery核心js文件-->
    <script src="${pageContext.request.contextPath}/boot/js/jquery-3.5.1.min.js"></script>
    <!--引入jqgrid核心js文件-->
    <script src="${pageContext.request.contextPath}/boot/grid/jquery.jqGrid.min.js"></script>
    <!--引入jqgrid国际化核心js文件-->
    <script src="${pageContext.request.contextPath}/boot/grid/grid.locale-cn.js"></script>
    <!--引入bootstrap核心js文件-->
    <script src="${pageContext.request.contextPath}/boot/js/bootstrap.min.js"></script>
    <!--//引入高德地图js-->
    <script src="https://webapi.amap.com/js/marker.js"></script>
    <script type="text/javascript" src="https://webapi.amap.com/demos/js/liteToolbar.js"></script>
    <script src="https://webapi.amap.com/maps?v=1.4.11&key=0adb83ce94b8d3ac7383c99ebe898f68"></script>
    <script type="text/javascript" src="http://webapi.amap.com/maps?v=1.4.11&key=0adb83ce94b8d3ac7383c99ebe898f68&plugin=AMap.Geocoder"></script>
    <style>
        html,body{
            height: 100%;
            width: 100%;
        }
        /*66666*/
    </style>

</head>
<body>
<!--导航条-->
<nav class="navbar " style="margin-bottom: 0;background-color: white;height: 7%;">
    <div class="container-fluid " >
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header" >
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" style="margin-top: 13px;" href="#"><span style="font-size: 25px;font-style: revert">垃圾运收管理系统</span> <small>V1.0</small></a>
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li class="active" style="margin-top: 13px;" ><a href="#">首页 <span class="sr-only">(current)</span></a></li>
                <li><a href="#" style="margin-top: 13px;">乖乖</a></li>
                <li class="dropdown">
                    <a href="#" style="margin-top: 13px;" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">下拉 <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="#">1</a></li>
                        <li><a href="#">2n</a></li>
                        <li><a href="#">3</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="#">Separated link</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="#">One more separated link</a></li>
                    </ul>
                </li>
            </ul>
            <form class="navbar-form navbar-left">
                <div class="form-group">
                    <input type="text" style="margin-top: 13px;" class="form-control" placeholder="搜索框">
                </div>
                <button type="submit" style="margin-top: 13px;" class="btn btn-default">确定</button>
            </form>
            <ul class="nav navbar-nav navbar-right">
                <li style="margin-top: 13px;"><a href="#">欢迎：<span class="text-danger">${backUser.userName}</span></a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" style="margin-top: 13px;" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">用户中心<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="#">修改密码</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="#">我的订单</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="#">退出登录</a></li>
                    </ul>
                </li>
            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
<!--页面主体-->
<div class="container-fluid bg-info" id="page" style="height: 93%">
    <!--栅格系统-->
    <div class="row" style="height: 100%;">
        <!--菜单-->
        <div class="col-sm-2 bg-primary" style="height: 100%;">
            <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true" >
                <div class="panel panel-default " >
                    <div class="panel-heading " role="tab" id="headingOne" >
                        <h4 class="panel-title">
                            <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne" >
                                <span class="glyphicon glyphicon-bed"></span> 运收车辆管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseOne" class="panel-collapse collapse bg-info" role="tabpanel" aria-labelledby="headingOne" >
                        <div class="panel-body">
                            <ul class="list-group " style="margin-bottom: 3px">
                                <li class="list-group-item" ><a href="javascript:$('#content').load('${pageContext.request.contextPath}/carInfo/findCarPageByCondition');" id="btnCarList">车辆列表</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingTwo">
                        <h4 class="panel-title">
                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                <span class="glyphicon glyphicon-map-marker"></span> 存放站点管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
                        <div class="panel-body">
                            <ul class="list-group" style="margin-bottom: 3px">
                                <li class="list-group-item"><a href="javascript:$('#content').load('${pageContext.request.contextPath}/stopInfo/findStopByCondition');">存放站点列表</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingThree">
                        <h4 class="panel-title">
                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                                <span class="glyphicon glyphicon-transfer"></span> 中转站点管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
                        <div class="panel-body">
                            <ul class="list-group" style="margin-bottom: 3px">
                                <li class="list-group-item"><a href="javascript:$('#content').load('${pageContext.request.contextPath}/transferInfo/findTransferByCondition');">中转站点列表</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingFoure">
                        <h4 class="panel-title">
                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseFoure" aria-expanded="false" aria-controls="collapseTwo">
                                <span class="glyphicon glyphicon-sort"></span> 运收路线管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseFoure" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFoure">
                        <div class="panel-body">
                            <ul class="list-group" style="margin-bottom: 3px">
                                <li class="list-group-item"><a href="javascript:$('#content').load('${pageContext.request.contextPath}/lineInfo/findLineByCondition');">路线列表</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingFive">
                        <h4 class="panel-title">
                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseFive" aria-expanded="false" aria-controls="collapseThree">
                                <span class="glyphicon glyphicon-user"></span> 工作人员管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseFive" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFive">
                        <div class="panel-body">
                            <ul class="list-group" style="margin-bottom: 3px">
                                <li class="list-group-item"><a href="javascript:$('#content').load('${pageContext.request.contextPath}/workerInfo/findWorkerByCondition');">工作人员列表</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--页面中心内容-->
        <div id="content">
            <div class="col-sm-10">
                <h1>画中仙</h1>
            </div>
        </div>

    </div>
</div>
</body>
</html>