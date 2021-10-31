import org.zeromq.SocketType;
import org.zeromq.ZMQ;
import org.zeromq.ZContext;
import java.nio.ByteBuffer;
import java.math.BigInteger;
public class Task3Server{
    public static void main(String[] args)throws Exception {
        try(ZContext context=new ZContext()){
             //  Socket to talk to clients
             ZMQ.Socket socket = context.createSocket(SocketType.REP);
             socket.bind("tcp://*:5555");
       
             while (!Thread.currentThread().isInterrupted()) {
                byte[] reply = socket.recv(0);

                String in=new String(reply,ZMQ.CHARSET);
                if (in.equals("close")){
                    break;
                }
                System.out.println(
                  "Received " + ": [" + in + "]"
                );
                in=in.toUpperCase();
                socket.send(in.getBytes(ZMQ.CHARSET), 0);
                Thread.sleep(500); //  Do some 'work'
        }
    }
}
}