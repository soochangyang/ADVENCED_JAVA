package thread.bounded;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static util.MyLogger.log;

public class BoundedQueueV5 implements BoundedQueue {
    /**
     * condition 분리
     */
    private final Lock lock = new ReentrantLock();
    private final Condition producerCondition = lock.newCondition();
    private final Condition consumerCondition = lock.newCondition();

    private final Queue<String> queue = new ArrayDeque<String>();

    private final int max;

    public BoundedQueueV5(int max) {
        this.max = max;
    }

    @Override
    public void put(String data) {
        lock.lock();
        try {
            while (queue.size() == max) {
                try {
                    /**/
                    log("[put] 큐가 가득 참, 생산자 대기 producerCondition.await() : " + data);
                    producerCondition.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            queue.offer(data);
            /**/
            log("[put] 생산자 데이터 저장, consumerCondition.signal() 호출");
            consumerCondition.signal();
        } finally {
            lock.unlock();
        }
    }


    @Override
    public String take() {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                try {
                    /**/
                    log("[take] 큐에 데이터가 없음, 소비자 대기 consumerCondition.await()");
                    consumerCondition.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            String data = queue.poll();
            /**/
            log("[take] 소비자 데이타 획득 , producerCondition.signal() 호출");
            producerCondition.signal();
            return data;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
