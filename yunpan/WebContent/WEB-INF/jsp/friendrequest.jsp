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
		<a style="color: #808080;text-decoration: none;" href="javascript:agreefq(${o.uid},${o.id});">同意</a>&nbsp&nbsp&nbsp<a style="color: #808080;text-decoration: none;" href="javascript:unagreefq(${o.id});">拒绝</a>
	</td>

	</tr>
	
</c:forEach>
</table>

<script>
function agreefq(uid,id) {

	$.ajax({
		url : "agreefq",
		type: 'POST',
		data: {"uid": uid,"id": id},
		success : function(msg) {
			$("#show").html(msg);
		},
		error : function() {

		}
	});
}

function unagreefq(id) {

	$.ajax({
		url : "unagreefq",
		type: 'POST',
		data: {"id": id},
		success : function(msg) {
			$("#show").html(msg);
		},
		error : function() {

		}
	});
}

</script>
    </body>
</html>