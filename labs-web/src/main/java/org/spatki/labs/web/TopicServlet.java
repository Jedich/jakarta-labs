package org.spatki.labs.web;

import jakarta.ejb.EJB;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.spatki.labs.model.*;
import org.spatki.labs.services.*;
import java.io.IOException;
import java.util.Collection;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.persistence.spi.PersistenceProvider;

@WebServlet(name = "TopicServlet", urlPatterns = {"/topics/*"})
public class TopicServlet extends BaseServlet {

    @EJB
    private TopicServiceLocal topicService;

    @EJB
    private UserServiceLocal userService;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("Persistence Provider: " + PersistenceProvider.class.getPackage().getImplementationVersion());
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ejbPU");
        System.out.println("EntityManagerFactory created: " + emf);

        request.setCharacterEncoding("UTF-8");

        String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            pathInfo = "/";
        }
        try {
            switch (pathInfo) {
                case "/topic":
                    topic(request, response);
                    break;
                case "/new":
                    createTopic(request, response);
                    break;
                case "/update":
                    editTopic(request, response);
                    break;
                case "/edited":
                    edited(request, response);
                    break;
                case "/delete":
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

    protected void createTopic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            error(request, response, "Sorry, you need to log in");
            return;
        } else if (!user.isAdmin()) {
            error(request, response, "Sorry, you don't have permission");
            return;
        }
        String text = request.getParameter("text");
        Topic topic = new Topic(-1, text);
        topicService.addTopic(topic, user);
        response.sendRedirect("/topics");
    }

    protected void editTopic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            error(request, response, "Sorry, you need to log in");
            return;
        } else if (!user.isAdmin()) {
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
        } else if (!user.isAdmin()) {
            error(request, response, "Sorry, you don't have permission");
            return;
        }

        int topicId = Integer.parseInt(request.getParameter("topicId"));
        Topic topic = topicService.getTopicById(topicId);
        String text = request.getParameter("text");
        topic.setTitle(text);
        topicService.editTopic(topic, user);
        response.sendRedirect("/topics");

    }

    protected void deleteTopic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            error(request, response, "Sorry, you need to log in");
            return;
        } else if (!user.isAdmin()) {
            error(request, response, "Sorry, you don't have permission");
            return;
        }

        int topicId = Integer.parseInt(request.getParameter("topicId"));
        Topic topic = topicService.getTopicById(topicId);
        topicService.removeTopic(topic, user);
        response.sendRedirect("/topics");
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
