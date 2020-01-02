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
    <meta charset="UTF-8">
    <title>${userInfo.name}的主页</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css" type="text/css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/st-style.css" type="text/css"/>
    <script src="https://libs.baidu.com/jquery/2.1.4/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/layui/layui.all.js"></script>
    <script type="text/javascript">
        <%--设置相册div的鼠标划入划出显示编辑删除--%>
        $(function () {
            $('.card-box.corner-img-div').mouseenter(function () {
                $(this).find(".corner-img-item.layui-btn-group").show();
            });

            $('.card-box.corner-img-div').mouseleave(function () {
                $(this).find(".corner-img-item.layui-btn-group").hide();
            });

            $("#GUANZHU").click(function () {
                var toId = "${userInfo.id}"
                $.ajax({
                    url: "http://localhost:8080/addfow",
                    type: "post",
                    data: {"TID":toId},
                    dataType: "json",
                    success: function (result) {
                        console.log(result.data);
                        if (result.status == 0) {
                            window.location.reload();
                        } else {
                            layer.open({
                                offset:'250px',
                                title: '关注失败 请登录！',
                                content: '关注失败',
                                shade: 0.5,
                                yes: function(){
                                    window.location.href = "http://localhost:8080/";
                                }
                            });

                        }
                    },
                    error: function () {
                        alert("关注异常");
                    }
                });
            });

            $("#QUGUAN").click(function () {
                var toId = "${userInfo.id}"
                layer.confirm('确定取消关注吗?', {icon: 3, title:'提示',offset:'250px'}, function(index){
                    $.ajax({
                        url: "http://localhost:8080/delfow",
                        type: "post",
                        data: {"TID":toId},
                        dataType: "json",
                        success: function (result) {
                            console.log(result.status);
                            console.log(result.data);
                            //如果删除成功
                            if (result.status == 0) {
                                layer.msg('取消关注成功!', {icon: 6});
                                window.location.reload();
                            } else {
                                window.location.reload();
                            }
                        },
                        error: function () {
                            alert("取消关注发生异常");
                        }
                    });
                    layer.close(index);
                });
            });
        });
        
        function clickDel(e) {
            var albumId = $(e).attr("data-id");
            var albumName = $(e).attr("data-name");
            layer.confirm('确定删除该相册「' + albumName +'」吗?该相册下的照片也会一并删除。', {icon: 3, title:'提示'}, function(index){
                $.ajax({
                    url: "http://localhost:8080/delAlbum",
                    type: "post",
                    data: {"albumId": albumId},
                    dataType: "json",
                    success: function (result) {
                        //如果删除成功
                        if (result.status == 0) {
                            layer.msg('删除成功!', {icon: 6});
                            window.location.reload();
                        } else {
                            window.location.reload();
                        }
                    },
                    error: function () {
                        alert("删除相册发生异常");
                    }
                });
                layer.close(index);
            });
        }
    </script>
</head>
<body class="bg-gray">

<jsp:include page="header.jsp"></jsp:include>

<div class="st-banner" style="height: 150px"></div>

<div style="background: white">
    <div class="home-information-box">
        <div class="information-headimg-box">
            <img src="/getAvatar?id=${userInfo.id}" width="150px" height="150px"/>
        </div>
    </div>

    <div class="text-center" style="margin-top: 80px">
        <div id="user_name" style="font-size: 24px">${userInfo.name}</div>
        <div id="user_id" style="font-size: 14px;color: #bbbbbb">id:${userInfo.id}</div>
        <%--自己的主页没有关注和私信--%>
        <c:if test="${sessionScope.myInfo.id != userInfo.id}">
            <div style="margin-top:10px;padding-bottom: 10px">
                <c:if test="${empty sessionScope.myInfo}">
                    <a href="/index">
                    <button id = "GUANZHU1" type="button" class="layui-btn">登录后关注</button>
                    </c:if>

                        <c:if test="${not empty sessionScope.myInfo}">
                        <c:if test="${isFollow == 1}">
                        <button id = "QUGUAN" type="button" class="layui-btn layui-btn-primary">取消关注</button>
                        </c:if>
                        <c:if test="${isFollow == 0}">
                        <button id = "GUANZHU" type="button" class="layui-btn">关注</button>
                        </c:if>
                        </c:if>

                <a href="/sendMessage?id=${albumInfo.userId}">
                    <button type="button" class="layui-btn">私信</button>
                </a>
            </div>
        </c:if>
        <c:if test="${userInfo.userState=='banned'}">
            <i class="layui-icon layui-icon-tips" style="font-size: 18px; color: #bbbbbb;"></i>
            <div style="font-size: 18px;color: #bbbbbb;">该用户已被封禁!</div>
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
                        <c:if test="${sessionScope.myInfo.userState!='banned'}">
                            <a href="/createAlbum">
                                <button type="button" class="layui-btn layui-btn-radius">创建相册</button>
                            </a>
                            <a href="/uploadPhoto"><button type="button" class="layui-btn layui-btn-radius">上传照片</button></a>
                        </c:if>
                        <c:if test="${sessionScope.myInfo.userState=='banned'}">
                            <a title="您已被封禁!">
                                <button type="button" class="layui-btn layui-btn-disabled" >创建相册</button>
                            </a>
                            <a title="您已被封禁!">
                                <button type="button" class="layui-btn layui-btn-disabled">上传照片</button>
                            </a>
                        </c:if>
                    </div>
                </c:if>
                <div id="albums" style="margin-top: 20px">
                    <c:if test="${albumList.size() == 0}">
                        <div>
                            TA暂时还没有上传相册哦!
                        </div>
                    </c:if>
                    <c:forEach var="album" items="${albumList}">

                        <div class="card-box corner-img-div">
                            <%--编辑删除的图标，仅当为自己主页时才有--%>
                            <c:if test="${sessionScope.myInfo.id == userInfo.id}">
                                <div class="corner-img-item layui-btn-group" style="display: none;">
                                    <a href="/editAlbum?albumId=${album.id}">
                                    <button type="button" class="layui-btn layui-btn-primary layui-btn-sm">
                                        <i class="layui-icon">&#xe642;</i>
                                    </button>
                                    </a>
                                    <button type="button" onclick = "clickDel(this)" data-id = '${album.id}' data-name = '${album.name}' class="layui-btn layui-btn-primary layui-btn-sm">
                                        <i class="layui-icon">&#xe640;</i>
                                    </button>
                                </div>
                            </c:if>
                            <div class="card-image">
                                    <%--到底传id还是直接传url好呢--%>
                                <a href="/album?albumId=${album.id}"><img class="fill-box" src="/getImage?url=${album.coverId}"></a>
                            </div>
                            <div class="card-info">
                                <div class="card-info-title">${album.name}[${album.category}]</div>
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
