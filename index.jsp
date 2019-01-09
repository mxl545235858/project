<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>登录</title>
        <link rel="stylesheet" href="resources/css/denglu.css" />
         <!--<link rel="stylesheet" href="resources/css/bootstrap.min.css" />  -->
                <script src="resources/js/jquery-3.3.1.min.js"></script>
        
        <script src="resources/js/scrollAd.js"></script>
    </head>
    <body>
    <div >
    
    </div>
    <div style=" z-index:30;position:absolute;top:0;left:50%; height: 300px;width: 250px;background-color: red;">
  
    <a style="z-index:30;position:absolute;top:0;font-size: 50px;background:white;">青大云盘</a>
    <form action="login" >
		<input name="account" type="text" value="" /><br/>
		<input name="password" type="text" value="" />
        <input type="submit" value="登录"></input>

</form>
<a href="index2"  >注册</a>
</div>
     
                <div class="box" style="z-index: 19">
	<ol></ol>
	
	<ul>
	
    	<li class="active1" style="left:0;z-index:11;">
            <a href=""><img style="height: 650px;width:1700px;" src="resources/imgs/1.jpg" /></a></li>
        <li><a href=""><img style="height: 650px;width:1700px;" src="resources/imgs/2.jpg" /></a></li>
        <li><a href=""><img  style="height:650px;width:1700px;" src="resources/imgs/3.jpg" /></a></li>
        <li><a href=""><img style="height: 650px;width:1700px;" src="resources/imgs/4.jpg" /></a></li>
    </ul>
</div>

    </body>
</html>