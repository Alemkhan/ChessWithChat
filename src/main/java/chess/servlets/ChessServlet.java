package chess.servlets;

import chat.client.ChatClient;
import chat.model.MessageJSON;
import chess.client.ChessClient;
import chess.model.Board;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

@WebServlet(name = "ChessServlet", value = "/ChessServlet")
public class ChessServlet extends HttpServlet {
    private static boolean isNew = true;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(isNew){
            new ChessServlet();
            isNew = false;
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));

        String json = "";
        if (br!=null) {
            json = br.readLine();
        }

        Gson gson = new Gson();
        HashMap<String, String> boardState = gson.fromJson(json, HashMap.class);

        HttpSession session = request.getSession();
        String username = (String)session.getAttribute("username");
        Board messageJSON = new Board(username, boardState);
        String jsonObject  = gson.toJson(messageJSON);
        System.out.println(jsonObject);
        ChessClient.servletMessage = jsonObject;

        response.setContentType("application/json");
        String jsonResponse = ChatClient.serverMessage;

        if(jsonResponse!=null&&!username.equals(getValueByKey(jsonResponse, "username"))){
            response.getWriter().print(json);
            ChessClient.serverMessage = null;
        }else{
            response.getWriter().print("Error");
        }

    }

    public String getValueByKey(String json, String key){
        JsonObject jobj = new Gson().fromJson(json, JsonObject.class);
        return jobj.get(key).getAsString();
    }
}
