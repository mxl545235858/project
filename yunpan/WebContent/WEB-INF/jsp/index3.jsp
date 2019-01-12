<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<!--  <base href="${pageContext.request.contextPath}/"> -->


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="resources/css/shouye.css" />
<link rel="stylesheet" href="resources/css/form.css" />

<!--<link rel="stylesheet" href="resources/css/bootstrap.min.css" />  -->
<script src="resources/js/jquery-3.3.1.min.js"></script>
<script src="resources/js/shubiao.js"></script>


<script src="resources/js/bootstrap.min.js"></script>


<title>JSP Page</title>

</head>
<body>
	<div class="header">
		<div
			style="position: relative; top: 25%; left: 50px; font-size: 25px; font-family: sans-serif; font-weight: bold;">
			<a style="color: white;">青大云盘</a>
		</div>
		<div style="position: relative; left: 50px">
			<img src="resources/imgs/yunpan.jpg"
				style="position: relative; top: 5px;" width="80px" height="50px" />
		</div>
		<div
			style="position: relative; top: 20px; left: 200px; font-size: 20px;">
			<a style="color: white;">主页</a>
		</div>
		<div
			style="position: relative; top: 20px; left: 250px; font-size: 20px;">
			<a style="color: white;">网盘</a>
		</div>
		<div
			style="position: relative; top: 20px; left: 300px; font-size: 20px;">
			<a style="color: white;">分享</a>
		</div>
		<div
			style="position: relative; top: 20px; left: 350px; font-size: 20px;">
			<a style="color: white;">空间</a>
		</div>


		<div
			style="position: relative; left: 70%; top: 30px; text-align: center">
			<a href="/yunpan/"
				style="color: white; text-decoration: none; font-family: sans-serif; font-weight: bold;">退出</a>
		</div>
		<div
			style="position: relative; left: 65%; top: 30px; text-align: center">
			<a
				style="color: white; text-decoration: none; font-family: sans-serif; font-weight: bold;">${user.name}</a>
		</div>

	</div>

	<div class="left" style="border: 1px darkgray solid;">

		<div id="a1"
			style="cursor: pointer; width: 200px; height: 50px; text-align: center">
			<img src="resources/imgs/quanbuwenjian.png"
				style="position: relative; top: 10px" width="30px" height="30px" />
			<a id="a1" style="font-weight: bold;">全部文件</a>
		</div>
		<div id="a2"
			style="cursor: pointer; width: 200px; height: 50px; text-align: center">
			<img src="resources/imgs/wodehaoyou.png"
				style="position: relative; top: 10px" width="30px" height="30px" />
			<a id="a2">我的好友</a>
		</div>
		<div id="a3"
			style="cursor: pointer; width: 200px; height: 50px; text-align: center">
			<img src="resources/imgs/haoyouqingqiu.png"
				style="position: relative; top: 10px" width="30px" height="30px" />
			<a id="a3">好友请求</a>
		</div>
		<div id="a4"
			style="cursor: pointer; width: 200px; height: 50px; text-align: center">
			<img src="resources/imgs/wodefenxiang.png"
				style="position: relative; top: 10px" width="30px" height="30px" />
			<a id="a4">我的分享</a>
		</div>
		<div id="a5"
			style="cursor: pointer; width: 200px; height: 50px; text-align: center">
			<img src="resources/imgs/haoyoufenxiang.png"
				style="position: relative; top: 10px" width="30px" height="30px" />
			<a id="a5">好友分享</a>
		</div>
		<div id="a6"
			style="cursor: pointer; width: 200px; height: 50px; text-align: center">
			<img src="resources/imgs/chazhaohaoyou.png"
				style="position: relative; top: 10px" width="30px" height="30px" />
			<a id="a6" style="font-weight: bold;">查找好友</a>

		</div>
		<hr />
		<a>空间使用情况：</a><br /> <a>空间大小：</a><a style="color: red">50M</a><br /> <a>释放空间：</a>



	</div>

	<div class="right">
		<div class="navigation" style="">

			<div style="position: relative; left: 20px; top: 10px;">
				<form style="display: inline;" form"  action="file"
					encType="multipart/form-data">
					<input style="font-size: 18px;" type="submit" value="上传"></input> <input
						style="font-size: 18px;" type="file" name="file" />
				</form>
			</div>
			<div style="position: relative; left: 30px;; top: 10px;">
				<form style="display: inline;" id="form" action="newdir">
					<input style="font-size: 18px;" type="text" name="dirname" /> <input
						style="font-size: 18px;" type="submit" value="新建文件夹"></input>
				</form>
				<form style="display: inline; position: relative; left: 40%"
					action="pageup">
					<input style="font-size: 18px;" type="submit" value="<<返回 &nbsp;&nbsp;">
				</form>
			</div>

			<div style="position: absolute; top: 7%; left: 60%;" class="search">
				<form></form>
								
					<input id="filename" type="text" name="filename" placeholder="请输入您要搜索的内容..." onkeyup="this.value=this.value.replace(/\s+/g,'')"/>
					<button id="a7" ></button>
					

			</div>
		</div>
		<div class="top">
			<div style="position: relative; left: 3%; top: 10px">
				<a style="color: dimgray">全部文件</a>
			</div>
			<div style="position: relative; left: 80%; top: 10px;">
				<a style="color: dimgray">全部加载</a>
			</div>
		</div>
		<div id="show" class="view" style="border: 1px darkgray solid;overflow-y:scroll;">
			<table id="table">
				<tr>
					<th>文件名</th>
					<th>类型</th>
					<th>大小</th>
					<th>操作</th>
				</tr>
				<c:forEach var="o" varStatus="vs" items="${dirpaths}">
					<tr>
						<td><a href="getpath?path=${o.path}&id=${o.id}">${o.name}</a></td>
						<td>${o.type}</td>
						<td>${o.size}</td>
						<td><a href="deletePath?id=${o.id}">删除</a> 
							<a href="index4?id=${o.id}">重命名</a></td>
							

					</tr>

				</c:forEach>
				<c:forEach var="o" varStatus="vs" items="${otherspaths}">
					<tr>
						<td>${o.name}</td>
						<td>${o.type}</td>
						<td>${o.size}</td>
						<td><a href="deletePath?id=${o.id}">删除</a> <a
							href="index4?id=${o.id}">重命名</a> 
							<a
							href="donlowd?location=${o.location}">下载</a> <a
							href="share?id=${o.id}">分享</a></td>

					</tr>

				</c:forEach>
			</table>
		</div>


	</div>









	<script>
		$(document).ready(function() {
			$("#a1").click(function() {
				window.location.href = "myfile";

			});
		});
		$("#a2").click(function() {

			$.ajax({
				url : "myfriend",
				success : function(msg) {
					$("#show").html(msg);
				},
				error : function() {

				}
			});
		});
		$("#a3").click(function() {

			$.ajax({
				url : "friendrequest",
				success : function(msg) {
					$("#show").html(msg);
				},
				error : function() {

				}
			});
		});

		$("#a4").click(function() {

			$.ajax({
				url : "myshare",
				success : function(msg) {
					$("#show").html(msg);
				},
				error : function() {

				}
			});
		});

		$("#a5").click(function() {

			$.ajax({
				url : "friendshare",
				success : function(msg) {
					$("#show").html(msg);
				},
				error : function() {

				}
			});
		});
		$("#a6").click(function() {

			$.ajax({
				url : "findfriend",
				success : function(msg) {
					$("#show").html(msg);
				},
				error : function() {

				}
			});
		});
		$("#a7").click(function() {

			$.ajax({
				url : "findfile",
				type: 'POST',
				data: {"filename": $("#filename").val()},
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