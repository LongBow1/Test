package com.owner.me.mytest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Stream;

public class SocketTest {
    public static void main(String[] args) {
        try {
            //System.out.println(0XFFFF);
            //bioDemo();
            nioDemo();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void bioDemo() throws IOException {
        ServerSocket serverSocket = new ServerSocket(8888);
        System.out.println("BIO Server Starting, listening on port:" +serverSocket.getLocalSocketAddress());

        while (true){
            //waiting client connect blocked
            Socket socket = serverSocket.accept();
            try {
                System.out.println("Connection from "+ socket.getRemoteSocketAddress());
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                while (true){
                    //blocked reading
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

    static void nioDemo() throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        serverSocketChannel.configureBlocking(false);

        serverSocketChannel.bind(new InetSocketAddress(11111));
        System.out.println("NIO server starting, listening on port : " + serverSocketChannel.getLocalAddress());

        Selector selector = Selector.open();

        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        ByteBuffer byteBuffer = ByteBuffer.allocate(2048);
        while (true){
            int select = selector.select();
            if(select == 0){
                continue;
            }
            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()){
                SelectionKey key = iterator.next();
                if(key.isAcceptable()){
                    ServerSocketChannel channel = (ServerSocketChannel) key.channel();

                    SocketChannel socketChannel = channel.accept();
                    System.out.println("Connection from "+socketChannel.getRemoteAddress());

                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);

                }

                if(key.isReadable()){
                    SocketChannel channel = (SocketChannel)key.channel();

                    channel.read(byteBuffer);
                    String request = new String(byteBuffer.array()).trim();
                    byteBuffer.compact();
                    System.out.println("Receive request :" + channel.getRemoteAddress() + ", msg :" + request);
                    String response = "Response :"+request+".\n";
                    channel.write(ByteBuffer.wrap(response.getBytes()));
                }
                iterator.remove();
            }
        }

    }
}
