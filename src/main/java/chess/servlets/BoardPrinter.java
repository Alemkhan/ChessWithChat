package chess.servlets;

import chess.client.ChessClient;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "BoardPrinter", value = "/BoardPrinter")
public class BoardPrinter extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");

        HttpSession session = request.getSession();
        String username = (String)session.getAttribute("username");
        String json = ChessClient.serverMessage;

        if(json!=null&&!username.equals(getValueByKey(json, "username"))){
            response.getWriter().print(json);
            ChessClient.serverMessage = null;
        }

    }

    public String getValueByKey(String json, String key){
        JsonObject jobj = new Gson().fromJson(json, JsonObject.class);
        return jobj.get(key).getAsString();
    }
}
