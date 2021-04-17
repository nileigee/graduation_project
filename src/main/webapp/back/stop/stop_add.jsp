<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>



<!--1.页面中心css样式-->
<style>
        #iMap {
            height: 620px;
            width: 800px;
            position: absolute;
            margin-left: 40px;
            margin-top: 15px;
            border-radius: 2px;
            border: solid;
            border: #761c19;
            z-index: 999999;
        }

        #div_form {
            border: solid;
            border: 2px;
            border: #2b669a;

        }
        label {
            width: 80px;
            float: left;
        }

        input{
            width: 450px;
            line-height: 25px;
        }
        select{
            width: 450px;
            line-height: 25px;
        }
        textarea{
            width: 450px;
            line-height: 25px;
        }
    </style>
<!--2.js方法等操作-->
<script language="javascript">
    var addressBox = document.getElementById('addressBox');
    var longitude = document.getElementById('longitude').value;
    var lng = document.getElementById('lng').value;
    var lat = document.getElementById('lat').value;
    var mapObj;
    var lnglatXY;
    //初始化地图
    function mapInit(x, y) {
        mapObj = new AMap.Map("iMap", {
            zoom: 16,
            center: new AMap.LngLat(x, y)
        });
        AMap.event.addListener(mapObj, 'click', getLnglat); //点击事件
        punctuation(x, y);
    }

    //标点展示
    function punctuation(x, y) {
        var marker = new AMap.Marker({
            map: mapObj,
            position: [x, y]
        });
    }

    if (longitude != "") {
        mapInit(lng, lat);
    } else {
        mapInit(113.6641, 34.7848);
    }

    function geocoder() {
        mapObj.clearMap();
        var myGeo = new AMap.Geocoder();
        //地理编码,返回地理编码结果
        myGeo.getLocation(addressBox.value, function(status, result) {
            if (status === 'complete' && result.info === 'OK') {
                //地址解析成功
                // var address = data.regeocode.formattedAddress;
                var x = result.geocodes[0].location.lng;
                var y = result.geocodes[0].location.lat;
                // document.getElementById("particular_site").value = address;
                document.getElementById("longitude").value = x + "," + y; //经纬度
                document.getElementById("lng").value = x; //经度
                document.getElementById("lat").value = y; //纬度
                var XY = [x, y];
                mapInit(x, y);
                regeocoder(XY);
                punctuation(x, y); //更新标记
            } else {
                //地址解析失败
                alert("地址不存在")
            }
        });
    }

    //鼠标点击，获取经纬度坐标
    function getLnglat(e) {
        mapObj.clearMap();
        console.log(e);
        var x = e.lnglat.getLng();
        var y = e.lnglat.getLat();
        document.getElementById("longitude").value = x + "," + y; //经纬度
        document.getElementById("lng").value = x; //经度
        document.getElementById("lat").value = y; //纬度
        var XY = [x, y];
        regeocoder(XY);
        punctuation(x, y); //更新标记
        lnglatXY = new AMap.LngLat(x, y);
    }

    //地址详细描述
    function regeocoder(loc) { //逆地理编码
        console.log(loc);
        var geocoder = new AMap.Geocoder({
            radius: 1000,
            extensions: "all"
        });
        geocoder.getAddress(loc, function(status, result) {
            if (status === 'complete' && result.info === 'OK') {
                geocoder_CallBack(result);
            }
        });
    }

    function geocoder_CallBack(data) {
        var address = data.regeocode.formattedAddress; //返回地址描述
        document.getElementById("particular_site").value = address;
    }
</script>
<!--3.页面中心内容-->
<div class="col-sm-10">
    <div class="container">
        <center><h2>站点添加页面</h2></center>

            <div class="text-center" style="margin: 0 auto;">
                <span style="font-size: small;"><strong>搜索地址：</strong></span><input type="text" id="addressBox" autocomplete="off" placeholder="请输入需要搜索的地址！" class="layui-input">
                <button type="button" onclick="geocoder();" class="btn btn-primary btn-sm" style="height: 25px">搜索</button>
            </div>

        <div class="col-sm-4" id="div_form">
            <form  role="form" >
                <div class="form-group">
                    <label for="stopName">站点名：</label>
                    <input type="text" class="form-control" id="stopName" name="stopName" placeholder="请输入站点名称">
                </div>

                <div class="form-group">
                    <label for="stopType">站点类型：</label>
                    <input type="text" class="form-control" id="stopType" name="stopType" placeholder="请输入站点类型">
                </div>

                <!--隐藏了经纬度-->
                <input type="hidden" name="longitude" id="longitude" value="" readonly autocomplete="off" placeholder="点击地图自动选定"
                       class="layui-input">

                <div class="form-group">
                    <label for="lng">站点经度：</label>
                    <input type="text" class="form-control" id="lng" name="stopLongitude" placeholder="站点经度">
                </div>

                <div class="form-group">
                    <label for="lat">站点纬度：</label>
                    <input type="text" class="form-control" id="lat" name="stopLatitude" placeholder="站点纬度">
                </div>

                <div class="form-group">
                    <label for="particular_site">详细地址：</label>
                    <input type="text" class="form-control" id="particular_site" name="stopAddress" placeholder="站点省份">
                </div>

                <div class="form-group">
                    <label for="workerId">负责人</label>
                    <select class="form-control" id="workerId" name="workerId">
                        <c:forEach items="${allStopWorkers}" var="worker" varStatus="s">
                            <option value="${worker.workerId}">${worker.workerName}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-group">
                    <label for="stopStatus">运行状态</label>
                    <select class="form-control" id="stopStatus" name="stopStatus">
                        <option>运行</option>
                        <option>停运</option>
                    </select>
                </div>

                <div class="form-group">
                    <label for="stopDescribe">站点描述：</label>
                    <textarea class="form-control" id="stopDescribe" name="stopDescribe" placeholder="请输入对车辆的描述">
                </textarea>
                </div>

                <a class="btn btn-primary" style="width: 450px;line-height: 25px" type="submit"
                href="javascript:$('#content').load('${pageContext.request.contextPath}/stopInfo/saveStop',
                {'stopName':$('#stopName').val(),'stopType':$('#stopType').val(),'stopLongitude':$('#lng').val(),'stopLatitude':$('#lat').val(),
                 'stopAddress':$('#particular_site').val(),'workerId':$('#workerId').val(),'stopStatus':$('#stopStatus').val(),'stopDescribe':$('#stopDescribe').val()});">添加</a>

            </form>
        </div>

        <div class="col-sm-8 text-center">

        <div id="iMap"></div>

        </div>



    </div>
</div>





