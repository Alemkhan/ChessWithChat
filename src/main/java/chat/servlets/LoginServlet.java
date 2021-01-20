package chat.servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        session.setAttribute("username",request.getParameter("username"));
        if (request.getParameter("selection").equals("Chat")){
            response.sendRedirect("chat.jsp");
        } else {
            response.sendRedirect("chess/index.html");
        }

    }
}
