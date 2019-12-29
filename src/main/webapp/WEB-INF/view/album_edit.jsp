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
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css" type="text/css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/st-style.css" type="text/css"/>
    <script src="https://libs.baidu.com/jquery/2.1.4/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/layui/layui.all.js" charset="utf-8"></script>
    <script type="text/javascript">
        $(function(){
            // $("#jumpMenu").val(要选中的option的value值即可);
            $("#type").val('${album.category}');
            layui.form.render('select');
        });
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
                <input id="title" type="text" name="title" value="${album.name}" required  lay-verify="required" placeholder="请输入相册名" autocomplete="off" class="layui-input">
            </div>
        </div>

        <label class="layui-form-label">类别</label>
        <div class="layui-input-block">
            <select id="type" name="type" lay-verify="required">
                <option value="">请选择类别</option>
                <option value="人文摄影">人文摄影</option>
                <option value="平面设计">平面设计</option>
                <option value="生活记录">生活记录</option>
                <option value="杂图作品">杂图作品</option>
                <option value="其他">其他</option>
            </select>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">权限</label>
            <div class="layui-input-block">
                <input type="radio" name="auth" value="normal" title="公开" checked>
                <input type="radio" name="auth" value="private" title="私密">
            </div>
        </div>

        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">相册描述</label>
            <div class="layui-input-block">
                <textarea name="desc" placeholder="请输入描述" class="layui-textarea">${album.descp}</textarea>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="formDemo">提交修改</button>
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
                info["userId"] = '${sessionScope.myInfo.id}';
                info["albumId"] = '${album.id}';
                console.log(info);
                //layer.msg(JSON.stringify(info));
                $.ajax({
                    url:"http://localhost:8080/submitEditAlbum",
                    type:"post",
                    data:info,
                    dataType:"json",
                    success:function (res) {
                        if(res.status==0){
                            layer.msg("编辑成功!");
                            //TODO:跳转到新相册的页面
                            window.location.href = "/album?albumId="+res.data.id;
                        }else {
                            layer.msg("编辑失败!");
                        }
                    },
                    error:function () {
                        layer.msg("编辑相册信息遇到问题!");
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
