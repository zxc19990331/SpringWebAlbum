<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 加法还没学
  Date: 2019/12/31
  Time: 21:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${myInfo.name} -我的关注</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css" type="text/css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/st-style.css" type="text/css"/>
    <script src="https://libs.baidu.com/jquery/2.1.4/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/layui/layui.all.js"></script>

    <script type="text/javascript">

        function clickdelfollow(e) {
            var toId = $(e).attr("data-id");

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
        }
    </script>
</head>
<body class="bg-gray">
<jsp:include page="header.jsp"></jsp:include>
<div class="st-main horizentol" style="margin-top: 15px">
    <jsp:include page="my_left_bar.jsp"></jsp:include>
    <div class="personal-content">
        <div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
            <ul class="layui-tab-title" style="">
                <li class="layui-this">我的关注</li>
            </ul>
            <div class="layui-tab-content" style="">
                <div class="layui-tab-item layui-show">
                    <div class="horizentol">
                        <c:forEach items="${followList}" var="user">
                            <div id="toFollow" class="horizentol" style="width: 350px;margin-right: 15px">
                                <div class="imgdiv follow-item">
                                    <a href="/user?id=${user.id}" target="_blank">
                                        <img class="imgcss" src="/getAvatar?id=${user.id}"/>
                                    </a>
                                </div>
                                <div class="vertical follow-item">
                                    <div class="horizentol">
                                        <div>${user.name}</div>
                                        <div class="detail-font" style="margin-left: 5px">ID:${user.id}</div>
                                    </div>
                                    <div class="detail-font">
                                        ${user.descp}
                                    </div>
                                </div>
                                <button id="delfol" type="button" class="layui-btn layui-btn-sm layui-btn-radius" style="margin-left: 45px"
                                        onclick = "clickdelfollow(this)" data-id = '${user.id}'>
                                    取消关注
                                </button>
                            </div>
                        </c:forEach>
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
