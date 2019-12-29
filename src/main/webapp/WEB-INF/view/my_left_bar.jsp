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
            <img src="/getImage?url=test.png" width="150px"/>
        </div>
        <div class = "content-center" style="font-size: 24px">${sessionScope.myInfo.name}</div>
        <div class = "content-center" style="font-size: 14px;color: #bbbbbb">ID:${sessionScope.myInfo.id}</div>
        <div class = "content-center" style="font-size: 14px;color: #bbbbbb">权限:${sessionScope.myInfo.type}</div>

    <%--封禁状态什么都不能干--%>
        <a href="/createAlbum" class = "content-center" target="_blank">
        <button type="button" class=" layui-btn ${sessionScope.myInfo.userState=='banned'?'layui-btn-disabled':'layui-btn-primary'}">创建相册</button>
        </a>
        <a href="/uploadPhoto" style="margin: 0 auto" target="_blank">
        <button type="button" class="layui-btn ${sessionScope.myInfo.userState=='banned'?'layui-btn-disabled':'layui-btn-primary'}">上传照片</button>
        </a>
    </div>
    <div class="personal-menu" style="margin-bottom: 20px">
        <ul>
            <li><a href="/me/albums">管理相册</a></li>
            <li><a href="/me/photos">管理照片</a></li>
            <li><a href="/me/info">个人信息与资料</a></li>
            <li><a href="">我的统计</a></li>
        </ul>
    </div>
</div>
</body>
</html>
