package thread.cas.increment;

public class IncrementPerformanceMain {

    public static final long COUNT = 100_000_000;

    public static void main(String[] args) {
        test(new BasicInteger());
        test(new VolatileInteger());
        test(new SyncInteger());
        test(new MyAtomicInteger());
    }

    private static void test(IncrementInteger integer) {
        long startMs = System.currentTimeMillis();

        //Logic
        for (int i = 0; i < COUNT; i++) {
            integer.increment();
        }

        long endMs = System.currentTimeMillis();
        System.out.println(integer.getClass().getSimpleName() + ": ms = " + (endMs - startMs));
    }
}
