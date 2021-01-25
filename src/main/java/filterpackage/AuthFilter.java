package filterpackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.*;

public class AuthFilter implements Filter{

    public void init(FilterConfig arg0) throws ServletException {}

    public void doFilter(ServletRequest req, ServletResponse resp,
                         FilterChain chain) throws IOException, ServletException {

        PrintWriter out=resp.getWriter();
        String string = "username  error!";

        String username=req.getParameter("username");
        Pattern pattern = Pattern.compile("@astanait.edu.kz");
        Matcher m=pattern.matcher(username);
        if(m.find()){
            chain.doFilter(req, resp);//sends request to next resource
        }
        else{
           req.setAttribute("string", "username error!");
            RequestDispatcher rd=req.getRequestDispatcher("login.jsp");
            rd.include(req, resp);
        }

    }
    public void destroy() {}

}