package thread.control.join;

import static util.ThreadUtils.sleep;
import static util.MyLogger.log;

public class JoinMainV0 {
    public static void main(String[] args) throws InterruptedException {
        log("Start");
        Thread thread1 = new Thread(new Job(), "thread-1");
        Thread thread2 = new Thread(new Job(), "thread-2");

        thread1.start();
        thread2.start();

        log("End");
    }

    static class Job implements Runnable {
        @Override
        public void run() {
            log("Job Start ");
            sleep(2000);
            log("Job End");
        }
    }
}
