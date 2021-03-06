package chat.server;

import java.io.*;
import java.net.Socket;

public class ClientHandler extends Thread{
    private Socket clientSocket;
    private BufferedReader reader;
    private BufferedWriter writer;

    public ClientHandler(Socket socket) throws IOException {
        this.clientSocket = socket;
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
                System.out.println(message);
                for (ClientHandler client: ChatServer.clients) {
                    client.send(message);
                }

            }catch(IOException e){
                System.out.println("Client Handler Thread got an exception with sending message");
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
                System.out.println("Error while connection close in Client Handler");
                e.printStackTrace();
            }
        }
    }


}
