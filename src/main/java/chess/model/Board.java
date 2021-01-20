package chess.model;

import java.util.HashMap;

public class Board {

    private String username;
    private HashMap<String, String> positionAndfigure;

    public Board(String username, HashMap<String, String> positionAndfigure) {
        this.username = username;
        this.positionAndfigure = positionAndfigure;
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
