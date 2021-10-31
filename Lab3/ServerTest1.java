//
//  Hello World server in Java
//  Binds REP socket to tcp://*:5555
//  Expects "Hello" from client, replies with "World"
//

import org.zeromq.SocketType;
import org.zeromq.ZMQ;
import org.zeromq.ZContext;

public class ServerTest1 {
    public static void main(String[] args) throws Exception {
        try (ZContext context = new ZContext()) {
            // Socket to talk to clients
            ZMQ.Socket socket = context.createSocket(SocketType.REP);
            socket.bind("tcp://*:5555");
            System.out.println("Waiting for connections...");

            while(!Thread.currentThread().isInterrupted()) {
                System.out.println("connected");

                byte[] reply = socket.recv(0);

                //socket.send("World", 0);
                System.out.println(
                    "Received " + ": [" + new String(reply, ZMQ.CHARSET) + "]"
                  );
                String response = "World";
                socket.send(response.getBytes(ZMQ.CHARSET), 0); 
                Thread.sleep(1000);       
            }
        }
    }
}