

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class MyQueue<T>
{
    private Queue<T> queue = new LinkedList<>();
    private final static int capacity = 10;
    private Lock mutex = new ReentrantLock();
    private Condition notFull = mutex.newCondition();
    private Condition notEmpty = mutex.newCondition();

    public MyQueue()
    {
       // this.capacity = capacity;
    }

    public void produce(T element) throws InterruptedException
    {
        mutex.lock();
        try {
            while (queue.size() == capacity) {
                notFull.await();
            }
            queue.add(element);
            notEmpty.signalAll();
            System.out.println("Producer: " + element);
        }finally{
            mutex.unlock();
        }

    }

    public void consume() throws InterruptedException
    {
        mutex.lock();
        try {
            while (queue.isEmpty()) {
                notEmpty.await();
            }
            T item = queue.remove();
            System.out.println("Consumer: " + item);
        }finally {
            mutex.unlock();
        }


    }


}
