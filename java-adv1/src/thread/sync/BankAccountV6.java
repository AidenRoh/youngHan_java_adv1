package thread.sync;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class BankAccountV6 implements BankAccount {

    private int balance;

    private final Lock lock = new ReentrantLock();

    public BankAccountV6(int initialBalance) {
        this.balance = initialBalance;
    }

    @Override
    public boolean withdraw(int amount) {
        log("transaction commenced: " + getClass().getSimpleName());

        try {
            if (!lock.tryLock(500, TimeUnit.MILLISECONDS)) {
                log("[Fail to proceed] there is another process already.");
                return false;
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        try{
            log("[validation commenced] withdraw amount: " + amount + ", current balance: " + balance);
            // balance < transaction amount
            if (balance < amount) {
                log("[validation failed]");
                return false;
            }
            // balance <= transaction amount
            log("[validation successful]");
            sleep(1000); //assume processing time for transaction
            balance -= amount;
            log("[withdraw successful] withdraw amount: " + amount + ", current balance: " + balance);

        } finally {
            lock.unlock();
        }

        log("transaction terminated");
        return true;
    }

    @Override
    public  int getBalance() {
        lock.lock();
        try {
            return balance;
        } finally {
            lock.unlock();
        }
    }
}
