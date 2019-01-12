<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="resources/js/shubiao.js"></script>
        <link rel="stylesheet" href="resources/css/form.css" />
        
        <title>JSP Page</title>

    </head>
    <body>
  <div style="text-align: center;position: relative;top: 6%">
    <form  ></form>
    
		<input id="name" style="height: 30px;width: 300px;border: 2px solid;color: lightgrey;border-radius: 4px" name="name" type="text" value="" placeholder="请输入您要搜索的好友	..." required="required" onkeyup="this.value=this.value.replace(/\s+/g,'')"/>
        <input id="find" type="submit" style="height: 30px;width: 50px;" value="查找"></input>

</div>
	<br/>
	<br/>
	<br/>
	<br/>

	<table id="table" >
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
<script>
$("#find").click(function() {

	$.ajax({
		url : "findfriend",
		type: 'POST',
		data: {"name": $("#name").val()},
		success : function(msg) {
			
			$("#show").html(msg);
		},
		error : function() {

		}
	});
});
</script>
    </body>
</html>