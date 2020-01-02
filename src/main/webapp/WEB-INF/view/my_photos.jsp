<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 52491
  Date: 2019/12/29
  Time: 17:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${myInfo.name} -照片管理</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css" type="text/css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/st-style.css" type="text/css"/>
    <script src="https://libs.baidu.com/jquery/2.1.4/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/layui/layui.all.js"></script>
    <script>
        $(function () {
            <%--if(${choose}){--%>
            <%--$("#album_choose").val('${choose}');--%>
            <%--}else{--%>
            <%--$("#album_choose").get(0).selectedIndex=1--%>
            <%--}--%>
            layui.form.render('select');
        });
    </script>
</head>
<body class="bg-gray">
<jsp:include page="header.jsp"></jsp:include>
<div class="st-main horizentol" style="margin-top: 15px">
    <jsp:include page="my_left_bar.jsp"></jsp:include>
    <div class="personal-content">
        <form class="layui-form">
            <div id = "select_album" class="layui-form-item" style="width: 300px">
                <%--选择相册的select--%>
                <select data-type = "reload" id="album_choose" name="album_choose" lay-filter="album_choose">
                    <c:forEach var="album" items="${albumList}">
                        <option value="${album.id}">${album.name}</option>
                    </c:forEach>
                </select>
            </div>
        </form>
        <table class="layui-hide" id="test" lay-filter="photo_table"></table>
    </div>

</div>

<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="cover">封面</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>

<script>
    layui.use(['table', 'form'], function () {
        var form = layui.form;
        //监听select table reload
        form.on('select(album_choose)',function (data) {
            console.log("change select");
            var album_choose = data.value;
            table.reload('photo_list', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                ,where: {
                    albumId:album_choose
                }
            }, 'data');
        });

        form.render();

        //不知道js怎么直接获取后端的list，就从dom里取了
        var list = [];
        $("#album_choose").each(function(i){
            list.push(this.value);
        });

        var table = layui.table;
        var albumId = "";
        if(list.length > 0) {
            albumId = list[0];
        }

        table.render({
            elem: '#test'
            ,url:'http://localhost:8080/me/getMyPhoto'
            ,where:{albumId:albumId}
            ,cols: [[
                {field:'id',  title: '照片ID', sort: true}
                ,{field:'name',  title: '照片名',event :'name', sort:true}
                ,{field: 'url', title: '预览',
                    templet: function(d){
                        var url = '/getImage?url=' + d.url;
                        return '<div><img  src= "'+url+'" alt="" width="50px" height="50px"></a></div>';
                    },event :'preview'
                }
                ,{field:'createTime',  title: '创建时间',sort:true}
                ,{field:'praiseCount',  title: '点赞',width:80,sort: true}
                ,{fixed: 'right', width:178, align:'center', toolbar: '#barDemo'}

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
            ,id: "photo_list"
        });

        //填入table的lay-filter参数
        table.on('tool(photo_table)',function(obj){
            var data = obj.data;
            console.log(obj);
            //预览
            if(obj.event === 'preview'){
                console.log("preview");
                var url = "/getImage?url=" + obj.data.url;
                var width = 800;
                var height = 800;
                // 创建对象
                var img = new Image();
                // 改变图片的src
                img.src = url;
                // 判断是否有缓存
                if(img.complete){
                    // 打印
                    width = img.width>width?width:img.width;
                    height = img.height>height?height:img.height + 42;
                }else{
                    // 加载完成执行
                    img.onload = function(){
                        width = img.width>width?width:img.width;
                        height = img.height>height?height:img.height + 42;
                    }
                }
                width = width + 'px';
                height = height + 'px';
                //页面层
                layer.open({
                    title:'预览',
                    type: 1,
                    skin: 'layui-layer-rim', //加上边框
                    area: [width, height], //宽高
                    shadeClose: true, //开启遮罩关闭
                    end: function (index, layero) {
                        return false;
                    },
                    content: '<div style="text-align:center"><img src="'+url+'" /></div>'
                });
            }
            else if(obj.event === 'del'){
                var albumId = $('#album_choose').val();
                layer.confirm('真的删除该照片吗？', function(index){
                    $.ajax({
                        url:"http://localhost:8080/delPhoto",
                        type:"get",
                        data:{albumId:albumId,photoId:obj.data.id},
                        dataType:"json",
                        success:function (res) {
                            if(res.status === 0){
                                layer.msg("删除照片成功!");
                                obj.del();
                            }else{
                                layer.msg("删除照片失败!");
                            }
                            layer.close(index);
                        },
                        error:function () {
                            layer.msg("操作失败!");
                        }
                    });


                });
            }
            else if(obj.event === 'cover'){
                var albumId = $('#album_choose').val();
                $.ajax({
                    url:"http://localhost:8080/setCover",
                    type:"get",
                    data:{albumId:albumId,photoId:obj.data.url},
                    dataType:"json",
                    success:function (res) {
                        if(res.status === 0){
                            layer.msg("设置封面成功!");
                        }else{
                            layer.msg("设置封面失败!");
                        }

                    },
                    error:function () {
                        layer.msg("操作失败!");
                    }
                })
            }
            else if(obj.event === 'name'){
                layer.prompt({
                    formType:0,
                    title:'请重命名该照片',
                    area:['400px','80px'],
                    btnAlign:'c',
                    offset:'auto'
                },function(value,index,elem){
                    var name = value;
                    $.ajax({
                        url: "http://localhost:8080/me/changePhotoName",
                        type: "post",
                        data: {"photoId": obj.data.id,"name": name},
                        dataType: "json",
                        offset:'auto',
                        success: function (result) {
                            //如果删除成功
                            if (result.status == 0) {
                                layer.msg('更改成功!', {icon: 6,offset:250});
                                window.location.reload();
                            } else {
                                window.location.reload();
                            }
                        },
                        error: function () {
                            alert("更改发生异常");
                        }
                    });
                    layer.close(index);
                });
            }
        })
    });
</script>
</body>
</html>
