import java.util.StringTokenizer;

import org.zeromq.SocketType;
import org.zeromq.ZMQ;
import org.zeromq.ZContext;
import java.util.Scanner;

//
//  Weather update client in Java
//  Connects SUB socket to tcp://localhost:5556
//  Collects weather updates and finds avg temp in zipcode
//
public class wuclient
{
    public static void main(String[] args)
    {
        try (ZContext context = new ZContext()) {
            //  Socket to talk to server
            System.out.println("Collecting updates from weather server");
            ZMQ.Socket subscriber = context.createSocket(SocketType.SUB);
            subscriber.connect("tcp://localhost:5556");

            //  Subscribe to zipcode, default is NYC, 10001
            Scanner input = new Scanner(System.in);

            System.out.print("What do you want to subscribe to: ");
            int postalCode = input.nextInt();
            String subscription = String.format("%d", postalCode);
            subscriber.subscribe(subscription.getBytes(ZMQ.CHARSET));

            while (true) {
                String topic=subscriber.recvStr();
                if(topic==null){
                    break;
                }
                String data=subscriber.recvStr();
                System.out.println("Received Code: [" + topic + "] Population: " + data);

            }
            subscriber.close();
            
           
        }
    }
}