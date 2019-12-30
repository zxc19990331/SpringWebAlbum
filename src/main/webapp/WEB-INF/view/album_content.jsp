<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 52491
  Date: 2019/12/11
  Time: 20:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css" type="text/css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/st-style.css" type="text/css"/>
    <script src="https://libs.baidu.com/jquery/2.1.4/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/layui/layui.all.js"></script>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<div class="st-banner-ad-box">
    <div class="st-banner-ad theme-bg"></div>
</div>

<div class="album-box horizentol border-bottom border-top">
    <div class="album-info-box vertical">
        <div style="font-size: 33px">${albumInfo.name}</div>
        <div class="gray-color" style="font-size: 16px;margin-top: 10px">上传时间:${albumInfo.createTime}</div>
        <div class="gray-color" style="font-size: 18px;margin-top: 10px">${albumInfo.name}</div>

    </div>
    <div class="album-author-box horizentol border-left">
        <div class="album-info-avatar">
            <a href="/user?id=${albumInfo.userId}"><img src="/getAvatar?id=${userInfo.id}"></a>
        </div>
        <div class="vertical">
            <div style="font-size: 16px">上传者：${albumInfo.userId}</div>
            <div>
                <c:if test="${sessionScope.myInfo.id != albumInfo.userId}">
                    <div style="margin-top:10px">
                        <button type="button" class="layui-btn">关注</button>
                        <a href="/sendMessage?id=${albumInfo.userId}">
                        <button type="button" class="layui-btn">私信</button>
                        </a>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
</div>

<%--展示照片--%>
<div style="margin-top: 20px">
    <c:forEach var="photo" items="${photoList}">
        <div class="album-photo vertical">
            <img src="/getImage?url=${photo.url}"/>
            <div class="gray-color">${photo.name}</div>
        </div>
    </c:forEach>

</div>

</body>
</html>
