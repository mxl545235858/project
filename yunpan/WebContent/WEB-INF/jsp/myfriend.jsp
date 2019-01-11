<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

    </head>
    <body>
    <a href="/yunpan/">首页</a><br/><a href="myfile">我的文件</a><br/>

	<br/>

	<table border="8">
	<tr><th>我的好友</th><th>操作</th></tr>
	<c:forEach var="o" varStatus="vs" items="${friend}">
	<tr>
	<td>${o.fname}</td>

	<td>  
		<a href="removefriend?fid=${o.fid}">删除</a>
	</td>

	</tr>
	
</c:forEach>
</table>
    </body>
</html>