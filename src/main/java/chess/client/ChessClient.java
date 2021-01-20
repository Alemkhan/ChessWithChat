package chess.client;

import chat.client.ChatClient;

import java.io.*;
import java.net.Socket;

public class ChessClient {

    public static volatile String servletMessage;
    public static volatile String serverMessage;
    private static final int PORT = 4000;
    private Socket clientSocket;
    private BufferedReader reader;
    private BufferedWriter writer;


    public ChessClient() {
        try {
            clientSocket = new Socket("localhost", PORT);
            reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            new ChessClient.InputHandler().start();
            new ChessClient.OutputHandler().start();
        } catch (IOException e) {
            System.out.println("The server is unreachable on port " + PORT);
            e.printStackTrace();
        }
    }

    public void close(){
        if(!clientSocket.isClosed()){
            try {
                reader.close();
                writer.close();
                clientSocket.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }



    private class InputHandler extends Thread{
        @Override
        public void run(){
            while(true) {
                try{
                    serverMessage = reader.readLine();
                } catch (IOException e) {
                    System.out.println("Input Handler has been interrupted");
                    ChessClient.this.close();
                }
            }
        }
    }

    private class OutputHandler extends Thread{
        @Override
        public void run(){
            while(true){
                try {
                    if(servletMessage !=null) {
                        writer.write(servletMessage);
                        writer.newLine();
                        writer.flush();
                        servletMessage = null;
                    }

                } catch (IOException e) {
                    System.out.println("Output Handler has been interrupted");
                    ChessClient.this.close();
                }

            }

        }
    }

}
