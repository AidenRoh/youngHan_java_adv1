package thread.volatile1;

import java.sql.SQLOutput;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class VolatileFlagMain {

    public static void main(String[] args) {
        MyTask task = new MyTask();
        Thread t = new Thread(task, "work");
        log("runFlag = " + task.runFlag);
        t.start();

        sleep(1000);
        log("attempt to change runFlag false");
        task.runFlag = false;
        log("runFlag = " + task.runFlag);
        log("main terminated");
    }

    static class MyTask implements Runnable {

        boolean runFlag = true;
//        volatile boolean runFlag = true;

        @Override
        public void run() {
            log("task running");
            while (runFlag) {
//                Thread.yield();
                //it will escape one runFlag being false
            }
            log("task terminated");
        }
    }
}
