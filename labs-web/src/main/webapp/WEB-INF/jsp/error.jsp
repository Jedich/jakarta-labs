<%-- 
    Document   : error
    Created on : May 10, 2020, 7:30:26 PM
    Author     : Aaz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Forum</title>
        <%@include file="dependencies.jspf"%>
    </head>
    <body class="content">
        <%@include file="header.jspf"%>
        <section>
            <h1><c:out value="${message}"/></h1>
            <a href=".">Go to main page</a>
        </section>  
        <br>
        <%@include file="footer.jspf"%>
    </body>
</html>
