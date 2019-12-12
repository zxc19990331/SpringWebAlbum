<%--
  Created by IntelliJ IDEA.
  User: 52491
  Date: 2019/12/11
  Time: 18:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<div class="st-header">
    <div class="left" style="padding: 10px;margin-left: 60px;">
        <a href="/home" class="horizentol">
            <img width="30px" height="30px" src="${pageContext.request.contextPath}/static/image/album-banner.png">
            <div class="st-white-font" style="padding: 10px 5px">电子相册</div>
        </a>
    </div>

    <div class="right" style="margin-right: 60px">
        <a href="/index" style="display:${sessionScope.isLogin?"none":"block"}">
            <div class="st-white-font">登录 | 注册</div>
        </a>
        <!--TODO:iconfont-->

        <a href="/user?id=${sessionScope.myInfo.id}" style="display:${sessionScope.isLogin?"block":"none"};margin-right: 20px;margin-top:15px">
            <%--<i class="layui-icon layui-icon-username"style="color: white"></i>--%>
            <div class="st-white-font">当前账户:${sessionScope.myInfo.name}</div>
        </a>
    </div>
</div>
</body>
</html>
