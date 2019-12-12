<%--
  Created by IntelliJ IDEA.
  User: 52491
  Date: 2019/12/11
  Time: 19:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css" type="text/css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/st-style.css" type="text/css"/>
    <script src="https://libs.baidu.com/jquery/2.1.4/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/layui/layui.all.js"></script>
    <script type="text/javascript">

    </script>
        </head>
<body>
<jsp:include page="header.jsp"></jsp:include>

<div class="st-banner" style="height: 150px"></div>
<div class="home-information-box">
    <div class="information-headimg-box">
        <img src="/getImage?url=test.png" width="150px"/>
    </div>
</div>

<div class="text-center" style="margin-top: 80px">
    <div id="user_name" style="font-size: 24px">${sessionScope.myInfo.name}</div>
    <div id="user_id" style="font-size: 14px;color: #bbbbbb">id:${sessionScope.myInfo.id}</div>
</div>

<div class="st-main" style="margin-top: 20px">
    <form class="layui-form">
        <div class="layui-form-item">
            <label class="layui-form-label">相册名</label>
            <div class="layui-input-block">
                <input id="title" type="text" name="title" required  lay-verify="required" placeholder="请输入相册名" autocomplete="off" class="layui-input">
            </div>
        </div>

        <label class="layui-form-label">类别</label>
        <div class="layui-input-block">
            <select id="type" name="type" lay-verify="required">
                <option value="">请选择类别</option>
                <option value="0">人文摄影</option>
                <option value="1">平面设计</option>
                <option value="2">生活记录</option>
                <option value="3">杂图作品</option>
                <option value="4">其他</option>
            </select>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">权限</label>
            <div class="layui-input-block">
                <input type="radio" name="auth" value="公开" title="公开" checked>
                <input type="radio" name="auth" value="私密" title="私密">
                <input type="radio" name="auth" value="仅关注者可见" title="仅关注者可见">
            </div>
        </div>

        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">相册描述</label>
            <div class="layui-input-block">
                <textarea name="desc" placeholder="请输入描述" class="layui-textarea"></textarea>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>


    </form>

    <script>
        layui.use('form', function(){
            var form = layui.form;

            //监听提交
            form.on('submit(formDemo)', function(data){
                var info = data.field;
                info["userId"] = ${sessionScope.myInfo.id};
                console.log(info);
                //layer.msg(JSON.stringify(info));
                $.ajax({
                    url:"http://localhost:8080/submitNewAlbum",
                    type:"post",
                    data:info,
                    dataType:"json",
                    success:function (res) {
                        if(res.status==0){
                            layer.msg("创建成功!");
                            //TODO:跳转到新相册的页面
                            window.location.href = "/album?albumId="+res.data.id;
                        }else {
                            layer.msg("创建失败!");
                        }
                    },
                    error:function () {
                        layer.msg("创建相册遇到问题!");
                    }
                });

                return false;
            });
            //here
            form.render();
        });
    </script>

</div>

</body>
</html>
