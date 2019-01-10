<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link rel="stylesheet" href="resources/css/zhuce.css" />
        
        <title>JSP Page</title>
<style>

</style>
    </head>
    <body>
    <div class="head">
    <div style="float:left;height: 70px;width: 70px;position: relative;top: 25%;left: 18%;"><img src="resources/imgs/caijian.jpg" style="height: 70px;width: 70px;"/></div>
    <div style="float:left;width: 1px;height: 40px; background: silver;position: relative;left: 20%;top: 35%;"></div> 
    <div style="float: left;position: relative;left: 22%;top: 30%;"><p style="font-family: sans-serif;font-weight: bolder;font-size: 23px;color:#808080;">注册青大云盘账号</p></div>
    <div style="float: left;position: relative;left:50%;top:50%;"><a style="font-family: sans-serif;font-weight: bolder;color:#808080;">我已经注册，现在就&nbsp;<input style="height: 30px;width: 80px;" type="submit" value="登录" /></a></div>
    </div>
    <div class="nav">
    <hr   width="80%" color="#808080" size=1/>
   
    </div>
    <div class="main">
    <br/><br/>
		           <form action="register" >
		<a style="font-family: sans-serif;font-weight: bolder;font-size: 18px;color:#808080;">用户名</a><input style="height: 35px;width: 270px;border: 1px solid silver;border-radius:5px;
    font-size: 14px;position: relative;left: 3%"  name="name" type="text" value="" placeholder="请设置用户名"/><br/><br/>
		<a style="font-family: sans-serif;font-weight: bolder;font-size: 18px;color:#808080;">&nbsp;&nbsp;&nbsp;账号</a><input style="height: 35px;width: 270px;border: 1px solid silver;border-radius:5px;
    font-size: 14px;position: relative;left: 3%"  name="account" type="text" value="" placeholder="请设置账号" /><br/><br/>
		<a style="font-family: sans-serif;font-weight: bolder;font-size: 18px;color:#808080;">&nbsp;&nbsp;&nbsp;密码</a><input style="height: 35px;width: 270px;border: 1px solid silver;border-radius:5px;
    font-size: 14px;position: relative;left: 3%"  name="password" type="text" value="" placeholder="请设置密码"/><br/><br/><br/>
        <input class="denglu1 denglu" type="submit" value="注册"></input>

</form>
       </div>         
                
                
                
    </body>
</html>