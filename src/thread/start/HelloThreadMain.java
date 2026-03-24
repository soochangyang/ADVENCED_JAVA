package thread.start;

public class HelloThreadMain {

    public static void main(String[] args) {

        System.out.println(Thread.currentThread().getName() + ": main() start");

        HelloThread helloThread = new HelloThread();
        System.out.println("start() : before invoke");
        helloThread.start();
        System.out.println("start() : after invoke");

        System.out.println(Thread.currentThread().getName() + ": main() finish");
    }
}
