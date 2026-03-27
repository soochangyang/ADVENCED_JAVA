package thread.sync.test;

import static util.MyLogger.log;

public class SynkTest2Main {
    public static void main(String[] args) {
        MyCounter myCounter = new MyCounter();

        Runnable task = new Runnable(){
            @Override
            public void run(){
                myCounter.counter();
            }
        };

        Thread t1 = new Thread(task, "t1");
        Thread t2 = new Thread(task, "t2");

        t1.start();
        t2.start();
    }

    static class MyCounter{

        public void counter(){
            int localValue = 0;
            for (int i= 0; i < 1000; i++){
                localValue = localValue +1;
            }
            log("결과 : "+ localValue);
        }
    }
}
