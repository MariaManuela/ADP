import java.util.Queue;

public class Producer extends Thread {
    MyQueue  q;

    Producer(MyQueue q)  {
        this.q = q;

    }
    public void run()
    {
        for(int i = 0; i<=10; i++) {

            try {
                q.produce(i);
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
