import java.util.Random;

import org.zeromq.SocketType;
import org.zeromq.ZMQ;
import org.zeromq.ZContext;

//
//  Weather update server in Java
//  Binds PUB socket to tcp://*:5556
//  Publishes random weather updates
//
public class wuserver
{
    public static void main(String[] args) throws Exception
    {
        //  Prepare our context and publisher
        try (ZContext context = new ZContext()) {
            ZMQ.Socket publisher = context.createSocket(SocketType.PUB);
            publisher.bind("tcp://*:5556");

            int postalCode;
            for(postalCode=0;postalCode<10;postalCode++){
                publisher.send(String.format("%d", postalCode),ZMQ.SNDMORE);
            }
            //  Initialize random number generator
            Random random = new Random(System.currentTimeMillis());
            while (!Thread.currentThread().isInterrupted()) {
                //  Get values that will fool the boss
                Thread.sleep(500);
                String data=String.format("%d",random.nextInt(100000) );
                String dest=String.format("%d", random.nextInt(10000));
                publisher.send(dest, ZMQ.SNDMORE);
                publisher.send(data);
                System.out.println("Code: [" + dest + "]: Population: " + data);
                
            }
            publisher.close();
            
        }
    }
}