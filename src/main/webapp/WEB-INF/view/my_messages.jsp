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
            <ul class="layui-tab-title" style="margin-left: 45px">
                <li class="layui-this">已收到私信</li>
                <li>已发送私信</li>
            </ul>
            <div class="layui-tab-content" style="">
                <div class="layui-tab-item layui-show">收到的私信</div>
                <div class="layui-tab-item">发送的私信</div>
                </div>

            </div>
        </div>
    </div>

    <script>

    </script>

</div>
</body>
</html>