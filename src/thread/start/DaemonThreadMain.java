package thread.start;

/**
 * User Thread vs. Daemon Thread
 * User Thread:
 * These are high-priority threads designed to execute the main logic of the application.
 * The Java Virtual Machine (JVM) will not exit as long as there is at least one User Thread still running.
 *
 * Daemon Thread:
 * These are low-priority "service" threads that run in the background.
 * They provide support to User Threads (e.g., Garbage Collection).
 * The JVM will exit immediately once all User Threads have finished,
 * even if Daemon Threads are still running.
 *
 * The method thread.setDaemon(true); is used to mark a specific thread as a Daemon.
 *
 * Timing:
 * This must be called before the thread is started (before thread.start()).
 * If you call it while the thread is running,
 * it will throw an IllegalThreadStateException.
 *
 * Effect:
 * By setting this to true, you are telling the JVM, "This thread isn't essential.
 * If everything else is done, feel free to shut down even if this thread is still working."
 */

public class DaemonThreadMain {
    public static void main(String[] args){
        System.out.println(Thread.currentThread().getName() + ": main() start");
        DaemonThread daemonThread = new DaemonThread();
        daemonThread.setDaemon(true); //is DeamonTread setting
        //daemonThread.setDaemon(false);
        daemonThread.start();
        System.out.println(Thread.currentThread().getName() + ": main() end");
    }

    static class DaemonThread extends Thread {

        @Override
        public void run(){
            System.out.println(Thread.currentThread().getName() + ": run()" );
            try {
                Thread.sleep(10000); //10Sec sleep
            } catch (InterruptedException e){
                throw new RuntimeException(e);
            }

            System.out.println(Thread.currentThread().getName()+ ": run() end");
        }
    }
}
