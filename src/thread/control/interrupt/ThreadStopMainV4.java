package thread.control.interrupt;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class ThreadStopMainV4 {

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
            // Thread.interrupted()는 생태를 체크후 true를 반환한 후 false로 변경한다.
            while (!Thread.interrupted()){
                log("작업중");
            }

            log("work 스레드 인터럽트 상태2 = "+  Thread.currentThread().isInterrupted());

            try {
                log("자원 정리");
                // interrupted 상태가 false가 되면 아래 sleep코드 실행이 가능해지고 예외가 발생하지 않는다.
                // interrupted 된 상태에서 thread는 sleep를 수행할 수 없어 예외가 발생한다
                Thread.sleep(1000);
                log("자원 종료");
            } catch (InterruptedException e){
                log("work 스레드 인터럽트 상태3 = "+  Thread.currentThread().isInterrupted());
            }
            log("작업 종료");
        }
    }
}
