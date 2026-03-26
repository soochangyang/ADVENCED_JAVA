package thread.control.test;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;


public class JoinTest1Main {
    public static void main(String[] args) throws InterruptedException{
        Thread t1 = new Thread(new MyTask(), "1");
        Thread t2 = new Thread(new MyTask(), "2");
        Thread t3 = new Thread(new MyTask(), "3");

        t1.start(); //3초
        t1.join();  //wait

        t2.start(); //3초
        t2.join();  //wait

        t3.start(); //3초
        t3.join();  //wait

        log("모든 스레드 실행완료 //완료 9초 소요 ");
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
