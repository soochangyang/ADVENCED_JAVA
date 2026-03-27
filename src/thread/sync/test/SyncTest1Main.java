package thread.sync.test;

import static util.ThreadUtils.sleep;

public class SyncTest1Main {
    public static void main(String[] args) throws InterruptedException{
        Counter counter = new Counter();

        Runnable task = new Runnable(){
            @Override
            public void run(){
                for (int i = 0; i < 10000 ; i++ ){
                    counter.increment();
                }
            }
        };

        Thread t1 = new Thread(task, "t1");
        Thread t2 = new Thread(task, "t2");

        t1.start();
        t2.start();
        sleep(1000);
        t1.join();
        t2.join();

        System.out.println("결과: " + counter.getCount());
    }

    static class Counter{
        private int count = 0;

        public synchronized void increment(){
            count = count + 1 ;
        }

        public int getCount(){
            return count;
        }


    }
}
