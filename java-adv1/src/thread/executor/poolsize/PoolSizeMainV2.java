package thread.executor.poolsize;

import thread.executor.ExecutorUtils;
import thread.executor.RunnableTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static thread.executor.ExecutorUtils.*;
import static util.MyLogger.log;

public class PoolSizeMainV2 {

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(2);
        // ==  ExecutorService es = new ThreadPoolExecutor(2, nThreads, 0L, TimeUnit.MILLISECONDS, new LinkedBLockingQueue<Runnable>());
        log("pool 생성");
        printState(es);

        for (int i = 1; i <= 6; i++) {
            String taskName = "task" + i;
            es.execute(new RunnableTask(taskName));
            printState(es, taskName);
        }

        es.close();
        log("== shutdown 완료 ==");
    }
}