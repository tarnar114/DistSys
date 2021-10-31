import org.zeromq.SocketType;
import org.zeromq.ZMQ;
import org.zeromq.ZContext;
import java.nio.ByteBuffer;
import java.math.BigInteger;
public class task2Server {

    public static void main(String[] args) throws Exception {
        try (ZContext context = new ZContext()) {
            //  Socket to talk to clients
            ZMQ.Socket socket = context.createSocket(SocketType.REP);
            socket.bind("tcp://*:5555");
      
            while (!Thread.currentThread().isInterrupted()) {
              byte[] reply = socket.recv(0);
            //   System.out.println(
            //     "Received " + ": [" + new String(reply, ZMQ.CHARSET) + "]"
            //   );
            //   String response = "world";
            //   socket.send(response.getBytes(ZMQ.CHARSET), 0);
            //   Thread.sleep(1000); //  Do some 'work'
                int fibReply=ByteBuffer.wrap(reply).getInt();
                String ans=fibString(fibReply);
                String response="fib series: "+ans;
                socket.send(response.getBytes(ZMQ.CHARSET),0);
            }
          }  
    }
    static String fibString(int n){
        String ans="";
        int prevNum=0, nextNum=1;
        int i=1;
        while (i<=n){
            ans+=prevNum+" ";
            int sum=prevNum+nextNum;
            prevNum=nextNum;
            nextNum=sum;
            i++;
        }
        System.out.println(ans);
        return ans;
    }
    

}