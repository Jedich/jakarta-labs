package org.spatki.jakarta.labs;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/member")
public class MemberServlet extends HttpServlet {

    private Map<String, Member> members;

    @Override
    public void init() throws ServletException {
        members = new HashMap<>();
        members.put("alex", new Member("Юдаков Олександр", "Фахівець з Java"));
        members.put("max", new Member("Вавринюк Максим", "Системний адміністратор"));
        members.put("sofiia", new Member("Лємєшова Софія", "UX/UI дизайнерка"));
        members.put("yehor", new Member("Тітов Єгор", "DevOps інженер"));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        String id = request.getParameter("id");

        out.println("<html><head><meta charset=\"UTF-8\"><title>Учасник</title></head><body>");

        if (id != null && members.containsKey(id)) {
            Member m = members.get(id);
            out.printf("<h2>%s</h2><p>%s</p>", m.name, m.description);
        } else {
            out.println("<h2>Учасника не знайдено</h2>");
            out.println("<p>Спробуйте: ?id=ivan, petro, olga або maria</p>");
        }

        out.println("<p><a href='index.html'>Назад на головну</a></p>");
        out.println("</body></html>");
    }

    private static class Member {
        String name;
        String description;

        Member(String name, String description) {
            this.name = name;
            this.description = description;
        }
    }
}
