package chat.model;

public class MessageJSON {
    private String username;
    private String message;

    public MessageJSON(){}

    public MessageJSON(String username, String message){
        this.message = message;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
