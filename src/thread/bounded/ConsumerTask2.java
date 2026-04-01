package thread.bounded;

import java.util.concurrent.BlockingQueue;

import static util.MyLogger.log;

public class ConsumerTask2 implements Runnable {
    private BlockingQueue queue;

    public ConsumerTask2(BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        log("[소비 시도]     ? <- " + queue);
        Object result = queue.poll();
        log("[소비 완료] " + result + " <- " + queue);
    }

}
