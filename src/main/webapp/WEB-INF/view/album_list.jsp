<%--
  Created by IntelliJ IDEA.
  User: 52491
  Date: 2019/12/10
  Time: 15:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>相册列表</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css" type="text/css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/st-style.css" type="text/css"/>
    <script src="https://libs.baidu.com/jquery/2.1.4/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/layui/layui.all.js"></script>
    <script type="text/javascript">
        // 测试
        // $(function () {
        //     var a = "<div class=\"card-box\">\n" +
        //         "            <div class=\"card-image\">\n" +
        //         "                <img src=\"/getDefaultImage\" style='width: 100%'>\n" +
        //         "            </div>\n" +
        //         "            <div class=\"card-info\">\n" +
        //         "                <div class=\"card-info-title\">一个相册</div>\n" +
        //         "                <div class=\"card-info-type\">这是一个相册</div>\n" +
        //         "            </div>\n" +
        //         "            <div class=\"card-item\">\n" +
        //         "                <div class=\"card-info-type\">王小猪</div>\n" +
        //         "                <div class=\"card-info-type\">20:00</div>\n" +
        //         "            </div>\n" +
        //         "        </div>";
        //     for(var i = 1;i<10;i++){
        //         $("#albums").append(a);
        //     }
        // });
    </script>
</head>

<body class="bg-gray">


<jsp:include page="header.jsp"></jsp:include>

<div class="st-main">
    <div class="st-banner" style="height: max-content;margin-top:20px">
        <div class="layui-carousel" id="test1">
            <div carousel-item>
                <div><div class="theme-bg" style="height: 100%">电子相册</div></div>
                <div><div class="theme-bg" style="height: 100%">Version 0.0.0.1</div></div>
            </div>
        </div>
    </div>
    <div id="albums" style="margin-top: 20px">
        <c:forEach var="album" items="${albumList}">
            <div class="card-box">
                <div class="card-image">
                        <%--到底传id还是直接传url好呢--%>
                    <a href="/album?albumId=${album.id}"><img class="fill-box" src="/getImage?url=${album.coverId}"></a>
                </div>
                <div class="card-info">
                    <div class="card-info-title">${album.name}</div>
                    <div class="card-info-type">${album.descp}</div>
                </div>
                <div class="card-item">
                    <div class="card-info-type">上传者:${album.userId}</div>
                    <div class="card-info-type">${album.createTime}</div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

<script>
    layui.use('carousel', function(){
        var carousel = layui.carousel;
        //建造实例
        carousel.render({
            elem: '#test1'
            ,width: '100%' //设置容器宽度
            ,arrow: 'none' //始终显示箭头
            ,autoplay:true
            ,interval:3000

            //,anim: 'updown' //切换动画方式
        });
    });
</script>

</body>
</html>
