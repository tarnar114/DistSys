import org.zeromq.ZMQ;

public class ZServer {

    public static void main(String[] args) throws Exception {
        ZMQ.Context context = ZMQ.context(1);

        ZMQ.Socket responder = context.socket(ZMQ.REP);
        responder.bind("tcp://*:5555");

        //while (!Thread.currentThread().isInterrupted()){

        //}
 
        responder.close();
        context.term();
    }
}