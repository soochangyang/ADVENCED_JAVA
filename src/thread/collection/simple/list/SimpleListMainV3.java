package thread.collection.simple.list;

import static util.MyLogger.log;

public class SimpleListMainV3 {

    static void main(String[] args) throws InterruptedException {
        //test(new BasicList());
        test(new SynchList());
        //test();
    }

    private static void test(SimpleList list) throws InterruptedException {
        log(list.getClass().getSimpleName());

        // A를 리스트에 저장하는 코드
        Runnable addA = new Runnable() {
            @Override
            public void run() {
                list.add("A");
                log("Thread-1: list.add(A)");
            }
        };

        // B를 리스트에 저장하는 코드
        Runnable addB = new Runnable() {
            @Override
            public void run() {
                list.add("B");
                log("Thread-2: list.add(B)");
            }
        };

        Thread t1 = new Thread(addA);
        Thread t2 = new Thread(addB);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        log(list);
    }
}

