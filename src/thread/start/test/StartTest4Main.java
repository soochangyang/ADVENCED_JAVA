package thread.start.test;

import static util.MyLogger.log;

public class StartTest4Main {
    public static void main(String[] args) {

        PrintWork printWorkA = new PrintWork("A", 1000);
        PrintWork printWorkB = new PrintWork("B", 500);

        Thread threadA = new Thread(printWorkA, "Thread-A");
        Thread threadB = new Thread(printWorkB, "Thread-B");
        threadA.start();
        threadB.start();
    }

    static class PrintWork implements Runnable{
        private String content;
        private int sleepMs;

        public PrintWork(String content, int sleepMs){
            this.content = content;
            this.sleepMs = sleepMs;
        }

        @Override
        public void run() {
            while (true){
                log(content);
                try {
                    Thread.sleep(sleepMs);
                }catch(InterruptedException e){
                    throw new RuntimeException(e);
                }
            }
        }
    }

}
