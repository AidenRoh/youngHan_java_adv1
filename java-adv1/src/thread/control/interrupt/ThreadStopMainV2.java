package thread.control.interrupt;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class ThreadStopMainV2 {

    public static void main(String[] args) {
        MyTask task = new MyTask();
        Thread thread = new Thread(task, "work");
        thread.start();

        sleep(4000);
        log("thread.interrupt()");
        thread.interrupt(); // interrupt() is working with methods relevant to Thread class
        log("work thread interrupted (1) = " + thread.isInterrupted());
    }

    static class MyTask implements Runnable {

        @Override
        public void run() {
            try {
                while (true) {
                    log("running");
                    Thread.sleep(3000); // this spot is where interruptedException occurring once Thread.interrupt() called
                    // TIMED_WAITING -> RUNNABLE -> InterruptedException! -> get out of while{}
                }
            } catch (InterruptedException e) {
                log("work thread interrupted (2)= " + Thread.currentThread().isInterrupted());
                //once InterruptedException occurred, Thread statement will be back to normal (isInterrupted(): true -> false)
                log("interrupt message= " + e.getMessage());
                log("state= " + Thread.currentThread().getState());
            }

            log("source organizing ");
            log("source terminated");
        }
    }
}
