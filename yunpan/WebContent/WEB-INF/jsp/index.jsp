<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

    </head>
    <body>
    <form action="get" >

    <input type="submit" value="获取"></input>

    </form >
    <br/>
    <form id="form"  action="file" encType="multipart/form-data">
<input type="submit" value="获取"></input>
	<input  type="file" name="file"/>
    </form>
    

    </body>
</html>