<%--
  Created by IntelliJ IDEA.
  User: jing
  Date: 2020/8/14
  Time: 16:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form method="post" action="${pageContext.request.contextPath}/user/req04">
        第一个user：<br>
        <input type="text" name="userList[0].name"><br>
        <input type="text" name="userList[0].age"><br>
        第二个user：<br>
        <input type="text" name="userList[1].name"><br>
        <input type="text" name="userList[1].age"><br>
        <input type="submit" value="提交">
    </form>
</body>
</html>
