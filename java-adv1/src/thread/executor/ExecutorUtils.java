package thread.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

import static util.MyLogger.log;

public abstract class ExecutorUtils {

    public static void printState(ExecutorService service) {
        if (service instanceof ThreadPoolExecutor poolExecutor) {
            int pool = poolExecutor.getPoolSize();
            int active = poolExecutor.getActiveCount();
            int queuedTasks = poolExecutor.getQueue().size();
            long completedTasks = poolExecutor.getCompletedTaskCount();
            log("[pool=" + pool + ", active=" + active + ", queuedTasks="
                    + queuedTasks + ", completedTasks=" + completedTasks + "]");
        }
    }

    public static void printState(ExecutorService service, String taskName) {
        if (service instanceof ThreadPoolExecutor poolExecutor) {
            int pool = poolExecutor.getPoolSize();
            int active = poolExecutor.getActiveCount();
            int queuedTasks = poolExecutor.getQueue().size();
            long completedTasks = poolExecutor.getCompletedTaskCount();
            log(taskName + "[pool=" + pool + ", active=" + active + ", queuedTasks="
                    + queuedTasks + ", completedTasks=" + completedTasks + "]");
        }
    }
}
