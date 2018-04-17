public class Consumer extends Thread {
    MyQueue q;

    Consumer(MyQueue q) {
        this.q=q;
    }
    public void run()
    {
        while (true) {
            try {
                q.consume();
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
