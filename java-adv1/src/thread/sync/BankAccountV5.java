package thread.sync;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class BankAccountV5 implements BankAccount {

    private int balance;

    private final Lock lock = new ReentrantLock();

    public BankAccountV5(int initialBalance) {
        this.balance = initialBalance;
    }

    @Override
    public boolean withdraw(int amount) {
        log("transaction commenced: " + getClass().getSimpleName());

        if (!lock.tryLock()) {
            log("[Fail to proceed] there is another process already.");
            return false;
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
