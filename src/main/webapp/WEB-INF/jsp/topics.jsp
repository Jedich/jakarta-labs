<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%@page contentType="text/html" pageEncoding="UTF-8"%><!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Forum</title>
        <%@include file="dependencies.jspf"%>
    </head>
    <body>
        <%@include file="header.jspf"%>
        
        <section>
            <c:if test="${!empty user}">
                <c:if test="${user.isAdmin()}">
                <form style="width: 100%;" action="createTopic" method="POST">
                    
                    <input type="hidden" name="user" value="${user}" />
                    <textarea class="boxsizingBorder" name="text" placeholder="Topic name"></textarea>
                    <input style="margin-left: 7px; margin-bottom: 7px;" class="btn btn-primary" type="submit" value="Create new topic" />
                </form> 
                </c:if>
            </c:if>
            
            <table class="topics-table">
                <thead>
                    <tr>
                        <th>Topic</th>
                        <th>Comments</th>
            <c:if test="${!empty user}">
                <c:if test="${user.isAdmin()}">
                <th>***       Actions       ***</th>
                </c:if>
            </c:if>                        
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="topic" items="${topics}">                        
                        <tr>
                            <td title="${topic.topicId}">
                                <a href="topic?topicId=${topic.topicId}">${topic.title}</a>
                            </td>
                            <td>${topic.numberOfComments}</td>
                            <c:if test="${!empty user}">
                                <c:if test="${user.isAdmin()}">
                                <td>
                                <form style="width: 100%;" action="editTopic" method="POST">
                    
                                    <input type="hidden" name="topicId" value="${topic.topicId}" />
                                    <input type="submit" value="EDIT" />
                                </form>
                                <form style="width: 100%;" action="deleteTopic" method="POST">
                    
                                    <input type="hidden" name="topicId" value="${topic.topicId}" />
                                    <input type="submit" value="DELETE" />
                                </form>                    
                                </td>
                                </c:if>
                            </c:if>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </section>
        <br>
        <%@include file="footer.jspf"%>
    </body>
</html>
