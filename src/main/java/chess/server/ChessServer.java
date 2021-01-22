package chess.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class ChessServer {

    private static final int PORT = 5000;
    public static LinkedList<ChessPlayerHandler> players = new LinkedList<>();

    public static void main(String[] args) {
        try(ServerSocket serverSocket = new ServerSocket(PORT)) {
            while(true){

                Socket socket = serverSocket.accept();

                if(players.size()==0) {
                    players.add(new ChessPlayerHandler(socket, "w"));
                }else{
                    players.add(new ChessPlayerHandler(socket, "b"));
                }

            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
