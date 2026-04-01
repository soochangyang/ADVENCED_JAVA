package thread.bounded;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class BoundedMain2 {

    public static void main(String[] args) {
        //1. BoundedQueue 선택
        BlockingQueue<String> queue = new ArrayBlockingQueue<String>(2);

        //2. 생산자, 소비자 실행 순서 선택, 반드시 하나만 선택!!!!
        //producerFirst(queue); //생산자 먼저 실행
        consumerFirst(queue); //소비자 먼저 실행
    }

    private static void producerFirst(BlockingQueue queue) {
        log("==[생산자 먼저 실행] 시작, " + queue.getClass().getSimpleName() + "== ");
        List<Thread> threads = new ArrayList<>();
        startProducer(queue, threads);
        printAllState(queue, threads);
        startConsumer(queue, threads);
        printAllState(queue, threads);
        log("==[생산자 먼저 실행] 종료, " + queue.getClass().getSimpleName() + "== ");
    }

    private static void startProducer(BlockingQueue queue, List<Thread> threads) {
        log();
        log("생산자 시작");
        for (int i = 1; i <= 3; i++) {
            Thread producer = new Thread(new ProducerTask2(queue, "data_" + i), "producer_" + i);
            threads.add(producer);
            producer.start();
            sleep(100);
        }
    }

    private static void startConsumer(BlockingQueue queue, List<Thread> threads) {
        log();
        log("소비자 시작");
        for (int i = 1; i <= 3; i++) {
            Thread consumer = new Thread(new ConsumerTask2(queue), "consumer_" + i);
            threads.add(consumer);
            consumer.start();
            sleep(100);
        }
    }

    private static void printAllState(BlockingQueue queue, List<Thread> threads) {
        log();
        log("현재 상태 출력, 큐 데이터: "+queue);
        for (Thread thread : threads)  {
            log(thread.getName() + ": " + thread.getState());
        }
    }

    private static void consumerFirst(BlockingQueue queue) {
        log("==[소비자 먼저 실행] 시작, " + queue.getClass().getSimpleName() + "== ");
        List<Thread> threads = new ArrayList<>();
        startConsumer(queue, threads);
        printAllState(queue, threads);
        startProducer(queue, threads);
        printAllState(queue, threads);
        log("==[소비자 먼저 실행] 시작, " + queue.getClass().getSimpleName() + "== ");
    }

/*    static class Task implements Runnable{
        @Override
        public void run() {

        }
    }*/
}


