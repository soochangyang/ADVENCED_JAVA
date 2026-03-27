package thread.sync;

import static util.MyLogger.log;

public class BankAccountV1 implements BankAccount{
    private int balance;

    public BankAccountV1(int balance){
        this.balance= balance;
    }

    @Override
    public boolean withdraw(int amount) {
        log("거래 시작 : "+ getClass().getSimpleName());
        //잔고가 출금액 보다 적어면 안됨

        log("[검증 시작] 출금액: "+amount +", 잔액 :"+balance);
        if (balance < amount){
            log("[검증 실패] 출금액: "+amount +", 잔액 :"+balance);
            return false;
        }

        log("[검증 완료] 출금액: "+amount +", 잔액 :"+balance);
        balance = balance - amount;
        log("[출금 완료] 출금액: "+amount +", 잔액 :"+balance);
        //
        log("거래 종료 : "+ getClass().getSimpleName());
        return true;
    }

    @Override
    public int getBalance() {
        return balance;
    }
}
