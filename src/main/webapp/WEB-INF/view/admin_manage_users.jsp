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
        {{# if(d.userState == 'banned') {}}
        <button type="button" class="layui-btn layui-btn-danger layui-btn-xs ">封禁</button>
        {{# }else{ }}
        <button type="button" class="layui-btn layui-btn-primary layui-btn-xs">正常</button>
        {{# } }}
        <%--<input type="checkbox" name="lock" value="{{d.user_state}}" title="封禁" lay-filter="lockDemo" {{ d.user_state == 'banned' ? 'checked' : '' }}>--%>
    </script>

    <script type="text/html" id="barDemo">
        {{# if(d.userState == 'banned') {}}
        <a class="layui-btn layui-btn-xs" lay-event="deban">解封</a>
        {{# }else{ }}
        <a class="layui-btn layui-btn-xs" lay-event="ban">封禁</a>
        {{# } }}
        <a class="layui-btn layui-btn-xs" lay-event="check">查看</a>
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
                    ,{field:'type',  title: '权限',width:80,sort:true}
                    ,{field:'userState',  title: '状态', width:110, sort: true, templet: '#checkboxTpl', unresize: true}
                    ,{fixed: 'right', title :'操作',width:178, align:'center', toolbar: '#barDemo'}
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

            table.on('tool(user_table)',function (obj) {
                var id = obj.data.id;
                if(obj.event == 'check'){
                    window.open("/user?id="+id,"_blank");
                }else if(obj.event == 'ban'){
                    layer.prompt({
                        formType:2,
                        title:'请填写封禁原因（必填）',
                        area:['500px','150px'],
                        btnAlign:'c',
                        offset:'auto'
                    },function(value,index,elem){
                        var note = value;
                        $.ajax({
                            url: "http://localhost:8080/admin/banUser",
                            type: "post",
                            data: {"userId": id,"note": note},
                            dataType: "json",
                            offset:'auto',
                            success: function (result) {
                                //如果删除成功
                                if (result.status == 0) {
                                    layer.msg('封禁成功!', {icon: 6});
                                    window.location.reload();
                                } else {
                                    window.location.reload();
                                }
                            },
                            error: function () {
                                alert("封禁发生异常");
                            }
                        });
                        layer.close(index);
                    });
                }else if(obj.event == 'deban'){
                    layer.confirm('确定解禁该用户id:' + id +'吗?。', {icon: 3, title:'提示'}, function(index){
                        $.ajax({
                            url: "http://localhost:8080/admin/debanUser",
                            type: "post",
                            data: {"userId": id},
                            dataType: "json",
                            offset:'auto',
                            success: function (result) {
                                //如果删除成功
                                if (result.status == 0) {
                                    //TODO 网页内静态改变table元素值，可以不用刷新，改善观感
                                    layer.msg('解禁成功!', {icon: 6});
                                    window.location.reload();
                                } else {
                                    window.location.reload();
                                }
                            },
                            error: function () {
                                alert("解禁发生异常");
                            }
                        });
                        layer.close(index);
                    });
                }

            });
        });

    </script>
</body>
</html>
