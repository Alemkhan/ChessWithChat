package chat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class Server{
    public static LinkedList<ClientHandler> clients = new LinkedList<>();

    public static void main(String[] args) {
        try(ServerSocket serverSocket = new ServerSocket(4000)) {
            while(true){
                Socket socket = serverSocket.accept();
                clients.add(new ClientHandler(socket));
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
