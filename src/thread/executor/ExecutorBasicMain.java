package thread.executor;

import java.util.concurrent.*;

import static thread.executor.ExecutorUtils.printState;
import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class ExecutorBasicMain {
    static void main(String[] args) {
        ExecutorService es =
                new ThreadPoolExecutor(2, 2, 0, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>());
        log("== 초기 상태 ==");
        printState(es);
        es.execute(new RunnableTask("taskA"));
        es.execute(new RunnableTask("taskB"));
        es.execute(new RunnableTask("taskC"));
        es.execute(new RunnableTask("taskD"));
        log("== 작업 수행 중 ==");

        sleep(3000);

        log("== 작업 수행 완료 ==");
        printState(es);

        //close 는 19 버전 부터 지원 19아래는 shutdown호출
        es.close();
        log("== showdown 완료 ==");
        printState(es);
    }
}
