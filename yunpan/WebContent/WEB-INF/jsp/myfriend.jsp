<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="resources/js/jquery-3.3.1.min.js"></script>
        <script src="resources/js/shubiao.js"></script>
        
        <title>JSP Page</title>

    </head>
    <body>
   

	<table id="table">
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
 <script>

    window.onload = function showTable() {

   			var tablename = document.getElementById("table");

   			var oRows = tablename.getElementsByTagName("tr");

   			for (var i = 0; i < oRows.length; i++) {

   				oRows[i].onmouseover = function() {

   					this.style.backgroundColor = "whitesmoke";

   				}

   				oRows[i].onmouseout = function() {

   					this.style.backgroundColor = "white";

   				}

   			}

   		}
    </script>
    </body>
   
</html>