package thread.volatile1;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class VolatileFlagMain {

    public static void main(String[] args) {
        MyTask myTask = new MyTask();
        Thread t = new Thread(myTask, "work");
        log("runFlag = " + myTask.runFlag);
        t.start();

        sleep(1000);
        log("runFlag를 false로 변경 시도 ");
        myTask.runFlag = false;
        log("runFlag = " + myTask.runFlag);
        log("main 종료");
    }

    static class MyTask implements Runnable{

        //boolean runFlag = true;
        /** volatile를 사용하면 캐쉬메모리를 사용하지 않고 메인메모리를 사용한다.
         *  스레드 구간에서 캐쉬메모리의 속도가 빠르지만 케쉬메모리속 변수가 언제 초기화 될지 장담할 수 없다.
         *
         */


        volatile boolean runFlag = true;

        @Override
        public void run() {
            log("task Start");
            while(runFlag){
                // runFlag가  false로 변하면 탈출
                // 작업수행을 하면 context swiching이 발생하기도한다. 그러나 게런티 할 수 없다.
            }
            log("task End");
        }
    }
}
