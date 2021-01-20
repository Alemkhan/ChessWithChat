package chat.client;

import java.io.*;
import java.net.Socket;

public class ChatClient {
    public static volatile String servletMessage;
    public static volatile String serverMessage;
    private Socket clientSocket;
    private BufferedReader reader;
    private BufferedWriter writer;


    public ChatClient() {
        try {
            clientSocket = new Socket("localhost", 4000);
            reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            new InputHandler().start();
            new OutputHandler().start();
        } catch (IOException e) {
            System.out.println("HELLO CLIENT WOLRD");
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



    class InputHandler extends Thread{
        @Override
        public void run(){
            while(true) {
                try{
                    serverMessage = reader.readLine();
                } catch (IOException e) {
                    System.out.println("closed inputhandler");
                    ChatClient.this.close();
                }
            }
        }
    }

    class OutputHandler extends Thread{
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
                        System.out.println("OUTPUThandler closed");
                        ChatClient.this.close();
                    }

            }

        }
    }


}
