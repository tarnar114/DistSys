import org.zeromq.ZMQ;

public class ZClient {

    public static void main(String[] args) {
        ZMQ.Context context = ZMQ.context(1);


        ZMQ.Socket requester = context.socket(ZMQ.REQ);
        requester.connect("tcp://localhost:5555");

        requester.close();
        context.term();
    }
}