package thread.bounded;

import java.util.ArrayDeque;
import java.util.Queue;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class BoundedQueueV3 implements BoundedQueue {

    private final Queue<String> queue = new ArrayDeque<>();
    private final int max;

    public BoundedQueueV3(int max) {
        this.max = max;
    }

    @Override
    public synchronized void put(String data) {
        while (queue.size() == max) {
            log("[put] queue is full, producer wait: " + data );
            try {
                wait(); // RUNNABLE -> WAITING, LOCK 반납
                log("[put] producer awaken");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        queue.offer(data);
        log("[put] producer data save, notify() called");
        notify(); // 대기 쓰레드, WAIT -> BLOCKED
    }

    @Override
    public synchronized String take() {
        while (queue.isEmpty()) {
            log("[take] queue is empty: consumer wait");
            try {
                wait();
                log("[take] consumer awaken");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        String data = queue.poll();
        log("[take] consumer data gain, notify() called");
        notify();
        return data; //대기 쓰레드, WAIT -> BLOCKED
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
