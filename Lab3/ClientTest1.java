//
//  Hello World client in Java
//  Connects REQ socket to tcp://localhost:5555
//  Sends "Hello" to server, expects "World" back
//

import org.zeromq.SocketType;
import org.zeromq.ZMQ;
import org.zeromq.ZContext;

public class ClientTest1 {
    public static void main(String[] args) {
        try (ZContext context = new ZContext()) {
            //  Socket to talk to server
            System.out.println("Connecting to hello world server");

            ZMQ.Socket socket = context.createSocket(SocketType.REQ);
            socket.connect("tcp://localhost:5555");
            System.out.println("connected");
            //socket.send("Hello", 0);
            String request = "Hello";
            System.out.println("Sending Hello ");
            socket.send(request.getBytes(ZMQ.CHARSET), 0);
            System.out.println(new String(socket.recv(0)));
            byte[] reply = socket.recv(0);
                System.out.println(
                    "Received " + new String(reply, ZMQ.CHARSET)
                    
                );

        }
    }
}