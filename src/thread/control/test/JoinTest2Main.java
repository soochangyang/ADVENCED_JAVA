package thread.control.test;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;


public class JoinTest2Main {
    public static void main(String[] args) throws InterruptedException{

        log("스레드 시작");
        Thread t1 = new Thread(new MyTask(), "1");
        Thread t2 = new Thread(new MyTask(), "2");
        Thread t3 = new Thread(new MyTask(), "3");

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        log("모든 스레드 실행완료 //완료 3초 소요 ");
    }


    static class MyTask implements Runnable{

        @Override
        public void run(){
            for (int i = 1; i <=3; i++){
                log(i);
                sleep(1000);
            }
        }
    }
}
