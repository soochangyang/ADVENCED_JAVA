package thread.volatile1;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

/**
 *  memory visibility
 *  멀티스레드 환경에서 한 스레드가 변경한 값이 다른 스레드에서 언제 보이는지에 대한 것을 메모리 가시성(memory visibility)라고 함
 *  메모리에 변경한값이 보이는가? 보이지 않는가의 문제이다.
* */
public class VolatileCountMain {
    public static void main(String[] args) {

        MyTask task = new MyTask();
        Thread t= new Thread(task, "work");
        t.start();

        sleep(1000);

        task.flag = false;
        log("flag = "+ task.flag + ", count = " + task.count + " in main()");
    }

    static class MyTask implements Runnable{
        //boolean flag = true;
        //long count;

        volatile boolean flag = true;
        volatile long count;

        @Override
        public void run() {
            while(flag){
                count++;
                //1억번에 한번씩 출력
                if (count % 100_000_000 == 0){
                    log("flag = "+ flag + ", count = " + count + " in while()");
                }
            }
            log("flag = "+ flag + ", count = " + count + " 종료");
        }
    }
}
