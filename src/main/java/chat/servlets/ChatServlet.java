package chat.servlets;


import chat.client.ChatClient;
import chat.model.MessageJSON;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ChatServlet", value = "/ChatServlet")
public class ChatServlet extends HttpServlet {
    public static boolean firstTime = true;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(firstTime);
        if(firstTime){
            new ChatClient();
            firstTime = false;
        }

        HttpSession session = request.getSession();
        String username = (String)session.getAttribute("username");
        String message = request.getParameter("message");
        MessageJSON messageJSON = new MessageJSON(username, message);
        Gson gson = new Gson();
        String json  = gson.toJson(messageJSON);
        System.out.println(json);
        ChatClient.servletMessage = json;
        response.getWriter().print(message);
        System.out.println(ChatClient.serverMessage);
    }


    public String getValueByKey(String json, String key){
        JsonObject jobj = new Gson().fromJson(json, JsonObject.class);
        return jobj.get(key).getAsString();
    }
}
