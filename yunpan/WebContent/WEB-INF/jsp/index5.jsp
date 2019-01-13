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
	
	<table id="table">
				<tr>
					<th>文件名</th>
					<th>类型</th>
					<th>大小</th>
					<th>操作</th>
				</tr>
				<c:forEach var="o" varStatus="vs" items="${dirpaths}">
					<tr>
						<td><a href="javascript:getpath('${o.path}',${o.id});">${o.name}</a></td>
						<td>${o.type}</td>
						<td>${o.size}</td>
						<td> 
							<a style="cursor: pointer;" onclick="javascript:tanchu(${o.id})" >重命名</a>
							</td>
							

					</tr>

				</c:forEach>
				<c:forEach var="o" varStatus="vs" items="${otherspaths}">
					<tr>
						<td>${o.name}</td>
						<td>${o.type}</td>
						<td>${o.size}</td>
						<td> 							<a style="cursor: pointer;" id="tanchu" onclick="javascript:tanchu(${o.id})" >重命名</a>

							<a style="color: #808080;text-decoration: none;"
							href="donlowd?location=${o.location}">下载</a> <a style="color: #808080;text-decoration: none;"
							href="javascript:share(${o.id});">分享</a></td>

					</tr>

				</c:forEach>
			</table>
	<div id="main" style="width: 100%;height: 100%;background-color: rgba(0,0,0,0.8);display: none;z-index: 1;position: relative;top:-17%;">
			
			 <div style="text-align: center;position: relative;top:20%;left:28%;width: 500px;height: 200px;background-color: white;"><br/><br/><br/><br/>
    
		<input id="hidden" type="text" hidden />
		<input id="rename" style="height: 30px;width: 300px;border: 2px solid;color: lightgrey;border-radius: 4px" name="rename" type="text" value="" placeholder="请输入您要更改的名字" required="required" onkeyup="this.value=this.value.replace(/\s+/g,'')"/>
        <input id="close" type="button" style="height: 30px;width: 70px;"value="重命名"></input>
        


    </div></div>
		
    <script>
    function tanchu(id){
		 $("#hidden").val(id);
		 $("#main").fadeIn();
	 }
	
	 
	 $("#close").click( function (){
		 $.ajax({
				url : "rename",
				type: 'POST',
				data: {"id": $("#hidden").val(),"rename" : $("#rename").val()},
				success : function(msg) {
					$("#show").html(msg);
				},
				error : function() {

				}
			});
		 $("#main").fadeIn();
	 }
	 );
	
    </script>

    </body>
</html>