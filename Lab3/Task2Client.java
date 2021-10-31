import org.zeromq.SocketType;
import org.zeromq.ZMQ;
import org.zeromq.ZContext;
import java.nio.ByteBuffer;
import java.math.BigInteger;
public class Task2Client {

    public static void main(String[] args) {
        
        try (ZContext context = new ZContext()) {
            System.out.println("Connecting to hello world server");

      		//  Socket to talk to server
            ZMQ.Socket socket = context.createSocket(SocketType.REQ);
            socket.connect("tcp://localhost:5555");

            // for (int requestNbr = 0; requestNbr != 10; requestNbr++) {
            //     String request = "Hello";
            //     System.out.println("Sending Hello " + requestNbr);
            //     socket.send(request.getBytes(ZMQ.CHARSET), 0);

            //     byte[] reply = socket.recv(0);
            //     System.out.println(
            //         "Received " + new String(reply, ZMQ.CHARSET) + " " +
            //         requestNbr
            //     );
            // }
            int fibNum=10;
            ByteBuffer bb=ByteBuffer.allocate(4);
            bb.putInt(fibNum);


            System.out.println("sending fib num to server for calc");
            socket.send(bb.array(),0);

            byte[] reply=socket.recv(0);
            // int ans=ByteBuffer.wrap(reply).getInt();
            
            System.out.println( "Received " + new String(reply, ZMQ.CHARSET));
        }
    }
}