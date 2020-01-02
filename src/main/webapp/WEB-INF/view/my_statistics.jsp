<%--
  Created by IntelliJ IDEA.
  User: 加法还没学
  Date: 2020/1/1
  Time: 21:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${myInfo.name} -我的统计</title>
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
                <li class="layui-this">我的统计</li>
            </ul>
            <div class="layui-tab-content" style="">
                <div class="layui-tab-item layui-show">
                    <div class="horizentol">
                        <div class="vertical st-box">
                            <div class="st-data">${mystatis.albumCount}</div>
                            <div class="st-content">发布的相册数量</div>
                        </div>

                        <div class="vertical st-box">
                            <div class="st-data">${mystatis.commentCount}</div>
                            <div class="st-content">发布的评论数量 </div>
                        </div>

                        <div class="vertical st-box">
                            <div class="st-data">${mystatis.followCount}</div>
                            <div class="st-content">我关注的数量</div>
                        </div>

                        <div class="vertical st-box">
                            <div class="st-data">${mystatis.followedCount}</div>
                            <div class="st-content">我被关注的数量</div>
                        </div>

                        <div class="vertical st-box">
                            <div class="st-data">${mystatis.photoCount}</div>
                            <div class="st-content">我的照片的数量</div>
                        </div>
                    </div>
            </div>

        </div>
    </div>
</div>

<script>

</script>

</div>
</body>
</html>
