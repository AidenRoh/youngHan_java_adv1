package thread.control.interrupt;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class ThreadStopMainV4 {

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
            while (!Thread.interrupted()) { //isInterrupted() + statement change from true -> false
                    log("running");
            }
            log("work thread interrupted (2)= " + Thread.currentThread().isInterrupted());
            //interrupted() will change it statement into false
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
