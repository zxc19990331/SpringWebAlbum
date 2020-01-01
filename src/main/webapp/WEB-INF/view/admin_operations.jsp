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
            <table class="layui-hide" id="test" lay-filter="operation_table"></table>
        </div>
    </div>


    <script>
        layui.use('table', function(){
            var table = layui.table;
            table.render({
                elem: '#test'
                ,url:'http://localhost:8080/admin/getOperationList'
                ,cols: [[
                    {field:'id',  title: '操作记录ID', sort: true}
                    ,{field:'fromId',  title: '管理员ID',sort:true}
                    ,{field:'toId',  title: '用户/相册ID'}
                    ,{field:'operateName',  title: '操作',sort:true}
                    ,{field:'createTime',  title: '创建时间',sort:true}
                    ,{field:'note',  title: '备注'}
                ]]
                ,page: true
                ,parseData: function(res){ //res 即为原始返回的数据
                    console.log(res);
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
