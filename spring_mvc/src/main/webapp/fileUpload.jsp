<%--
  Created by IntelliJ IDEA.
  User: jing
  Date: 2020/8/18
  Time: 19:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath}/user/req14">
        名称：<input type="text" name="username"><br>
        选择文件：<input type="file" name="uploadFile"><br>
        选择文件：<input type="file" name="uploadFile"><br>
        <input type="submit" value="提交">
    </form>
</body>
</html>
