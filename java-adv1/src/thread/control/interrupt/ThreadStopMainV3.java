package thread.control.interrupt;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class ThreadStopMainV3 {

    public static void main(String[] args) {
        MyTask task = new MyTask();
        Thread thread = new Thread(task, "work");
        thread.start();

        sleep(2);
        log("thread.interrupt()");
        thread.interrupt(); // interrupt() is working with methods relevant to Thread class
        log("work thread interrupted (1) = " + thread.isInterrupted());
    }

    static class MyTask implements Runnable {

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                    log("running");
            }
            log("work thread interrupted (2)= " + Thread.currentThread().isInterrupted());
            //once InterruptedException occurred, Thread statement will be back to normal (isInterrupted(): true -> false)
            //but in this code Exception won't be occurred, so interrupted() will maintain as true
            try {
                log("source organizing ");
                Thread.sleep(1000);
                log("source terminated");
            } catch (InterruptedException e) {
                log("fail to organize source");
                log("work thread interrupted (3)= " + Thread.currentThread().isInterrupted());
            }
            log("operation terminated");
        }
    }
}
