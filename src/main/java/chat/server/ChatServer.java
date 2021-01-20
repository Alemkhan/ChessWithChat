package chat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class ChatServer {
    public static LinkedList<ClientHandler> clients = new LinkedList<>();
    private static final int PORT = 4000;
    public static void main(String[] args) {
        try(ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server is based on port" + serverSocket.getLocalPort());
            while(true){
                Socket socket = serverSocket.accept();
                System.out.println("Client on address " + socket.getLocalAddress() + " is accepted by server");
                clients.add(new ClientHandler(socket));
            }
        }catch(IOException e){
            System.out.println("Error while establishing server");
            e.printStackTrace();
        }
    }
}
