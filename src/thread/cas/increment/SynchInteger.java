package thread.cas.increment;

public class SynchInteger implements IncrementInteger{

    private int value;

    @Override
    public void increment() {
        value++;
    }

    @Override
    public int get(){
        return value;
    }
}
