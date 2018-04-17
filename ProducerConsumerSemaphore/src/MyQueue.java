

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;


public class MyQueue<T>
{
    private Queue<T> queue = new LinkedList<>();
    private final static int capacity = 10;
    private Semaphore isFull = new Semaphore(0);
    private Semaphore isFree = new Semaphore(capacity);

    public MyQueue()
    {
       // this.capacity = capacity;
    }

    public void produce(T element) throws InterruptedException
    {
        isFree.acquire();
        queue.add(element);
        isFull.release();
        System.out.println("Producer: " + element);

    }

    public void consume() throws InterruptedException
    {

        isFull.acquire();
        T item = queue.remove();
        isFree.release();
        System.out.println("Consumer: " + item);


    }


}
