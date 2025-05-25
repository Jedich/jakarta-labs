<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
            <div class="topic-title">
                <h1>Old topic name: "${topic.title}"</h1>
            </div>

            <c:if test="${!empty user}">
                <c:if test="${user.isAdmin()}">
            
                
                <br>
                <form style="width: 100%;" action="edited" method="POST">
                    <div class="username">EDITOR</div>
                    <input type="hidden" name="topicId" value="${topic.topicId}" />
                    <textarea class="boxsizingBorder" name="text" placeholder="Enter new topic name"></textarea>
                    <input style="margin-left: 7px" class="btn btn-primary" type="submit" value="EDIT" />
                </form> 
                </c:if> 
            </c:if>
            <hr>
            
        </section>  
        <%@include file="footer.jspf"%>
    </body>
</html>
