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
	<tr><th>文件名</th><th>好友用户名</th><th>操作</th></tr>
	<c:forEach var="o" varStatus="vs" items="${friendshare}">
	<tr>
	<td>${o[0]}</td>
	<td>${o[1]}</td>
	<td>  
		<a href="donlowd?location=${o[2]}">下载</a>  
	</td>

	</tr>
	
</c:forEach>
</table>
    </body>
</html>