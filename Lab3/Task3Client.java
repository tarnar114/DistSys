import org.zeromq.SocketType;
import org.zeromq.ZMQ;
import org.zeromq.ZContext;
import java.util.Scanner;

public class Task3Client {
    public static void main(String[] args) {
        try(ZContext context=new ZContext()){
            System.out.println("Connecting to hello world server");

            //  Socket to talk to server
          ZMQ.Socket socket = context.createSocket(SocketType.REQ);
          socket.connect("tcp://localhost:5555");

         
            Scanner in = new Scanner(System.in);
            while (true){
                String s=in.next();
                socket.send(s.getBytes(ZMQ.CHARSET),0);
                if(s.equals("close")){
                    break;
                }
                byte[] reply=socket.recv(0);
                System.out.println(
                    "Received " + new String(reply, ZMQ.CHARSET)
                );
            }
            
            }
        }
    }

