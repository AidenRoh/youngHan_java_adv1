package thread.executor.future;

import java.util.concurrent.*;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class futureCancelMain {

//    private static boolean mayInterruptIfRunning = true;
    private static boolean mayInterruptIfRunning = false;

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(1);
        Future<String> future = es.submit(new MyTask());
        log("Future.state: " + future.state());

        log("일정 시간 후 취소");
        sleep(3000);

        //cancel() 호출
        log("future.cancel(" + mayInterruptIfRunning + ") 호출");
        boolean cancelResult = future.cancel(mayInterruptIfRunning);
        log("cancel(" + mayInterruptIfRunning + ") result: " + cancelResult);

        //결과 확인
        try {
            log("future result: " + future.get());
        } catch (CancellationException e) {
            log("future는 이미 취소 되었습니다.");
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        es.close();
    }

    static class MyTask implements Callable<String> {

        @Override
        public String call() throws Exception {

            try {
                for (int i = 0; i <= 10; i++) {
                    log("작업 중 : " + i);
                    Thread.sleep(1000); // sleep for one sec
                }
            } catch(InterruptedException e) {
                log("interrupted 발생");
                return "Interrupted";
            }
            return "Completed";
        }
    }
}
