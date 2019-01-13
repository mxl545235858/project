<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="resources/js/shubiao.js"></script>
        <script src="resources/js/jquery-3.3.1.min.js"></script>
        
        
        <title>JSP Page</title>

    </head>
    <body>
    
	<table id="table1">
	<tr><th>我的分享</th><th>操作</th></tr>
	<c:forEach var="o" varStatus="vs" items="${myshare}">
	<tr>
	<td>${o.name}</td>

	<td>  
		<a style="color: #808080;text-decoration: none;" href="javascript:removeshare(${o.id});">取消分享</a>
	</td>

	</tr>
	
</c:forEach>
</table>
<script type="text/javascript">

function removeshare (pathsid) {

	$.ajax({
		url : "removeshare",
		data: {"pathsid": pathsid},
		success : function(msg) {
			$("#show").html(msg);
		},
		error : function() {

		}
	});
}


        window.onload = function showTable() {

			var tablename = document.getElementById("table1");

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