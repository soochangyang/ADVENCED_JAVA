package thread.cas.increment;

import java.util.concurrent.atomic.AtomicInteger;

public class MyAtomicInteger implements IncrementInteger{

    AtomicInteger atomicInteter = new AtomicInteger(0);

    @Override
    public void increment() {
        atomicInteter.incrementAndGet();
    }

    @Override
    public int get() {
        return atomicInteter.get();
    }
}
