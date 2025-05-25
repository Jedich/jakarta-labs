package org.spatki.labs.web;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public abstract class BaseServlet extends HttpServlet {

    protected void error(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
        request.setAttribute("message", message);
        request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
    }
}