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
	<tr><th>我的分享</th><th>操作</th></tr>
	<c:forEach var="o" varStatus="vs" items="${myshare}">
	<tr>
	<td>${o.name}</td>

	<td>  
		<a href="removeshare?pathsid=${o.id}">取消分享</a>
	</td>

	</tr>
	
</c:forEach>
</table>
    </body>
</html>