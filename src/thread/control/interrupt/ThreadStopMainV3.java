package thread.control.interrupt;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class ThreadStopMainV3 {

    public static void main(String[] args){
        MyTask task = new MyTask();
        Thread thread = new Thread(task,"work");
        thread.start();

        sleep(100);
        log("작업 중단 지시 Thread.interrupt()");
        thread.interrupt();
        log("work 스레드 인터럽트 상태1 = " + thread.isInterrupted());
    }


    static class MyTask implements Runnable{

        @Override
        public void run(){
            while (!Thread.currentThread().isInterrupted()){
                log("작업중");
            }

            log("work 스레드 인터럽트 상태2 = "+  Thread.currentThread().isInterrupted());

            try {
                log("자원 정리");
                Thread.sleep(1000);
                log("자원 종료");
            } catch (InterruptedException e){
                log("work 스레드 인터럽트 상태3 = "+  Thread.currentThread().isInterrupted());
            }
            log("작업 종료");
        }
    }
}
