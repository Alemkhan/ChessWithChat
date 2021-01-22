package chess.servlets;

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
    private static boolean isClientNew = true;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(isClientNew){
            new ChessClient();
            isClientNew = false;
        }

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));

            String json = null;
            if (br != null) {
                json = br.readLine();
            }

            Gson gson = new Gson();
            HashMap<String, String> boardState = gson.fromJson(json, HashMap.class);

            HttpSession session = request.getSession();
            String username = (String) session.getAttribute("username");

            Board messageJSON = new Board(username, boardState);
            String jsonObject = gson.toJson(messageJSON);

            ChessClient.servletMessage = jsonObject;

            System.out.println("serverMessage: " + ChessClient.serverMessage);
            System.out.println("servletMessage: " + ChessClient.servletMessage);

        }catch(NullPointerException e){
            System.out.println("Client Created");
        }

    }
}
