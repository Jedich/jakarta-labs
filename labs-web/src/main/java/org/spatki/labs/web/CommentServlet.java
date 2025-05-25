package org.spatki.labs.web;

import jakarta.ejb.EJB;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.spatki.labs.model.*;
import org.spatki.labs.services.TopicServiceLocal;


@WebServlet(name = "CommentServlet", urlPatterns = {"/comment"})
public class CommentServlet extends BaseServlet {

    @EJB
    private TopicServiceLocal topicService;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            error(request, response, "Sorry, you need to log in");
            return;
        }

        int topicId = Integer.parseInt(request.getParameter("topicId"));
        Topic topic = topicService.getTopicById(topicId);

        if (topic == null) {
            error(request, response, "Sorry, topic does not exists");
            return;
        }

        String text = request.getParameter("text");

        topicService.addComment(topic, user, text);
        response.sendRedirect("/topics/topic?topicId=" + topicId);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
