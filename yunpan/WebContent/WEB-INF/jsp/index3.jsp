<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

    </head>
    <body>
    <form action="get" >

    <input type="submit" value="获取"></input>

    </form >
    <br/>
    
    
    <form id="form"  action="file" encType="multipart/form-data">
<input type="submit" value="获取"></input>
	<input  type="file" name="file"/></form>
	<br/>
	<form id="form"  action="newdir" >
	<input  type="text" name="dirname"/>
	<input type="submit" value="新建文件夹"></input>
	</form>

	<br/>
	
	<form action="pageup"><input type="submit" value="上一级"></form>
	
	<br/>
	
	<table border="8">
	<tr><th>文件名</th><th>类型</th><th>大小</th><th>操作</th></tr>
	<c:forEach var="o" varStatus="vs" items="${paths}">
	<tr>
	<td><a href="getpath?path=${o.path}&id=${o.id}" >${o.name}</a></td>
	<td>${o.type}</td>
	<td>${o.size}</td>
	<td><a>下载</a>  <a>重命名</a> <a href="deletePath?id=${o.id}">删除</a></td>

	</tr>
	
</c:forEach>
</table>
	
	
	
	
	
    </form>
    

    </body>
</html>