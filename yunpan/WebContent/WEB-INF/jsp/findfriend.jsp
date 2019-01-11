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
    <form action="findfriend" >
		<input name="name" type="text" value="" /><br/>
        <input type="submit" value="查找"></input>

</form>
	<br/>

	<table border="8">
	<tr><th>用户名</th><th>操作</th></tr>
	<c:forEach var="o" varStatus="vs" items="${user}">
	<tr>
	<td>${o.name}</td>

	<td>  
		<a href="addfriend?id=${o.id}">申请</a>
	</td>

	</tr>
	
</c:forEach>
</table>
    </body>
</html>