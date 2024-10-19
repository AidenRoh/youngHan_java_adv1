package thread.sync;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class BankAccountV2 implements BankAccount {

    private int balance;

    public BankAccountV2(int initialBalance) {
        this.balance = initialBalance;
    }

    @Override
    public synchronized boolean withdraw(int amount) {
        log("transaction commenced: " + getClass().getSimpleName());

        // critical section ----->
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
        // <----- critical section

        log("transaction terminated");
        return true;
    }

    @Override
    public synchronized int getBalance() {
        return balance;
    }
}