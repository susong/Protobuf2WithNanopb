package com.dream;


import com.dream.pb.MessageProto;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        Socket socket = serverSocket.accept();

        InputStream in = socket.getInputStream();
        OutputStream out = socket.getOutputStream();

        MessageProto.Message messageReceive = MessageProto.Message.parseDelimitedFrom(in);
        System.out.println("服务端收到:\r\n" + messageReceive.toString());

        MessageProto.Message.Builder builder = MessageProto.Message.newBuilder();
        builder.setInt32Value(57);
        MessageProto.Message messageResponse = builder.build();
        System.out.println("服务端发送:\r\n" + messageResponse.toString());
        messageResponse.writeDelimitedTo(out);

        out.close();
        in.close();
        socket.close();
        serverSocket.close();
    }
}
