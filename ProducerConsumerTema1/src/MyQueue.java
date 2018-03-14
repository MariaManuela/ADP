

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;


public class MyQueue<T>
{
    public Queue<T> queue = new LinkedList<>();
    int capacity;


    public MyQueue(int capacity)
    {
        this.capacity = capacity;
    }

    public synchronized void produce(T element) throws InterruptedException
    {
        while (queue.size() == capacity)
        {

               wait();
        }
        queue.add(element);
        notifyAll();
        System.out.println("Producer: " + element);

    }

    public synchronized T consume() throws InterruptedException
    {
        while(queue.isEmpty())
        {
            wait();
        }
        T item = queue.remove();
        notifyAll();
        System.out.println("Consumer: " + item);
        return item;

    }


}
