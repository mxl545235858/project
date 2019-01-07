<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    <!--  <base href="${pageContext.request.contextPath}/"> -->
           
    
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="resources/css/shouye.css" />
        
        <title>JSP Page</title>

    </head>
    <body>
    <div class="header">
        <div style="position: relative;top:25%;left: 50px;font-size: 25px; "><a style="color: white;">青大云</a></div>
        <div style="position: relative;left: 50px"><img src="resources/imgs/yunpan.jpg" style="position:relative;top: 5px;" width="80px" height="50px"/></div>
        <div style="position: relative;top:20px;left: 200px;font-size: 20px; "><a style="color: white;">主页</a></div>
                <div style="position: relative;top:20px;left: 250px;font-size: 20px; "><a style="color: white;">网盘</a></div>
                        <div style="position: relative;top:20px;left: 300px;font-size: 20px; "><a style="color: white;">分享</a></div>
                                <div style="position: relative;top:20px;left: 350px;font-size: 20px; "><a style="color: white;">空间</a></div>


        <div style="position: relative;left:70%;top: 30px;text-align: center"><a href="/yunpan/" style="color: white;">登录</a></div>
    </div>
	
	<div class="left" style="border: 1px darkgray solid;">
    
    <div  style="width: 200px;height: 50px;text-align: center"><img src="resources/imgs/quanbuwenjian.png" style="position:relative;top: 10px" width="30px" height="30px"/>
        <a id="a1" style="font-weight: bold;">全部文件</a>
    </div>
    <div style="width: 200px;height: 50px;text-align: center"><img src="tupian.png"style="position:relative;top: 10px" width="30px" height="30px"/>
    <a id="a2">我的好友</a>
    </div>
    <div style="width: 200px;height: 50px;text-align: center"><img src="shipin.png"style="position:relative;top: 10px"width="30px" height="30px" />
        <a id="a3">好友请求</a>
    </div>
    <div style="width: 200px;height: 50px;text-align: center"><img src="wendang.png"style="position:relative;top: 10px" width="30px" height="30px"/>
        <a id="a4">我的分享</a></div>
    <div style="width: 200px;height: 50px;text-align: center"><img src="yinyue.png" style="position:relative;top: 10px"width="30px" height="30px"/>
        <a id="a5">好友分享</a>
    </div>
      <div style="width: 200px;height: 50px;text-align: center"><img src="hushouzhan.png"style="position:relative;top: 10px" width="30px" height="30px"/>
        <a id="a6" style="font-weight: bold;">回收站&nbsp;&nbsp;&nbsp;&nbsp;</a>
        
    </div>
    <hr/>
    <a>空间使用情况：</a><br/>
    <a>空间大小：</a><a style="color: red">50M</a><br/>
    <a>释放空间：</a>
    

    
</div>

<div  class="right">
    <div class="navigation" style="">
    
        <div style="position: relative;left: 20px;top: 10px;"> <form style="display: inline; id="form"  action="file" encType="multipart/form-data">
<input style="font-size: 18px;" type="submit" value="上传"></input>
	<input style="font-size: 18px;" type="file" name="file"/></form></div>
        <div style="position: relative;left:30px;;top:10px;">
        <form style="display: inline;" id="form"  action="newdir" >
	<input style="font-size: 18px;" type="text" name="dirname"/>
	<input style="font-size: 18px;" type="submit" value="新建文件夹"></input>
	</form>
	<form  style="display: inline; action="pageup"><input style="font-size: 18px;" type="submit" value="上一级"></form>
        </div>
        
        <div style="position: absolute;top: 7%;left: 60%;" class="search">
                  <form id="form"  action="findfile">
            <input type="text" name="filename" placeholder="请输入您要搜索的内容...">
            <button type="submit"></button>
                  </form>
                </div>
    </div>
    <div class="top">
        <div style="position: relative;left: 3%;top:10px"><a style="color: dimgray">全部文件</a></div>
        <div style="position: relative;left: 80%;top:10px;"><a style="color: dimgray">全部加载，共10个</a></div>
    </div>
        <div id="show" class="view">
        <table border="8">
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
		<a >分享</a>  
	</td>

	</tr>
	
</c:forEach>
</table></div>


</div>
	<!-- <a href="myfile">我的文件</a>&nbsp&nbsp 
	<a href="myfriend">我的好友</a>&nbsp&nbsp
	<a href="friendrequest">好友请求</a>&nbsp&nbsp-->
    <!--  <form action="findfriend" >
		<input name="name" type="text" value="" />
        <input type="submit" value="查找好友"></input>

</form>
    <br/>
    -->
    
   <!--   <form id="form"  action="file" encType="multipart/form-data">
<input type="submit" value="上传"></input>
	<input  type="file" name="file"/></form>
	<br/>-->
	<!--  <form id="form"  action="newdir" >
	<input  type="text" name="dirname"/>
	<input type="submit" value="新建文件夹"></input>
	</form>-->
	<!-- <br/>
	<form id="form"  action="findfile" >
	<input  type="text" name="filename"/>
	<input type="submit" value="查找文件"></input>
	</form> -->
	<!--  <br/>
	
	<form action="pageup"><input type="submit" value="上一级"></form>
	
	<br/>-->
	
	
	
	
	
	
	
    </form>
    <script>


           $(document).ready(function(){
  $("#a1").click(function(){
    $('#show').load('myfile.jsp');
  });
});
$(document).ready(function(){
  $("#a2").click(function(){
    $('#show').load('http://localhost:8080/yunpan/myfriend');
  });
});
$(document).ready(function(){
  $("#a3").click(function(){
    $('#show').load('shipin.html');
  });
});
$(document).ready(function(){
  $("#a4").click(function(){
    $('#show').load('wendang.html');
  });
});
$(document).ready(function(){
  $("#a5").click(function(){
    $('#show').load('yinyue.html');
  });
});
$(document).ready(function(){
  $("#a6").click(function(){
    $('#show').load('huishouzhan.html');
  });
});

          

</script>
    

    </body>
</html>