<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

    </head>
    <body>
    <form action="login" >
		<input name="account" type="text" value="" /><br/>
		<input name="password" type="text" value="" />
        <input type="submit" value="登录"></input>

</form>
<a href="index2"  >注册</a>
    </body>
</html>