package com.owner.me.mytest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketTest {
    public static void main(String[] args) {
        try {
            bioDemo();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void bioDemo() throws IOException {
        ServerSocket serverSocket = new ServerSocket(8888);
        System.out.println("BIO Server Starting, listening on port:" +serverSocket.getLocalSocketAddress());

        while (true){
            Socket socket = serverSocket.accept();
            try {
                System.out.println("Connection from "+ socket.getRemoteSocketAddress());
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                while (true){
                    String request = bufferedReader.readLine();
                    System.out.println("receive request: "+socket.getRemoteSocketAddress()+ ", msg:" + request.getBytes());
                    String response = " response : "+request+". \n";
                    socket.getOutputStream().write(response.getBytes());
                }
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }

    }
}
