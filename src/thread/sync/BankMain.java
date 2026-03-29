package thread.sync;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class BankMain {

    public static void main(String[] args) throws InterruptedException{
        //BankAccount account = new BankAccountV1(1000);
        //BankAccount account = new BankAccountV2(1000);
        //BankAccount account = new BankAccountV3(1000);
        BankAccount account = new BankAccountV4(1000);
        //BankAccount account = new BankAccountV5(1000);
        //BankAccount account = new BankAccountV6(1000);


        Thread t1 = new Thread(new WithdrawTask(account, 300), "t1");
        Thread t2 = new Thread(new WithdrawTask(account, 200), "t2");
        Thread t3 = new Thread(new WithdrawTask(account, 200), "t3");
        Thread t4 = new Thread(new WithdrawTask(account, 200), "t4");
        Thread t5 = new Thread(new WithdrawTask(account, 100), "t5");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

        sleep(500);
        log("t1 state : "+ t1.getState());
        log("t2 state : "+ t2.getState());
        log("t3 state : "+ t3.getState());
        log("t4 state : "+ t4.getState());
        log("t5 state : "+ t5.getState());

        t1.join();
        t2.join();
        t3.join();
        t4.join();
        t5.join();

        log("최종 잔액: "+ account.getBalance());

    }
}
