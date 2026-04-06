package thread.executor;

//추상클래스로 만드는이유는 직접 생성해서 사용하지 말라는 의미로

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

import static util.MyLogger.log;

public abstract class ExecutorUtils {

    public static void printState(ExecutorService executorService) {
        /**
        ThreadPoolExecutor poolExecutor // Casting 새로운 문법 <-- Casting을 한이유 ?????
        = ThreadPoolExecutor abc = (ThreadPoolExecutor) poolExecurtor; //Casting 과거문법
         */
        if (executorService instanceof ThreadPoolExecutor poolExecutor) {
            // 스레드풀에서 관리되는 스레스 숫자
            int pool = poolExecutor.getPoolSize();
            // 작업을 수행하는 스레드 숫자
            int active = poolExecutor.getActiveCount();
            // queuedTask 큐에 대기중인 작업의 숫자
            int queuedTask = poolExecutor.getQueue().size();
            // completedTask 완료된 작업의 숫자
            long completedTask = poolExecutor.getCompletedTaskCount();
            log("[pool=" + pool + ", active=" + active +
                    ", queued=" + queuedTask + ", completedTask=" + completedTask +"]");
        } else {
            log(executorService);
        }
    }
}
