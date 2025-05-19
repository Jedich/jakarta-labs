package org.spatki.jakarta.labs.web;

import org.spatki.jakarta.labs.model.*;
import org.spatki.jakarta.labs.services.*;
import java.io.IOException;
import java.util.Collection;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet(name = "FrontControllerServlet", urlPatterns = {"/do/*"})
public class FrontControllerServlet extends HttpServlet {

    TopicService topicService;
    UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        topicService = (TopicService) config.getServletContext().getAttribute("topicService");
        userService = (UserService) config.getServletContext().getAttribute("userService");
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        
        String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            pathInfo = "/";
        }
        try {
            switch (pathInfo) {
                case "/login":
                    login(request, response);
                    break;
                case "/logout":
                    logout(request, response);
                    break;
                case "/topic":
                    topic(request, response);
                    break;
                case "/comment":
                    comment(request, response);
                    break;
                case "/createTopic":
                    createTopic(request, response);
                    break;
                case "/editTopic":
                    editTopic(request, response);
                    break;
                case "/edited":
                    edited(request, response);
                    break;
                case "/deleteTopic":
                    deleteTopic(request, response);
                    break;
                case "/":
                case "/search":
                default:
                    topics(request, response);
                    break;
            }
        } catch (RuntimeException ex) {
            error(request, response, "Oops, " + ex.getMessage());
        }

    }

    protected void topics(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchText = request.getParameter("text");

        

        Collection<Topic> topics = topicService.search(searchText);
        request.setCharacterEncoding("UTF-8");
        request.setAttribute("topics", topics);
        request.setAttribute("text", searchText);
        request.getRequestDispatcher("/WEB-INF/jsp/topics.jsp").forward(request, response);
    }

    protected void topic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int topicId = Integer.parseInt(request.getParameter("topicId"));
        Topic topic = topicService.getTopicById(topicId);
        request.setAttribute("topic", topic);
        request.getRequestDispatcher("/WEB-INF/jsp/topic.jsp").forward(request, response);
    }

    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();

        String login = request.getParameter("login");
        User user = userService.getByLogin(login);
        if (user == null) {
            error(request, response, "Sorry, user with login '" + login + "' not exists");
            return;
        }
        String password = request.getParameter("password");

        if (!userService.checkPassword(user, password)) {
            error(request, response, "Sorry, wrong password");
            return;
        }

        request.getSession().setAttribute("user", user);
        response.sendRedirect(".");
    }

    protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("topics", topicService.getAllTopics());
        request.getSession().invalidate();
        response.sendRedirect(".");
    }

   

    protected void comment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        response.sendRedirect("./topic?topicId=" + topicId);
    }
    
    protected void createTopic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            error(request, response, "Sorry, you need to log in");
            return;
        }else if (!user.isAdmin()) {
            error(request, response, "Sorry, you don't have permission");
            return;
        }
        String text = request.getParameter("text");
        Topic topic = new Topic(-1, text);
        topicService.addTopic(topic, user);
        response.sendRedirect("/ForumWebsite");
    }
    
    protected void editTopic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            error(request, response, "Sorry, you need to log in");
            return;
        }else if (!user.isAdmin()) {
            error(request, response, "Sorry, you don't have permission");
            return;
        }
        

        int topicId = Integer.parseInt(request.getParameter("topicId"));
        Topic topic = topicService.getTopicById(topicId);
        request.setAttribute("topic", topic);
        request.getRequestDispatcher("/WEB-INF/jsp/topicEditor.jsp").forward(request, response);
        
    }
    
    protected void edited(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            error(request, response, "Sorry, you need to log in");
            return;
        }else if (!user.isAdmin()) {
            error(request, response, "Sorry, you don't have permission");
            return;
        }
        

        int topicId = Integer.parseInt(request.getParameter("topicId"));
        Topic topic = topicService.getTopicById(topicId);
        String text = request.getParameter("text");
        topic.setTitle(text);
        topicService.editTopic(topic, user);
        response.sendRedirect("/ForumWebsite");
        
    }
    
    protected void deleteTopic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            error(request, response, "Sorry, you need to log in");
            return;
        }else if (!user.isAdmin()) {
            error(request, response, "Sorry, you don't have permission");
            return;
        }

        int topicId = Integer.parseInt(request.getParameter("topicId"));
        Topic topic = topicService.getTopicById(topicId);
        topicService.removeTopic(topic, user);
        response.sendRedirect("/ForumWebsite");
    }

    protected void error(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
        request.setAttribute("message", message);
        request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
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
