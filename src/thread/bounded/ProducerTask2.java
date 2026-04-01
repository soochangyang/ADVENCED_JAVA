package thread.bounded;

import java.util.concurrent.BlockingQueue;

import static util.MyLogger.log;

public class ProducerTask2 implements Runnable {
    private BlockingQueue queue;
    private String request;

    public ProducerTask2(BlockingQueue queue, String request) {
        this.queue = queue;
        this.request = request;
    }

    @Override
    public void run() {
        log("[생산 시도] " + request + "->" +queue +"  <"+this.getClass().getSimpleName()+">");
        queue.offer(request);
        log("[생산 완료] " + request + "->" +queue +"  <"+this.getClass().getSimpleName()+">");
    }
}
