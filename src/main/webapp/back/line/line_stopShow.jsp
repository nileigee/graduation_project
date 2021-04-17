<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<script src="https://webapi.amap.com/js/marker.js"></script>
<script type="text/javascript" src="https://webapi.amap.com/demos/js/liteToolbar.js"></script>
<script src="https://webapi.amap.com/maps?v=1.4.0&key=0adb83ce94b8d3ac7383c99ebe898f68"></script>
<script type="text/javascript">
    var map = new AMap.Map('container',{resizeEnable: true,zoom:4});
    var markers = []; //province见Demo引用的JS文件
    var long=new Array();
    var lat=new Array();
    $("#identity2 option").each(function(){ //遍历全部option
        var txt = $(this).text(); //获取option的内容
        long.push(txt); //添加到数组中
    });
    $("#identity1 option").each(function(){ //遍历全部option
        var txt = $(this).text(); //获取option的内容
        lat.push(txt); //添加到数组中
    });

    var lines=[];
    for (var i=0;i<long.length;i+=1){
        lines.push([long[i],lat[i]]);
    }

    for (var i = 0; i < lines.length; i += 1) {
        var marker;
        var icon = new AMap.Icon({
            image: 'https://vdata.amap.com/icons/b18/1/2.png',
            size: new AMap.Size(24, 24)
        });
        marker = new AMap.Marker({
            icon: icon,
            position: lines[i],
            offset: new AMap.Pixel(-12,-12),
            zIndex: 101,
            title:JSON.stringify(lines[i]),
            map: map
        });

        markers.push(marker);
    }
    map.setFitView();

</script>

<div class="col-sm-10 ">
    <div class="text-center" style="margin-top: 10px">
        <span style="font-size: 35px;font-style:unset">线路中所有站点显示</span>
    </div>
    <div id="container" class="bg-danger" style="border: solid 5px red;margin-top: 10px;margin-left:130px;height: 750px;width: 1300px;"></div>
    <select style="display: none;" id="identity2">
        <c:forEach items="${stopLongitude}" var="longitude" varStatus="s">
            <option>${longitude}</option>
        </c:forEach>
    </select>
    <select style="display: none;" id="identity1">
        <c:forEach items="${stopLatitude}" var="latitude" varStatus="s">
            <option>${latitude}</option>
        </c:forEach>
    </select>
</div>


