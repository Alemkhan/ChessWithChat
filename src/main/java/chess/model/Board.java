package chess.model;

import com.google.gson.Gson;

import java.util.HashMap;

public class Board {

    private String username;
    private HashMap<String, String> positionAndfigure;
    private String color;

    public Board(String username, HashMap<String, String> positionAndfigure) {
        this.username = username;
        this.positionAndfigure = positionAndfigure;
    }

    public static String newBoardJson(String json, String color){
        Gson gson = new Gson();
        Board board = gson.fromJson(json, Board.class);
        board.setColor(color);
        return gson.toJson(board);
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public HashMap<String, String> getPositionAndfigure() {
        return positionAndfigure;
    }

    public void setPositionAndfigure(HashMap<String, String> positionAndfigure) {
        this.positionAndfigure = positionAndfigure;
    }
}
