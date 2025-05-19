<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Forum</title>
        <%@include file="dependencies.jspf"%>
    </head>
    <body>
        <%@include file="header.jspf"%>
        <section>
            <div class="topic-title">
                <h1>${topic.title}</h1>
            </div>

            <c:if test="${!empty user}">
                
                
                <br>
                <form style="width: 100%;" action="comment" method="POST">
                    <div class="username">${user.name}</div>
                    <input type="hidden" name="topicId" value="${topic.topicId}" />
                    <textarea class="boxsizingBorder" name="text"></textarea>
                    <input style="margin-left: 7px" class="btn btn-primary" type="submit" value="Comment" />
                </form> 
            </c:if>
            <hr>
            <c:forEach var="comment" items="${topic.comments}">
                <div class="username">
                    <c:out value="${comment.user.name}"/> ---
                    <fmt:formatDate pattern="dd.MM.yyyy HH:mm:ss" value="${comment.date}" />
                </div>
                <div class="comment">
                    <c:out value="${comment.text}"/>
                </div>
                <br>
            </c:forEach>
        </section>  
        <%@include file="footer.jspf"%>
    </body>
</html>
