package thread.start.test;

import static util.MyLogger.log;

public class StartTest2Main {
    public static void main(String[] args) {
        log("main() start");
        Thread thread = new Thread(new CounterRunnable(), "counter");
        thread.start();

        log("main() end");
    }

    static class CounterRunnable implements Runnable{
        @Override
        public void run() {
            try {
                for (int i = 1; i <= 5; i++) {
                    Thread.sleep(1000);
                    log("value :"+ i);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
