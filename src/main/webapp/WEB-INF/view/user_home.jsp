<%--
  Created by IntelliJ IDEA.
  User: 52491
  Date: 2019/12/10
  Time: 21:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css" type="text/css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/st-style.css" type="text/css"/>
    <script src="https://libs.baidu.com/jquery/2.1.4/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/layui/layui.all.js"></script>

</head>
<body class="bg-gray">

<jsp:include page="header.jsp"></jsp:include>

<div class="st-banner" style="height: 150px"></div>

<div style="background: white">
    <div class="home-information-box">
        <div class="information-headimg-box">
            <img src="/getImage?url=test.png" width="150px"/>
        </div>
    </div>

    <div class="text-center" style="margin-top: 80px">
        <div id="user_name" style="font-size: 24px">${userInfo.name}</div>
        <div id="user_id" style="font-size: 14px;color: #bbbbbb">id:${userInfo.id}</div>
        <%--自己的主页没有关注和私信--%>
        <c:if test="${sessionScope.myInfo.id != userInfo.id}">
            <div style="margin-top:10px;padding-bottom: 10px">
                <button type="button" class="layui-btn">关注</button>
                <button type="button" class="layui-btn">私信</button>
            </div>
        </c:if>
    </div>
</div>


<div class="layui-tab layui-tab-brief" style="" lay-filter="docDemoTabBrief">
    <ul class="layui-tab-title" style="text-align: center;background:white">
        <li class="layui-this" style="width: 200px">相册</li>
        <li style="width: 200px">动态</li>
    </ul>
    <div class="st-main">
        <div class="layui-tab-content">
            <%--相册--%>
            <div class="layui-tab-item layui-show">
                <%--是自己的页面才可以点创建相册--%>
                <c:if test="${sessionScope.isLogin && sessionScope.myInfo.id == userInfo.id}">
                    <div class="" style="margin-top:20px;text-align: right">
                        <a href="/createAlbum">
                            <button type="button" class="layui-btn layui-btn-radius">创建相册</button>
                        </a>
                        <button type="button" class="layui-btn layui-btn-radius">上传照片</button>
                    </div>
                </c:if>
                <div id="albums" style="margin-top: 20px">
                    <c:if test="${albumList.size() == 0}">
                        <div>
                            TA暂时还没有上传相册哦!
                        </div>
                    </c:if>
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
                                <div class="card-info-type">${album.createTime}</div>
                            </div>
                        </div>
                    </c:forEach>

                </div>
            </div>
            <%--动态--%>
            <div class="layui-tab-item">敬请期待</div>
        </div>
    </div>
</div>


</body>
</html>
