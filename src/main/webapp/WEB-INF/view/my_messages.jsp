<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 52491
  Date: 2019/12/30
  Time: 18:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css" type="text/css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/st-style.css" type="text/css"/>
    <script src="https://libs.baidu.com/jquery/2.1.4/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/layui/layui.all.js"></script>

</head>
<body class="bg-gray">
<jsp:include page="header.jsp"></jsp:include>
<div class="st-main horizentol" style="margin-top: 15px">

    <jsp:include page="my_left_bar.jsp"></jsp:include>
    <div class="personal-content">
        <div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
            <ul class="layui-tab-title" style="">
                <li class="layui-this">已收到私信</li>
                <li>已发送私信</li>
            </ul>
            <div class="layui-tab-content">
                <div class="layui-tab-item layui-show">
                    <c:forEach items="${reciveMessageList}" var="message">
                        <div class="horizentol msg-item">
                            <div class="imgdiv">
                                <a href="/user?id=${message.fromId}">
                                <img class="imgcss" src="/getAvatar?id=${message.fromId}"/>
                                </a>
                            </div>
                            <div class="vertical msg-right-box">
                                <div  style="font-weight: bold">${message.fromId}</div>
                                <div class="msg-detail-content">${message.context}</div>
                                <div class="horizentol">
                                    <div class="msg-detail-content detail-font">${message.createTime}</div>
                                    <a href="/sendMessage?id=${message.fromId}" target="_blank"><div class="button-font" style="margin-left: 50px">回复</div></a>
                                </div>
                            </div>
                        </div>
                </c:forEach>
                </div>
                <div class="layui-tab-item">
                    <c:forEach items="${sendMessageList}" var="message">
                        <div class="horizentol msg-item">
                            <div class="imgdiv">
                                <a href="/user?id=${message.fromId}">
                                <img class="imgcss" src="/getAvatar?id=${message.toId}"/>
                                </a>
                            </div>
                            <div class="vertical msg-right-box">
                                <div  style="font-weight: bold">发送给:${message.toId}</div>
                                <div class="msg-detail-content">${message.context}</div>
                                <div class="msg-detail-content detail-font">${message.createTime}</div>
                            </div>
                        </div>
                </c:forEach></div>
                </div>

            </div>
        </div>
    </div>

    <script>

    </script>

</div>
</body>
</html>