package chess.server;

import chess.model.Board;

import java.io.*;
import java.net.Socket;

public class ChessPlayerHandler extends Thread{

    private String color;
    private Socket clientSocket;
    private BufferedReader reader;
    private BufferedWriter writer;


    public ChessPlayerHandler(Socket socket, String color) throws IOException{
        System.out.println("HELLO");
        this.clientSocket = socket;
        this.color = color;
        reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        start();
    }

    @Override
    public void run(){
        String message;
        while(true){
            try{
                message = reader.readLine();
                String json = Board.newBoardJson(message, color);
                System.out.println("HHELLO" + json);
                for (ChessPlayerHandler client: ChessServer.players) {
                    client.send(json);
                }

            }catch(IOException e){
                System.out.println("Client Player Thread got an exception with sending message");
                close();
            }
        }
    }

    private void send(String message){
        try {
            writer.write(message);
            writer.newLine();
            writer.flush();
        }catch(IOException e){
            e.printStackTrace();
            close();
        }
    }

    public void close(){
        if(!clientSocket.isClosed()){
            try {
                reader.close();
                writer.close();
                clientSocket.close();
            }catch(IOException e){
                System.out.println("Close connection in Client Handler");
                e.printStackTrace();
            }
        }
    }

}
