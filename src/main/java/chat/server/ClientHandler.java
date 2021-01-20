package chat.server;

import java.io.*;
import java.net.Socket;

public class ClientHandler extends Thread{
    private Socket clientSocket;
    private BufferedReader reader;
    private BufferedWriter writer;

    public ClientHandler(Socket socket1) throws IOException {
        this.clientSocket = socket1;
        reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        start();
    }

    public void run(){
        String message;
        while(true){
            try{
                message = reader.readLine();
                System.out.println(message);
                for (ClientHandler object: Server.clients) {
                    object.send(message);
                }

            }catch(IOException e){
                System.out.println("thread closed clienthandler");
                close();
            }
        }
    }

    public void send(String message){
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
                e.printStackTrace();
            }
        }
    }


}
