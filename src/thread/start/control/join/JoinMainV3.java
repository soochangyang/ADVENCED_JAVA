package thread.start.control.join;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class JoinMainV3 {

    public static void main(String[] args) throws InterruptedException {
        log("Start");

        SumTask task1 = new SumTask(1, 50);
        SumTask task2 = new SumTask(51, 100);

        Thread thread1 = new Thread(task1, "thread-1");
        Thread thread2 = new Thread(task2, "thread-2");

        thread1.start();
        thread2.start();

        //Wait until Thread finish
        log(" join() - main thread wait until thread1, thread2 finish ");

        /**
         * DESCRIPTION:
         * The Thread.join() method causes the calling thread to wait until the Runnable's run method terminates.
        * */
        thread1.join();
        thread2.join();

        log("task1.result = "+task1.result);
        log("task2.result = "+task2.result);

        int sumAll = task1.result + task2.result;
        log("task1.result + task2.result = "+ sumAll);
        log("End");
    }


    static class SumTask implements Runnable {
        int startValue;
        int endValue;
        int result = 0;

        public SumTask(int startValue, int endValue) {
            this.startValue = startValue;
            this.endValue = endValue;
        }

        @Override
        public void run() {
            log("Job Start ");
            //sleep(2000);
            int sum = 0;
            for (int i = startValue; i <= endValue; i++) {
                sum += i;
            }
            result = sum;
            log("Job Complete reslt = " + result);
        }
    }
}
