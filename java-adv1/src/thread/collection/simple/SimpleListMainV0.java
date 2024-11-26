package thread.collection.simple;

import java.util.ArrayList;
import java.util.List;

public class SimpleListMainV0 {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        // thread1, thread2 가 동시에 실행 가정
        list.add("A"); // thread1 실행 가정
        list.add("B"); // thread2 실행 가정
        System.out.println(list);

    }
}
