package thread.executor.future;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static util.MyLogger.log;

public class InvokeAnyMain {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService es = Executors.newFixedThreadPool(10);

        CallableTask taskA = new CallableTask("taskA", 1000);
        CallableTask taskB = new CallableTask("taskB", 2000);
        CallableTask taskC = new CallableTask("taskC", 3000);

        List<CallableTask> tasks = List.of(taskA, taskB, taskC);

        Integer value = es.invokeAny(tasks);
        log("value = " + value);

        es.close();
    }
}
