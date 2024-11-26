package thread.cas.spinlock;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class SpinLockMain {

    public static void main(String[] args) {
        SpinLockBad spinLockBad = new SpinLockBad();
        SpinLock spinLock = new SpinLock();

        Runnable task = new Runnable() {
            @Override
            public void run() {
                spinLock.lock();
                //critical section
                try {
                    log("bizLogic");
                    sleep(10);
                } finally {
                    spinLock.unlock();
                }
            }
        };

        Thread t1 = new Thread(task, "thread-1");
        Thread t2 = new Thread(task, "thread-2");

        t1.start();
        t2.start();
    }
}
