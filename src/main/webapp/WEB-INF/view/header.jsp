<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<div class="layui-nav layui-bg-green st-header">
    <div class="left" style="padding: 10px;margin-left: 60px;">
        <a href="/home" class="horizentol">
            <img width="30px" height="30px" src="${pageContext.request.contextPath}/static/image/album-banner.png">
            <div class="st-white-font" style="padding: 10px 5px">电子相册</div>
        </a>
    </div>

    <li class="layui-nav-item right" lay-unselect="" style="margin-right: 60px;display:${sessionScope.isLogin?"none":"block"}">
        <a href="/index" style="">
            <div class="st-white-font">登录 | 注册</div>
        </a>
        <!--TODO:iconfont-->
    </li>
    <li class="layui-nav-item right" lay-unselect="" style="display:${sessionScope.isLogin?"block":"none"};margin-right: 20px">
        <a href="/user?id=${sessionScope.myInfo.id}"><%--<i class="layui-icon layui-icon-username"style="color: white"></i>--%>
         <div class="st-white-font">当前账户:${sessionScope.myInfo.name}</div>
        </a>
        <dl class="layui-nav-child">
            <dd><a>权限:${sessionScope.myInfo.type}</a></dd>
            <dd><a href="/me/albums">管理相册</a></dd>
            <dd><a href="/me/photos">管理照片</a></dd>
            <dd><a href="/me/messages">我的私信</a></dd>
            <dd><a href="/me/follow">我的关注</a></dd>
            <dd><a href="/me/info">个人信息</a></dd>
            <c:if test="${sessionScope.myInfo.type=='admin'}">
                <dd><a href="/admin/users">管理员后台</a></dd>
            </c:if>
            <dd id="logout"><a href="">退出</a></dd>
        </dl>
    </li>


</div>
<script>
    layui.use('element', function(){
        var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块

        //监听导航点击
        element.on('nav(demo)', function(elem){
            //console.log(elem)
            layer.msg(elem.text());
        });
        element.render();
    });

    $("#logout").click(function () {
        //TODO 这里有有关session的bug
        //如果当前是/me或者/admin或者/sendMessage，则跳转回主页，否则刷新
        //没有吊用
        // var path = window.location.pathname;
        // var ii = ['/me','/admin','/sendMessage'];
        // ii.forEach(function (value) {
        //     if(path.indexOf(value) === 0 ){
        //         window.location.href = "/home";
        //         return;
        //     }
        // });
        $.ajax({
            url:"http://localhost:8080/logout",
            type:"get",
            dataType:"json",
            success:function () {
                window.location.reload();
            },
            error:function () {
                window.location.href = "/home";
            }
        })
    });

</script>
</body>
</html>
