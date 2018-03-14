import java.util.LinkedList;
import java.util.Queue;

public class main
{
    public static void main(String args[]) throws InterruptedException {

        MyQueue q = new MyQueue();
        Consumer consumer = new Consumer(q);
        Producer producer = new Producer(q);


       new Thread(consumer).start();
       new Thread(producer).start();

       new Thread(consumer).join();
       new Thread(producer).join();
    }
}
