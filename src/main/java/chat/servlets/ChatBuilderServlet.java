package chat.servlets;

import chat.client.ChatClient;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ChatBuilderServlet", value = "/ChatBuilderServlet")
public class ChatBuilderServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        HttpSession session = request.getSession();
        String username = (String)session.getAttribute("username");
        String json = ChatClient.serverMessage;
        if(json!=null&&!username.equals(getValueByKey(json, "username"))){
            response.getWriter().print(json);
            System.out.println(json);
            ChatClient.serverMessage = null;
        }else{
            response.getWriter().print("Error");
        }

    }


    public String getValueByKey(String json, String key){
        JsonObject jobj = new Gson().fromJson(json, JsonObject.class);
        return jobj.get(key).getAsString();
    }
}
