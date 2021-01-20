package chess.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class ChessServer {

    private static final int PORT = 4000;
    public static LinkedList<ChessPlayerHandler> clients = new LinkedList<>();

    public static void main(String[] args) {
        try(ServerSocket serverSocket = new ServerSocket(PORT)) {
            while(true){
                Socket socket = serverSocket.accept();
                clients.add(new ChessPlayerHandler(socket));
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
