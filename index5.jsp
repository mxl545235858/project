<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
<link rel="stylesheet" href="resources/css/form.css" />

    </head>
    <body>
	<!--  <a href="/yunpan/">首页</a><br/>
<a href="myfile">我的文件</a>
    <br/>
    
    

<form id="form"  action="findfile" >
<input  type="text" name="filename"/>
	<input type="submit" value="查找"></input>
	</form>
	<br/>-->
	
	<table>
	<tr><th>文件名</th><th>类型</th><th>大小</th><th>操作</th></tr>
	<c:forEach var="o" varStatus="vs" items="${dirpaths}">
	<tr>
	<td><a href="getpath?path=${o.path}&id=${o.id}" >${o.name}</a></td>
	<td>${o.type}</td>
	<td>${o.size}</td>
	<td>  
		<a href="deletePath?id=${o.id}">删除</a>
		<a href="index4?id=${o.id}">重命名</a>
	</td>

	</tr>
	
</c:forEach>
	<c:forEach var="o" varStatus="vs" items="${otherspaths}">
	<tr>
	<td>${o.name}</td>
	<td>${o.type}</td>
	<td>${o.size}</td>
	<td>
		<a href="deletePath?id=${o.id}">删除</a>
		<a href="index4?id=${o.id}">重命名</a>
		<a href="donlowd?location=${o.location}">下载</a>  
	</td>

	</tr>
	
</c:forEach>
</table>
    </form>
    

    </body>
</html>