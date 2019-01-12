<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

    </head>
    <body>
      <div style="text-align: center;position: relative;top: 6%">
    
    <form action="rename" >
		<input  style="height: 30px;width: 300px;border: 2px solid;color: lightgrey;border-radius: 4px" name="rename" type="text" value="" placeholder="请输入您要更改的名字" required="required" onkeyup="this.value=this.value.replace(/\s+/g,'')"/>
        <input type="submit"  style="height: 30px;width: 70px;"value="重命名"></input>
    
        

</form>
    </div>
    </body>
</html>