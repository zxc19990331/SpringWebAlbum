<%--
  Created by IntelliJ IDEA.
  User: 52491
  Date: 2019/12/29
  Time: 13:25
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
<div class="st-main horizentol" style="">

    <jsp:include page="my_left_bar.jsp"></jsp:include>
    <div class="personal-content">
        <div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
            <ul class="layui-tab-title" style="">
                <li class="layui-this">基本信息</li>
                <li>上传头像</li>
            </ul>
            <div class="layui-tab-content" style="height: 100px;">
                <%--基本信息--%>
                <div class="layui-tab-item layui-show">
                    <form class="layui-form">
                        <div class="layui-form-item">
                            <label class="layui-form-label">用户ID</label>
                            <div class="layui-input-block">
                                <input type="text" name="id" required lay-verify="required" value="${myInfo.id}"
                                       class="layui-input layui-disabled">
                            </div>
                        </div>

                        <div class="layui-form-item">
                            <label class="layui-form-label">注册时间</label>
                            <div class="layui-input-block">
                                <input type="text" name="create_time" required lay-verify="required"
                                       value="${myInfo.createTime}" class="layui-input layui-disabled">
                            </div>
                        </div>

                        <div class="layui-form-item layui-form-text">
                            <label class="layui-form-label">用户昵称</label>
                            <div class="layui-input-block">
                                <input type="text" name="name" required lay-verify="required" placeholder="请输入昵称"
                                       autocomplete="off" value="${myInfo.name}" class="layui-input">
                            </div>
                        </div>

                        <div class="layui-form-item layui-form-text">
                            <label class="layui-form-label">个人简介</label>
                            <div class="layui-input-block">
                                <textarea type="text" name="desc" placeholder="请输入个人简介"
                                          class="layui-textarea">${myInfo.descp}</textarea>
                            </div>
                        </div>

                        <div class="layui-form-item">
                            <div class="layui-input-block">
                                <button class="layui-btn" lay-submit lay-filter="formDemo">提交修改</button>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="layui-tab-item">
                    <div class="layui-upload" style="">
                        <%--头像预览--%>
                        <div class="layui-upload-list">
                            <img src="/getAvatar?id=${sessionScope.myInfo.id}"class="avatar-preview" id="demo1">
                            <p id="demoText"></p>
                        </div>
                        <button type="button" class="layui-btn" id="test2">选择头像</button>
                        <button type="button" id = 'btn_submit'class="layui-btn">上传头像</button>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script>
        layui.use(['form','upload'], function () {
            var form = layui.form;

            //监听提交
            form.on('submit(formDemo)', function (data) {
                var info = data.field;
                $.ajax({
                    url: "http://localhost:8080/me/editInfo",
                    type: "post",
                    data: info,
                    dataType: "json",
                    success: function (res) {
                        if (res.status == 0) {
                            layer.msg("编辑成功!请刷新页面!",{offset:250});
                            //TODO:跳转到新相册的页面
                        } else {
                            layer.msg("编辑失败!");
                        }
                    },
                    error: function () {
                        layer.msg("编辑个人信息遇到问题!",{offset:250});
                    }
                });

                return false;
            });
            //here
            form.render();

            var upload = layui.upload;

            upload.render({
                elem: '#test2'
                ,url: 'http://localhost:8080/me/uploadAvatar'
                ,multiple: true
                ,field:'file'
                ,auto:false
                ,bindAction:'#btn_submit'
                ,choose:function (obj) {
                    obj.preview(function(index, file, result){
                        $('#demo1').attr('src', result); //图片链接（base64）
                    });
                }
                ,before: function(obj){
                }
                ,done: function(res){
                    //上传完毕
                    if(res.status == 0){
                        layer.msg("上传成功！");
                        window.location.reload();
                    }else{
                        layer.msg("上传失败！");
                    }

                }
            });
        });
    </script>

</div>
</body>
</html>
