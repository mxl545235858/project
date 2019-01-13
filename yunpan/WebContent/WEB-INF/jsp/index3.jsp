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
			<a href="loginout"
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

					<input type="button" id="uplodebt" style="font-size: 18px;" value="上传"></input> 
					<input id="file" style="font-size: 18px;" type="file" name="file" />

			</div>
			<div style="position: relative; left: 30px;; top: 10px;">
				<form style="display: inline;" id="form" ></form>
					<input id="dirname" style="font-size: 18px;" type="text" name="dirname" onkeyup="this.value=this.value.replace(/\s+/g,'')" /> 
					<input id="createdir" style="font-size: 18px;" type="submit" value="新建文件夹"></input>
				
				<form style="display: inline; position: relative; left: 40%">
					<input id="pageup" style="font-size: 18px;" type="button" value="<<返回 &nbsp;&nbsp;">
</form>
			</div>

			<div style="position: absolute; top: 7%; left: 60%;" class="search">
				<form></form>
								
					<input id="filename" type="text" name="filename" placeholder="请输入您要搜索的内容..." onkeyup="this.value=this.value.replace(/\s+/g,'')"/>
					<button id="findfile" ></button>
					

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
						<td><a href="javascript:getpath('${o.path}',${o.id});">${o.name}</a></td>
						<td>${o.type}</td>
						<td>${o.size}</td>
						<td><a style="color: #808080;text-decoration: none;" href="javascript:deletePath(${o.id});">删除</a> 
							<a style="cursor: pointer;"  onclick="javascript:tanchu(${o.id})" >重命名</a>
							</td>
							

					</tr>

				</c:forEach>
				<c:forEach var="o" varStatus="vs" items="${otherspaths}">
					<tr>
						<td>${o.name}</td>
						<td>${o.type}</td>
						<td>${o.size}</td>
						<td><a style="color: #808080;text-decoration: none;" href="javascript:deletePath(${o.id});">删除</a> 
							<a style="color: #808080;text-decoration: none;"
							href="donlowd?location=${o.location}">下载</a> <a style="color: #808080;text-decoration: none;"
							href="javascript:share(${o.id});">分享</a>
							<a style="cursor: pointer;" id="tanchu" onclick="javascript:tanchu(${o.id})" >重命名</a>
							</td>


					</tr>

				</c:forEach>
			</table>
			<div id="main" style="width: 100%;height: 100%;background-color: rgba(0,0,0,0.8);display: none;z-index: 1;position: relative;top:-17%;">
			
			 <div style="text-align: center;position: relative;top:20%;left:28%;width: 500px;height: 200px;background-color: white;"><br/><br/><br/><br/>
    
		<input id="hidden" type="text" hidden />
		<input id="rename" style="height: 30px;width: 300px;border: 2px solid;color: lightgrey;border-radius: 4px" name="rename" type="text" value="" placeholder="请输入您要更改的名字" required="required" onkeyup="this.value=this.value.replace(/\s+/g,'')"/>
        <input id="close" type="button" style="height: 30px;width: 70px;"value="重命名"></input>
        


    </div></div>
			
		</div>


	</div>


	<script>
		$(document).ready(function() {
			
			$("#a1").click(function() {
				$.ajax({
					url : "myfile",
					success : function(msg) {
						$("#show").html(msg);
					},
					error : function() {

					}
				});

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
		$("#findfile").click(function() {

			$.ajax({
				url : "findfile",
				type: 'POST',
				data: {"filename": $("#filename").val()},
				success : function(msg) {
					if($("#filename").val()==""){
						alert("请输入要查找的文件名");
					}else{
					$("#show").html(msg);
					}
				},
				error : function() {

				}
			});
		});
		$("#createdir").click(function() {

			$.ajax({
				url : "newdir",
				data: {"dirname": $("#dirname").val()},
				success : function(msg) {
					if($("#dirname").val()==""){
						alert("请输入文件夹名称");
					}else{
					$("#show").html(msg);
					}
				},
				error : function() {

				}
			});
		});
		
			$("#uplodebt").click(function() {
				
				if($("#file").val()==""){
					alert("请选择文件");
					
				}else{
				$.ajax({
					url : "file",
					data: {"file": $("#file").val()},
					success : function(msg) {
						$("#show").html(msg);
					},
					error : function() {
						alert("该文件在文件夹中已存在");

					}
				});
				}
			});
			
			$("#pageup").click(function() {
				
				$.ajax({
					url : "pageup",
					success : function(msg) {
						$("#show").html(msg);
					},
					error : function() {
					}
				});
			});

		

			

			function getpath (path,id) {

				$.ajax({
					url : "getpath",
					data: {"path": path , "id": id},
					success : function(msg) {
						$("#show").html(msg);
					},
					error : function() {

					}
				});
			}
			
			function share (id) {

				$.ajax({
					url : "share",
					data: {"id": id},
					success : function(msg) {
						if(msg=="1"){
							alert("分享成功");
						}else if(msg=="2"){
							alert("已分享该文件");
						}
					},
					error : function() {

					}
				});
			}
			
			function deletePath (id) {

				$.ajax({
					url : "deletePath",
					data: {"id": id},
					success : function(msg) {
						$("#show").html(msg);
					},
					error : function() {

					}
				});
			}
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