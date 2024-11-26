package thread.cas.spinlock;

import java.util.concurrent.atomic.AtomicBoolean;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class SpinLock {

    private final AtomicBoolean lock = new AtomicBoolean(false);

    public void lock() {
        log("lock 획득 시도");
        while(!lock.compareAndSet(false, true)) {
            log("lock 획득 실패 ==> 스핀 대기");
        }
        log("lock 획득 완료");
    }

    public void unlock() {
        lock.set(false);
        log("lock 반납 완료");
    }
}
