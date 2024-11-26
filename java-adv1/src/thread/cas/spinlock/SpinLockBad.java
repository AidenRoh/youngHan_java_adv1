package thread.cas.spinlock;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class SpinLockBad {

    private volatile boolean lock;

    public void lock() {
        log("lock 획득 시도");
        while (true) {
            if (!lock) {
                sleep(1000);
                lock = true;
                break;
            } else {
                // lock을 획득할 때까지 스핀 대기(바쁜 대기)
                log("lock 획득 실패 ==> 스핀 대기");
            }
        }
        log("lock 획득 완료");
    }

    public void unlock() {
        lock = false;
        log("lock 반납 완료");
    }

}
