<%--
  Created by IntelliJ IDEA.
  User: 52491
  Date: 2019/12/29
  Time: 23:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${myInfo.name} -管理后台</title>
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
        <div class = "personal-content">
            <table class="layui-hide" id="test" lay-filter="user_table"></table>
        </div>
    </div>

    <script type="text/html" id="checkboxTpl">
        <%--layjpt--%>
        <input type="checkbox" name="lock" value="{{d.user_state}}" title="封禁" lay-filter="lockDemo" {{ d.user_state == 'banned' ? 'checked' : '' }}>
    </script>

    <script>
        layui.use('table', function(){
            var form = layui.form;
            form.render();
            var table = layui.table;
            table.render({
                elem: '#test'
                ,url:'http://localhost:8080/admin/getUserList'
                ,cols: [[
                    {field:'id',  title: '用户ID', sort: true}
                    ,{field:'name',  title: '用户昵称',sort:true}
                    ,{field:'descp',  title: '个人简介'}
                    ,{field:'createTime',  title: '注册时间',sort:true}
                    ,{field:'type',  title: '权限',sort:true}
                    ,{field:'userState',  title: '状态', width:110, sort: true, templet: '#checkboxTpl', unresize: true}
                ]]
                ,page: true
                ,parseData: function(res){ //res 即为原始返回的数据
                    return {
                        "code": res.status, //解析接口状态
                        "msg": res.msg, //解析提示文本
                        "count": res.data.length, //解析数据长度
                        "data": res.data //解析数据列表
                    };
                }
            });
        });

    </script>
</body>
</html>
