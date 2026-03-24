package thread.start.test;

import static util.MyLogger.log;

public class StartTest1Main {
    public static void main(String[] args) {
        log("main() start");
        CounterThread thread = new CounterThread();
        thread.start();

        log("main() end");
    }

    static class CounterThread extends Thread{
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
