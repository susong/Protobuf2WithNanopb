package com.dream;

import com.dream.pb.MessageProto;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TcpClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 8080);

        InputStream in = socket.getInputStream();
        OutputStream out = socket.getOutputStream();

        MessageProto.Message.Builder builder = MessageProto.Message.newBuilder();
        builder.setInt32Value(56);
        MessageProto.Message messageSend = builder.build();
        System.out.println("客户端发送:\r\n" + messageSend.toString());
        messageSend.writeDelimitedTo(out);

        MessageProto.Message messageReceive = MessageProto.Message.parseDelimitedFrom(in);
        System.out.println("客户端收到:\r\n" + messageReceive.toString());

        out.close();
        in.close();
        socket.close();
    }
}
