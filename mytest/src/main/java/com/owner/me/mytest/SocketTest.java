package com.owner.me.mytest;

import org.apache.http.impl.nio.bootstrap.ServerBootstrap;
import org.springframework.context.annotation.Scope;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
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


    public class PlainOioServer {

        public void serve(int port) throws IOException {
            final ServerSocket socket = new ServerSocket(port);     //1
            try {
                for (;;) {
                    final Socket clientSocket = socket.accept();    //2
                    System.out.println("Accepted connection from " + clientSocket);

                    new Thread(new Runnable() {                        //3
                        @Override
                        public void run() {
                            OutputStream out;
                            try {
                                out = clientSocket.getOutputStream();
                                out.write("Hi!\r\n".getBytes(Charset.forName("UTF-8")));                            //4
                                out.flush();
                                clientSocket.close();                //5

                            } catch (IOException e) {
                                e.printStackTrace();
                                try {
                                    clientSocket.close();
                                } catch (IOException ex) {
                                    // ignore on close
                                }
                            }
                        }
                    }).start();                                        //6
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public class PlainNioServer {
        public void serve(int port) throws IOException {
            ServerSocketChannel serverChannel = ServerSocketChannel.open();
            serverChannel.configureBlocking(false);
            ServerSocket ss = serverChannel.socket();
            InetSocketAddress address = new InetSocketAddress(port);
            ss.bind(address);                                            //1
            Selector selector = Selector.open();                        //2
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);    //3
            final ByteBuffer msg = ByteBuffer.wrap("Hi!\r\n".getBytes());
            for (;;) {
                try {
                    selector.select();                                    //4
                } catch (IOException ex) {
                    ex.printStackTrace();
                    // handle exception
                    break;
                }
                Set<SelectionKey> readyKeys = selector.selectedKeys();    //5
                Iterator<SelectionKey> iterator = readyKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    iterator.remove();
                    try {
                        if (key.isAcceptable()) {                //6
                            ServerSocketChannel server =
                                    (ServerSocketChannel)key.channel();
                            SocketChannel client = server.accept();
                            client.configureBlocking(false);
                            client.register(selector, SelectionKey.OP_WRITE |
                                    SelectionKey.OP_READ, msg.duplicate());    //7
                            System.out.println(
                                    "Accepted connection from " + client);
                        }
                        if (key.isWritable()) {                //8
                            SocketChannel client =
                                    (SocketChannel)key.channel();
                            ByteBuffer buffer =
                                    (ByteBuffer)key.attachment();
                            while (buffer.hasRemaining()) {
                                if (client.write(buffer) == 0) {        //9
                                    break;
                                }
                            }
                            client.close();                    //10
                        }
                    } catch (IOException ex) {
                        key.cancel();
                        try {
                            key.channel().close();
                        } catch (IOException cex) {
                            // 在关闭时忽略
                        }
                    }
                }
            }
        }
    }

    @Scope("singleton")
    public class NettyOioServer {

        public void server(int port) throws Exception {
            /*final ByteBuf buf = Unpooled.unreleasableBuffer(
                    Unpooled.copiedBuffer("Hi!\r\n", Charset.forName("UTF-8")));
            EventLoopGroup group = new OioEventLoopGroup();
            try {
                ServerBootstrap b = new ServerBootstrap();        //1

                b.group(group)                                    //2
                        .channel(OioServerSocketChannel.class)
                        .localAddress(new InetSocketAddress(port))
                        .childHandler(new ChannelInitializer<SocketChannel>() {//3
                            @Override
                            public void initChannel(SocketChannel ch)
                                    throws Exception {
                                ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {            //4
                                    @Override
                                    public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                        ctx.writeAndFlush(buf.duplicate()).addListener(ChannelFutureListener.CLOSE);//5
                                    }
                                });
                            }
                        });
                ChannelFuture f = b.bind().sync();  //6
                f.channel().closeFuture().sync();
            } finally {
                group.shutdownGracefully().sync();        //7
            }*/
        }
    }
}
