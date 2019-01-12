<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="resources/js/shubiao.js"></script>
        
        <title>JSP Page</title>

    </head>
    <body>
   

	<table id="table" >
	<tr><th>用户名</th><th>操作</th></tr>
	<c:forEach var="o" varStatus="vs" items="${friendrequest}">
	<tr>
	<td>${o.name}</td>

	<td>  
		<a href="agreefq?uid=${o.uid}&id=${o.id}">同意</a><a href="unagreefq?id=${o.id}">拒绝</a>
	</td>

	</tr>
	
</c:forEach>
</table>
    </body>
</html>