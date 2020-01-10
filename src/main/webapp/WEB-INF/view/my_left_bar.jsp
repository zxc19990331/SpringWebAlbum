<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 52491
  Date: 2019/12/29
  Time: 15:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<div class = "vertical" style="width: 250px">
    <div class="personal-card vertical" style="margin-bottom: 20px;padding:10px">
        <div class="me-avatar content-center">
            <img src="/getAvatar?id=${sessionScope.myInfo.id}" width="150px" height="150px"/>
        </div>
        <div class = "content-center" style="font-size: 24px">${sessionScope.myInfo.name}</div>
        <div class = "content-center" style="font-size: 14px;color: #bbbbbb">ID:${sessionScope.myInfo.id}</div>
        <div class = "content-center" style="font-size: 14px;color: #bbbbbb">权限:${sessionScope.myInfo.type}</div>

        <%--封禁状态什么都不能干--%>
        <c:if test="${sessionScope.myInfo.userState == 'banned'}">
            <i class="layui-icon layui-icon-tips" style="margin:0 auto;font-size: 18px; color: #bbbbbb;"></i>
            <div style="font-size: 14px;color: #bbbbbb;margin:0 auto">该用户已被封禁!</div>
        </c:if>

        <c:if test="${sessionScope.myInfo.userState != 'banned'}">
            <a href="/createAlbum" class = "content-center" target="_blank">
                <button type="button" class=" layui-btn layui-btn-primary}">创建相册</button>
            </a>
            <a href="/uploadPhoto" style="margin: 0 auto" target="_blank">
                <button type="button" class="layui-btn layui-btn-primary">上传照片</button>
            </a>
        </c:if>

    </div>
    <div class="personal-menu" style="margin-bottom: 20px">
        <ul>
            <li><a href="/me/albums">管理相册</a></li>
            <li><a href="/me/photos">管理照片</a></li>
            <li><a href="/me/messages">我的私信</a></li>
            <li><a href="/me/follow">我的关注</a></li>
            <li><a href="/me/info">个人信息与资料</a></li>
            <li><a href="/me/mysta">我的统计</a></li>
        </ul>
    </div>

    <%--管理员才有的后台菜单--%>
    <c:if test="${sessionScope.myInfo.type == 'admin'}">
        <div class="personal-menu" style="margin-bottom: 20px">
            <ul>
                <li><a href="/admin/albums">管理用户相册</a></li>
                <li><a href="/admin/users">管理用户状态</a></li>
                <li><a href="/admin/operations">操作记录</a></li>

            </ul>
        </div>
    </c:if>
</div>
</body>
</html>
