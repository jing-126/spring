<%--
  Created by IntelliJ IDEA.
  User: jing
  Date: 2020/8/14
  Time: 17:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="${pageContext.request.contextPath}/js/jquery-3.5.1.min.js"></script>
    <script>
        var userList = new Array();
        userList.push({name:"jing",age:22});
        userList.push({name:"景",age:22});
        $.ajax({
            type:"post",
            url:"/user/req05",
            data:JSON.stringify(userList),
            contentType:'application/json;charset=utf-8'
        })
    </script>
</head>
<body>
    <h1>使用ajax完成集合类型参数传递</h1>
</body>
</html>
