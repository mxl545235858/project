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
    <div style="filter:alpha(Opacity=80);-moz-opacity:0.8;opacity: 0.8;border-radius:5px;box-shadow:2px 5px 5px silver; z-index:30;position:absolute;top:20%;left:70%; height: 400px;width: 300px;background-color:white;">
  <br/>
    <p  style="font-weight: bold;font-size: 23px;color: black;font-family: sans-serif;position: relative;left: 5%">账号密码登录</p>
    <form action="login" >
    </form>
		<input id="account" style="height: 35px;width: 270px;border: 1px solid silver;border-radius:5px;
    font-size: 14px;position: relative;left: 3%" name="account" type="text" value="" placeholder="请输入您的账号" required="required" onkeyup="this.value=this.value.replace(/\s+/g,'')"/><br/>
		<input id="password" style="height: 35px;width: 270px;border-radius:5px;border: 1px solid silver;
    font-size: 14px;position: relative;left: 3%" name="password" type="password" value="" placeholder="密码" required="required" onkeyup="this.value=this.value.replace(/\s+/g,'')" /><br/>
    <br/><br/>
        <input id="login" class="denglu1 denglu" type="submit" value="登录"></input>
<br/><br/>

<a href="index2" style="font-size: 20px;position: relative;left: 65%;text-decoration: none;" >立即注册</a>
</div>
     
                <div class="box" style="z-index: 19">
	<ol></ol>
	
	<ul>
	
    	<li class="active1" style="left:0;z-index:11;">
            <a ><img style="height: 650px;width:1700px;" src="resources/imgs/1.jpg" /></a></li>
        <li><a><img style="height: 650px;width:1700px;" src="resources/imgs/2.jpg" /></a></li>
        <li><a ><img  style="height:650px;width:1700px;" src="resources/imgs/3.jpg" /></a></li>
        <li><a ><img style="height: 650px;width:1700px;" src="resources/imgs/4.jpg" /></a></li>
    </ul>
</div>
   <script>


		$("#login").click(function() {

			$.ajax({
				url : "login",
				type: 'POST',
				data: {"account": $("#account").val(),"password": $("#password").val()},
				success : function(msg) {
					if(msg=="1"){
					window.location.href = "index3";
					}else{
						alert("账号密码不正确");
					}
				},
				error : function() {

				}
			});
		});


	</script>
    </body>
</html>