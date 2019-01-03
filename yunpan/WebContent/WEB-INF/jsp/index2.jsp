<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

    </head>
    <body>
		        <c:forEach var="o" varStatus="vs" items="${filesName}">
 				<td>${o}<br/></td>
                </c:forEach>
                
                
                
                
    </body>
</html>