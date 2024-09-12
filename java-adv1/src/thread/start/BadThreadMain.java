package thread.start;

public class BadThreadMain {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + ": main() start");

        HelloThread helloThread = new HelloThread();
        System.out.println(Thread.currentThread().getName() + ": before start()");
        helloThread.run(); // start() 가 아닌 run()을 호출하게 된다면?
        System.out.println(Thread.currentThread().getName() + ": after start()");

        System.out.println(Thread.currentThread().getName() + ": main() end");

    }
}
